package com.ygsoft.kpiviewer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ygsoft.kpiviewer.entity.Server;
import com.ygsoft.kpiviewer.entity.ServerKPIValue;
import com.ygsoft.kpiviewer.entity.ServerOfflineLog;
import com.ygsoft.kpiviewer.service.ServerKPIValueService;
import com.ygsoft.kpiviewer.service.repository.ServerKPIValueRepository;
import com.ygsoft.kpiviewer.service.repository.ServerOfflineLogRepository;
import com.ygsoft.kpiviewer.service.repository.ServerRepository;
import com.ygsoft.kpiviewer.util.ElasticSearchUtil;
import com.ygsoft.kpiviewer.util.WsdlKPIValueUtil;

@Service
public class ServerKPIValueServiceImpl implements ServerKPIValueService {
	@Autowired
	private ServerOfflineLogRepository serverOfflineLogRepository;
	@Autowired
	private ServerRepository serverRepository;
	@Autowired
	private ServerKPIValueRepository serverKPIValueRepository;
    
	@Override
	public void saveServerKPIValue(List<ServerKPIValue> values){
		for(ServerKPIValue value : values) {
			String serverId = value.getServerId();
			Server pServer = getServerByID(serverId);
			Date kpiDate = value.getInsertDate();
			//当服务器断开时，插入offline信息
			if(value.getTableSpaceSize()==null && "1".equals(pServer.getStatus())) {
				ServerOfflineLog log = new ServerOfflineLog();
				log.setServerid(serverId);
				log.setOfflinetime(kpiDate);
				log.setUpdatetime(kpiDate);
				log.setStatus("0");
				serverOfflineLogRepository.save(log);
				
				pServer.setStatus("0");
				pServer.setUpdatetime(kpiDate);
				serverRepository.save(pServer);
				
			}else if(value.getTableSpaceSize()!=null && "0".equals(pServer.getStatus())){
				//当服务重新连接时，插入connect信息
				ServerOfflineLog log = new ServerOfflineLog();
				log.setServerid(serverId);
				log.setConnecttime(kpiDate);
				log.setUpdatetime(kpiDate);
				log.setStatus("1");
				serverOfflineLogRepository.save(log);
				
				pServer.setStatus("1");
				pServer.setUpdatetime(kpiDate);
				serverRepository.save(pServer);
			}
			
//			if("1".equals(pServer.getStatus())) {
//				publicDao.saveOrUpdate(value);
//			}
//			serverKPIValueRepository.save(value);
			new ESAnalyseServiceImpl().saveKPIValueToES(value);
		}
		
	}
	
	public Server getServerByID(String id) {       
        return serverRepository.findById(id).get();
	}
	
	@Override
	public List<ServerKPIValue> getServerKPIValues(List<Server> list) {		
 		List<ServerKPIValue> kpiList = new ArrayList<ServerKPIValue>();
		for(Server server : list) {			
			String address = server.getAddress();
			String serverId = server.getId();
			
			String wsdl = address + "services/GetKPIValue?wsdl";
			
			String appType = server.getType();
			if("EDT".equals(appType)) {
				wsdl = address + "webservice/getKPIValueService?wsdl";
			}
			
			String serverInfo = WsdlKPIValueUtil.portalData(wsdl);
			
			ServerKPIValue kpiValue = WsdlKPIValueUtil.formatXml(serverInfo);

			kpiValue.setServerId(serverId);
			kpiValue.setInsertDate(new Date());
			
			kpiList.add(kpiValue);
		}
		return kpiList;
	}
	
	@Override
	public void transfer() {
		List<ServerKPIValue> list = serverKPIValueRepository.findAll();
		String index="server_kpi_value";
		String type = "server_kpi_value";
		for(ServerKPIValue value : list) {
			ObjectMapper objectMapper = new ObjectMapper();
		    try {
		    	String id = value.getId();
		    	value.setInsertDay(value.getInsertDate());
				String json = objectMapper.writeValueAsString(value);
				ElasticSearchUtil.save(index, type, id, json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

	}

}
