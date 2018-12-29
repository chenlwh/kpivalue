package com.ygsoft.kpiviewer.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ygsoft.kpiviewer.entity.ServerKPIValue;

@Service
public interface ESAnalyseService {
	void saveKPIValueToES(ServerKPIValue value);
	
	List<Map<String,Object>> findValueByServerId(String serverId);
	List<Map<String,Object>> findDailyValueByServerId(String serverId);


}
