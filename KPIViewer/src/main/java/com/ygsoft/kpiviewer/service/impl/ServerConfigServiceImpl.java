package com.ygsoft.kpiviewer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.ygsoft.kpiviewer.entity.Server;
import com.ygsoft.kpiviewer.entity.ServerConfig;
import com.ygsoft.kpiviewer.service.ServerConfigService;
import com.ygsoft.kpiviewer.service.repository.ServerConfigRepository;
import com.ygsoft.kpiviewer.service.repository.ServerRepository;
import com.ygsoft.kpiviewer.util.ProjectUtil;

@Service
public class ServerConfigServiceImpl implements ServerConfigService {
    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private ServerConfigRepository serverConfigRepository;
    @Autowired
	private NamedParameterJdbcTemplate namedTemplate;
    
	@Override
	public void saveServerData(ServerConfig config, String appType) {
		String serverID = null;
		String serverName = config.getPortalip();
		String serverPort = config.getPortalport();
		String portalName = config.getPortalname();
		String portalAddress = "http://" + serverName + ":" + serverPort + "/" ;
		if(portalName!=null && portalName.length()>0) {
			portalAddress = portalAddress + portalName + "/";
		}
		
		Server server = getServerByAddr(portalAddress);
		if(server==null) {
			serverID = ProjectUtil.getUUID();
			
			Server pServer = new Server();
			pServer.setId(serverID);
			pServer.setAddress(portalAddress);
			pServer.setStatus("1");
			pServer.setInserttime(new Date());
			pServer.setType(appType);
			
			serverRepository.save(pServer);
			
			config.setServerid(serverID);
			config.setInserttime(new Date());
			
			serverConfigRepository.save(config);
			
		}else {					
			serverID = server.getId();	
			if(!isExistServerConfig(config)) {
				config.setServerid(serverID);
				config.setInserttime(new Date());
				
				serverConfigRepository.save(config);
			}
		}
		
		
	}
	
	@Override
	public List<Server> getServerList() {
		return serverRepository.findAll();
	}
	
	public Server getServerByAddr(String address) {
		return serverRepository.findByAddress(address);
	}
	
	@Override
	public boolean isExistServerConfig(ServerConfig config) {
        String csql = " select count(*) from PV_SERVER_CONFIG where portalip = :portalip and portalport = :portalport and portalname = :portalname and dbtype =:dbtype "
        		+ "and dbversion = :dbversion and dbip = :dbip and dbport = :dbport and dbsid = :dbsid and dbuser = :dbuser and dbpassword = :dbpassword ";
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("portalip", config.getPortalip());
        params.put("portalport", config.getPortalport());
        params.put("portalname", config.getPortalname());
        params.put("dbtype", config.getDbtype());
        params.put("dbversion", config.getDbversion());
        params.put("dbip", config.getDbip());
        params.put("dbport", config.getDbport());
        params.put("dbsid", config.getDbsid());
        params.put("dbuser", config.getDbuser());
        params.put("dbpassword", config.getDbpassword());
        
        
        List<Map<String,Object>> list = namedTemplate.queryForList(csql, params);
        boolean isExist = false;
        if(list.size()>0) {
        	isExist = true;
        }
        
        return isExist;
	}

}
