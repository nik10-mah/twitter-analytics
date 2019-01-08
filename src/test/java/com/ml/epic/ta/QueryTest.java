package com.ml.epic.ta;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ml.epic.ta.dto.ExecuteQueryDTO;
import com.ml.epic.ta.service.AthenaService;

public class QueryTest extends BaseTest{

	@Autowired
	AthenaService athenaService;
	
	
	public void executeQuery() {
		try {
		String query = "select * from socialanalyticsblog.tweets hgh gh gh where event_id=\'830b9614-fee3-40b3-a25a-cab49066a679\' limit 20 ";
		ExecuteQueryDTO executeQueryDTO = new ExecuteQueryDTO();
		executeQueryDTO.setSqlValue(query);
		Map<String,Object> map = athenaService.executeQuery(executeQueryDTO);
		assert(map.values().size() > 0);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}

	}
	
	@Test
	public void saveQuery() {
		try {
		String query = "select * from socialanalyticsblog.tweets hgh gh gh where event_id=\'830b9614-fee3-40b3-a25a-cab49066a679\' limit 20 ";
		ExecuteQueryDTO executeQueryDTO = new ExecuteQueryDTO();
		executeQueryDTO.setSqlValue(query);
		executeQueryDTO.setAction("Execute & Save");
		executeQueryDTO.setSqlName("Query");

		Map<String,Object> map = athenaService.executeQuery(executeQueryDTO);
		assert(map.values().size() > 0);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}

	}
}
