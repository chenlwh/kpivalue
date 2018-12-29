package com.ygsoft.kpiviewer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ygsoft.kpiviewer.entity.Server;
import com.ygsoft.kpiviewer.entity.ServerConfig;

@Service
public interface ServerConfigService {	
	void saveServerData(ServerConfig config,String appType);
	List <Server> getServerList();
	Server getServerByAddr(String address);
	boolean isExistServerConfig(ServerConfig config);

}
