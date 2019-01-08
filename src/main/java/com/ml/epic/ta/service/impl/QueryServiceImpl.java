package com.ml.epic.ta.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ml.epic.ta.model.Query;
import com.ml.epic.ta.repository.QueryRepository;
import com.ml.epic.ta.service.QueryService;

/**
 * The Class QueryServiceImpl.
 */
@Service("queryService")
public class QueryServiceImpl implements QueryService{

	@Autowired
	QueryRepository queryRepository;
	
	@Override
	public String save(String query, String name) {
		String saveResult = null;
		// Save Query Object.
		Query queryObj = new Query();
		queryObj.setCreatedAt(new Date());
		// get user already login
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		queryObj.setName(name);
		queryObj.setValue(query);
		queryObj.setCreatedBy(username);
		queryRepository.save(queryObj);
		saveResult = "success";
		return saveResult;
	}

	
	@Override
	public List<Query> findAll() {
		return (List<Query>) queryRepository.findAll();
	}

}
