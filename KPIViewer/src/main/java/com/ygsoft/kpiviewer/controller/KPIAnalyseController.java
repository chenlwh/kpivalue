package com.ygsoft.kpiviewer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ygsoft.kpiviewer.entity.Server;
import com.ygsoft.kpiviewer.entity.ServerKPIValue;
import com.ygsoft.kpiviewer.entity.ServerOfflineLog;
import com.ygsoft.kpiviewer.service.ESAnalyseService;
import com.ygsoft.kpiviewer.service.KPIAnalyseService;
import com.ygsoft.kpiviewer.service.ServerKPIValueService;
import com.ygsoft.kpiviewer.vo.ServerNumVO;

@RestController
@RequestMapping("/rest/kpiAnalyse")
public class KPIAnalyseController{
	
	@Autowired
	private KPIAnalyseService kpiAnalyseService;
	@Autowired
	private ESAnalyseService esAnalyseService;
	@Autowired
	private ServerKPIValueService serverKPIValueService;
	
	@RequestMapping(value = "/allServer", method = RequestMethod.POST)
	public Map<String, Object> allServer() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List <Server> allServerList = kpiAnalyseService.getAllServer();		
			List<ServerNumVO> dailyList = kpiAnalyseService.getDailyServerNum();
			List<String> nameList = new ArrayList<String>();
			List<Object> onList = new ArrayList<Object>();
			List<Object> offList = new ArrayList<Object>();
			for(ServerNumVO vo : dailyList) {
				nameList.add(vo.getAddPeriod());
				onList.add(vo.getOnlineNum());
				offList.add(vo.getOfflineNum());
			}
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("name", nameList);
			values.put("on", onList);
			values.put("off", offList);
			
			result.put("suc", "yes");
            result.put("allServerList", allServerList);
            result.put("values", values);
		} catch (Exception e) {
            result.put("suc", "no");
            result.put("msg", "error");
            e.printStackTrace();
        }
		return result;

	}
	
	@RequestMapping(value = "/searchServer", method = RequestMethod.POST)
	public Map<String, Object> searchServerByName(@RequestParam("value") String value) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List <Server> serverList = kpiAnalyseService.getServerByCondition(value);		
			result.put("suc", "yes");
            result.put("allServerList", serverList);
		} catch (Exception e) {
            result.put("suc", "no");
            result.put("msg", "error");
            e.printStackTrace();
        }
		return result;
	}
	
	@RequestMapping(value = "/showKPIValue", method = RequestMethod.POST)
	public Map<String, Object> showKPIValueById(@RequestParam("id") String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
//			List <ServerKPIValue> kpiValueList = kpiAnalyseService.getKPIValueByServerId(id);
//			List <Map<String,Object>> dailyKPIList = kpiAnalyseService.getKPIValueByDaily(id);
			List <Map<String,Object>> kpiValueList = esAnalyseService.findValueByServerId(id);
			List <Map<String,Object>> dailyKPIList = esAnalyseService.findDailyValueByServerId(id);
			List<ServerOfflineLog> logList = kpiAnalyseService.getLogHistory(id);
			
			
			List<Object> nameList = new ArrayList<Object>();
			List<Object> registeredNumList = new ArrayList<Object>();
			List<Object> onlineNumList = new ArrayList<Object>();
			List<Object> responseTimeList = new ArrayList<Object>();
			List<Object> runningTimeList = new ArrayList<Object>();
			List<Object> tableSpaceSizeList = new ArrayList<Object>();
			List<Object> dbResponseTimeList = new ArrayList<Object>();
			for(Map<String,Object> map : kpiValueList) {
				Object insertDate =map.get("insertDate");
				Object registeredNum =map.get("registeredNum");
				Object onlineNum = map.get("onlineNum");
				Object responseTime = map.get("responseTime");
				Object runningTime = map.get("runningTime");
				Object dbResponseTime = map.get("dbResponseTime");
				Object spaceSize = map.get("spaceSize");
				if(registeredNum==null) {
					registeredNum = 0;
				}
				if(onlineNum==null) {
					onlineNum = 0;
				}
				if(responseTime==null) {
					responseTime = 0;
				}
				if(runningTime==null) {
					runningTime = 0;
				}
				if(dbResponseTime==null) {
					dbResponseTime = 0;
				}
				if(spaceSize==null) {
					spaceSize = 0.0;
				}
				
				nameList.add(insertDate);
				registeredNumList.add(registeredNum);
				onlineNumList.add(onlineNum);
				responseTimeList.add(responseTime);
				runningTimeList.add(runningTime);
				dbResponseTimeList.add(dbResponseTime);
				tableSpaceSizeList.add(spaceSize);

			}
//			
//			for(ServerKPIValue value: kpiValueList) {
//				Integer registeredNum =value.getRegisteredNum();
//				Integer onlineNum = value.getOnlineNum();
//				Integer responseTime = value.getResponseTime();
//				Integer runningTime = value.getRunningTime();
//				Integer dbResponseTime = value.getDbResponseTime();
//				Double spaceSize = value.getTableSpaceSize();
//				if(registeredNum==null) {
//					registeredNum = 0;
//				}
//				if(onlineNum==null) {
//					onlineNum = 0;
//				}
//				if(responseTime==null) {
//					responseTime = 0;
//				}
//				if(runningTime==null) {
//					runningTime = 0;
//				}
//				if(dbResponseTime==null) {
//					dbResponseTime = 0;
//				}
//				if(spaceSize==null) {
//					spaceSize = 0.0;
//				}
//				
//				nameList.add(value.getInsertDate());
//				registeredNumList.add(registeredNum);
//				onlineNumList.add(onlineNum);
//				responseTimeList.add(responseTime);
//				runningTimeList.add(runningTime);
//				dbResponseTimeList.add(dbResponseTime);
//				tableSpaceSizeList.add(spaceSize);
//
//			}
			
			Map<String, Object> kpiValue = new HashMap<String, Object>();
			kpiValue.put("name", nameList);
			kpiValue.put("registeredNum",registeredNumList);
			kpiValue.put("onlineNum", onlineNumList);
			kpiValue.put("responseTime", responseTimeList);
			kpiValue.put("runningTime", runningTimeList);
			kpiValue.put("tableSpaceSize", tableSpaceSizeList);
			kpiValue.put("dbResponseTime", dbResponseTimeList);
			
			List<Object> dailyList = new ArrayList<Object>();
			List<Object> dailyLoginNumList = new ArrayList<Object>();
			List<Object> loginNumList = new ArrayList<Object>();
			List<Object> sessionNumList = new ArrayList<Object>();
			for(Map<String,Object> map: dailyKPIList) {
				dailyList.add(map.get("insertDate").toString().replaceAll("-", ""));
				dailyLoginNumList.add(map.get("dailyLoginNum"));
				loginNumList.add(map.get("loginNum"));
				sessionNumList.add(map.get("sessionNum"));
			}
			Map<String, Object> dailyKpi = new HashMap<String, Object>();
			dailyKpi.put("daily", dailyList);
			dailyKpi.put("dailyLoginNum", dailyLoginNumList);
			dailyKpi.put("loginNum", loginNumList);
			dailyKpi.put("sessionNum", sessionNumList);
			
			result.put("suc", "yes");
            result.put("kpiValue", kpiValue);
            result.put("dailyKPI", dailyKpi);
            result.put("logList", logList);
		} catch (Exception e) {
            result.put("suc", "no");
            result.put("msg", "error");
            e.printStackTrace();
        }
		return result;
	}
	
	@RequestMapping(value = "/transfer")
	public Map<String, Object> transfer() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			serverKPIValueService.transfer();
			result.put("suc", "yes");
		} catch (Exception e) {
            result.put("suc", "no");
            result.put("msg", "error");
            e.printStackTrace();
        }
		return result;
	}
}
