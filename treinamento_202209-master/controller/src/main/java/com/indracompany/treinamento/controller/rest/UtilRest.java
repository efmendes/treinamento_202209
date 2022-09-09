package com.indracompany.treinamento.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("rest/util")
public class UtilRest {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@RequestMapping(value = "/query-result", method = { RequestMethod.POST })
	public @ResponseBody ResponseEntity<List<Map<String, Object>>> query(
			@RequestBody Map<String, Object> param) {

		StringBuilder query = new StringBuilder()
				.append(param.get("query").toString());
		
		
		log.info("params: {}",param);
			
		List<Map<String, Object>> result = namedParameterJdbcTemplate.queryForList(query.toString(), param);
		

		return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
	}

	
	
	

}