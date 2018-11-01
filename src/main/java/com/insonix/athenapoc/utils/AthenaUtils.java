/**
 * 
 */
package com.insonix.athenapoc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.athena.AmazonAthena;
import com.amazonaws.services.athena.model.ColumnInfo;
import com.amazonaws.services.athena.model.Datum;
import com.amazonaws.services.athena.model.GetQueryExecutionRequest;
import com.amazonaws.services.athena.model.GetQueryExecutionResult;
import com.amazonaws.services.athena.model.GetQueryResultsRequest;
import com.amazonaws.services.athena.model.GetQueryResultsResult;
import com.amazonaws.services.athena.model.QueryExecutionContext;
import com.amazonaws.services.athena.model.QueryExecutionState;
import com.amazonaws.services.athena.model.ResultConfiguration;
import com.amazonaws.services.athena.model.Row;
import com.amazonaws.services.athena.model.StartQueryExecutionRequest;
import com.amazonaws.services.athena.model.StartQueryExecutionResult;
import com.insonix.athenapoc.utils.IConstants.AwsAthena;

/**
 * The Class AthenaUtils: The utilty calss for transacting with Athena
 *
 * @author Nikhil Mahajan
 * @since Oct 27, 2018
 */
public class AthenaUtils {

	/**
	 * Submits a sample query to Athena and returns the execution ID of the
	 * query.
	 *
	 * @param client
	 *            the AmazonAthena client
	 * @param athenaQuery
	 *            the athena query
	 * @return the excutionId
	 */
	public static String submitAthenaQuery(AmazonAthena client, String athenaQuery) {
		// The QueryExecutionContext allows us to set the Database.
		QueryExecutionContext queryExecutionContext = new QueryExecutionContext()
				.withDatabase(AwsAthena.ATHENA_DEFAULT_DATABASE);

		// The result configuration specifies where the results of the query
		// should go
		// in S3 and encryption options
		ResultConfiguration resultConfiguration = new ResultConfiguration()
				// You can provide encryption options for the output that is
				// written.
				// .withEncryptionConfiguration(encryptionConfiguration)
				.withOutputLocation(AwsAthena.ATHENA_OUTPUT_BUCKET);

		// Create the StartQueryExecutionRequest to send to Athena which will
		// start the
		// query.
		StartQueryExecutionRequest startQueryExecutionRequest = new StartQueryExecutionRequest()
				.withQueryString(athenaQuery).withQueryExecutionContext(queryExecutionContext)
				.withResultConfiguration(resultConfiguration);

		StartQueryExecutionResult startQueryExecutionResult = client.startQueryExecution(startQueryExecutionRequest);
		return startQueryExecutionResult.getQueryExecutionId();
	}

	/**
	 * Wait for an Athena query to complete, fail or to be cancelled. This is
	 * done by polling Athena over an interval of time. If a query fails or is
	 * cancelled, then it will throw an exception.
	 *
	 * @param client
	 *            the AmazonAthena client
	 * @param queryExecutionId
	 *            the query execution id
	 * @throws InterruptedException
	 *             the interrupted exception
	 */

	public static void waitForQueryToComplete(AmazonAthena client, String queryExecutionId)
			throws InterruptedException {
		GetQueryExecutionRequest getQueryExecutionRequest = new GetQueryExecutionRequest()
				.withQueryExecutionId(queryExecutionId);

		GetQueryExecutionResult getQueryExecutionResult = null;
		boolean isQueryStillRunning = true;
		while (isQueryStillRunning) {
			getQueryExecutionResult = client.getQueryExecution(getQueryExecutionRequest);
			String queryState = getQueryExecutionResult.getQueryExecution().getStatus().getState();
			if (queryState.equals(QueryExecutionState.FAILED.toString())) {
				throw new RuntimeException("Query Failed to run with Error Message: "
						+ getQueryExecutionResult.getQueryExecution().getStatus().getStateChangeReason());
			} else if (queryState.equals(QueryExecutionState.CANCELLED.toString())) {
				throw new RuntimeException("Query was cancelled.");
			} else if (queryState.equals(QueryExecutionState.SUCCEEDED.toString())) {
				isQueryStillRunning = false;
			} else {
				// Sleep an amount of time before retrying again.
				Thread.sleep(AwsAthena.SLEEP_AMOUNT_IN_MS);
			}
			System.out.println("Current Status is: " + queryState);
		}
	}

	/**
	 * This code calls Athena and retrieves the results of a query. The query
	 * must be in a completed state before the results can be retrieved and
	 * paginated. The first row of results are the column headers.
	 *
	 * @param client
	 *            the client
	 * @param queryExecutionId
	 *            the query execution id
	 * @return the list
	 */
	public static List<Map<String, String>> processResultRows(AmazonAthena client, String queryExecutionId) {
		GetQueryResultsRequest getQueryResultsRequest = new GetQueryResultsRequest()
				// Max Results can be set but if its not set,
				// it will choose the maximum page size
				// As of the writing of this code, the maximum value is 1000
				// .withMaxResults(1000)
				.withQueryExecutionId(queryExecutionId);

		GetQueryResultsResult getQueryResultsResult = client.getQueryResults(getQueryResultsRequest);
		List<ColumnInfo> columnInfoList = getQueryResultsResult.getResultSet().getResultSetMetadata().getColumnInfo();
		List<Map<String, String>> records = new ArrayList<>();
		while (true) {
			List<Row> results = getQueryResultsResult.getResultSet().getRows();
			for (Row row : results) {
				// Process the row. The first row of the first page holds the
				// column names.
				// System.out.println("Row "+row.getData());
				records.add(processRow(row, columnInfoList));
			}
			// If nextToken is null, there are no more pages to read. Break out
			// of the loop.
			if (getQueryResultsResult.getNextToken() == null) {
				break;
			}
			getQueryResultsResult = client
					.getQueryResults(getQueryResultsRequest.withNextToken(getQueryResultsResult.getNextToken()));

		}

		// removing the first record as it contains column names only
		records.remove(0);
		return records;
	}

	/**
	 * Process single row object from Amazon athena query result
	 *
	 * @param row
	 *            the row
	 * @param columnInfoList
	 *            the column info list
	 * @return the Map<String, String> where key = columnName and value = row
	 *         value in string for that column
	 */
	public static Map<String, String> processRow(Row row, List<ColumnInfo> columnInfoList) {

		Map<String, String> record = new HashMap<>();
		for (int i = 0; i < columnInfoList.size(); ++i) {
			Datum data = row.getData().get(i);
			ColumnInfo col = columnInfoList.get(i);

			record.put(col.getName(), data.getVarCharValue());
			// switch (columnInfoList.get(i).getType()) {
			// case "varchar":
			// // Convert and Process as String
			// break;
			// case "tinyint":
			// // Convert and Process as tinyint
			// break;
			// case "smallint":
			// // Convert and Process as smallint
			// break;
			// case "integer":
			// // Convert and Process as integer
			// break;
			// case "bigint":
			// // Convert and Process as bigint
			// break;
			// case "double":
			// // Convert and Process as double
			// break;
			// case "boolean":
			// // Convert and Process as boolean
			// break;
			// case "date":
			// // Convert and Process as date
			// break;
			// case "timestamp":
			// // Convert and Process as timestamp
			// break;
			// default:
			// throw new RuntimeException("Unexpected Type is not expected" +
			// columnInfoList.get(i).getType());
			// }
		}

		return record;
	}
}
