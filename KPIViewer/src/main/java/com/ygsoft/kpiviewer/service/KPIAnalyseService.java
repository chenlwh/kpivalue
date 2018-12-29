package com.ygsoft.kpiviewer.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ygsoft.kpiviewer.entity.Server;
import com.ygsoft.kpiviewer.entity.ServerKPIValue;
import com.ygsoft.kpiviewer.vo.ServerNumVO;

@Service
public interface KPIAnalyseService {
	List<Server> getAllServer();
	List<Server> getServerByCondition(String condition);
	List<ServerKPIValue> getKPIValueByServerId(String serverId);
	List<Map<String,Object>> getKPIValueByDaily(String serverId);
	List<Map<String, Object>> getLogHistory(String id);
	List<ServerNumVO> getMonthlyServerNum();
	List<ServerNumVO> getDailyServerNum();

}
