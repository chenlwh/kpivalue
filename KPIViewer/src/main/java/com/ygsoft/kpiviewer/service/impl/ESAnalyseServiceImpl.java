package com.ygsoft.kpiviewer.service.impl;

import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ygsoft.kpiviewer.entity.ServerKPIValue;
import com.ygsoft.kpiviewer.service.ESAnalyseService;
import com.ygsoft.kpiviewer.util.ElasticSearchUtil;
import com.ygsoft.kpiviewer.util.ProjectUtil;

@Service
public class ESAnalyseServiceImpl implements ESAnalyseService{
	private static String index="server_kpi_value";
	private static String type = "server_kpi_value";
	
	
	public void saveKPIValueToES(ServerKPIValue value) {
		ObjectMapper objectMapper = new ObjectMapper();
	    try {
	    	String id = ProjectUtil.getUUID();
	    	value.setId(id);
	    	value.setInsertDay(value.getInsertDate());
			String json = objectMapper.writeValueAsString(value);
			ElasticSearchUtil.save(index, type, id, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Map<String,Object>> findValueByServerId(String serverId) {
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("serverId", serverId);
		return ElasticSearchUtil.termQuery(index, type, termQueryBuilder, "insertDate", SortOrder.ASC);
		
	}
	
	public List<Map<String,Object>> findDailyValueByServerId(String serverId){
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("serverId", serverId);
		return ElasticSearchUtil.groupQuery(index, type, termQueryBuilder);
	}
	
}
