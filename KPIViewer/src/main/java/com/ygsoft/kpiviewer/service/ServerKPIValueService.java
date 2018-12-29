package com.ygsoft.kpiviewer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ygsoft.kpiviewer.entity.Server;
import com.ygsoft.kpiviewer.entity.ServerKPIValue;

@Service
public interface ServerKPIValueService {
	void saveServerKPIValue(List<ServerKPIValue> values);
	List <ServerKPIValue> getServerKPIValues(List <Server> list);
	Server getServerByID(String id);
	void transfer();

}
