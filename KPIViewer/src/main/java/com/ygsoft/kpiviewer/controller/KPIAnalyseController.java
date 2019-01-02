package com.ygsoft.kpiviewer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ygsoft.kpiviewer.entity.Server;
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
			List<ServerNumVO> monthList = kpiAnalyseService.getDailyServerNum();
			result.put("suc", "yes");
            result.put("allServerList", allServerList);
            result.put("monthList", monthList);
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
//			List <Map<String,Object>> dailyKPIList1 = kpiAnalyseService.getKPIValueByDaily(id);
			List <Map<String,Object>> kpiValueList = esAnalyseService.findValueByServerId(id);
			List <Map<String,Object>> dailyKPIList = esAnalyseService.findDailyValueByServerId(id);
			List<Map<String, Object>> logList = kpiAnalyseService.getLogHistory(id);
			result.put("suc", "yes");
            result.put("kpiValueList", kpiValueList);
            result.put("dailyKPIList", dailyKPIList);
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
