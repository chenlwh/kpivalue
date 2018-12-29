/**
 * @author chenlin3
 * @date 2017年9月12日
 */
package com.ygsoft.kpiviewer.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ygsoft.kpiviewer.entity.Server;
import com.ygsoft.kpiviewer.entity.ServerKPIValue;
import com.ygsoft.kpiviewer.service.ServerConfigService;
import com.ygsoft.kpiviewer.service.ServerKPIValueService;

@Component
public class WsdlKPIValueJob {
    @Autowired
    private ServerConfigService serverConfigService; 
    @Autowired
    private ServerKPIValueService serverKPIValueService; 
    
    @Scheduled(cron="0 */5 * * * ?")
    public void execute() {
		List <Server> serverList = serverConfigService.getServerList();	
		List<ServerKPIValue> kpiList = serverKPIValueService.getServerKPIValues(serverList);
		
		serverKPIValueService.saveServerKPIValue(kpiList);
    }

}
