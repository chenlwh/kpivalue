package com.ygsoft.kpiviewer.controller;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ygsoft.kpiviewer.entity.ServerConfig;
import com.ygsoft.kpiviewer.service.ServerConfigService;

@RestController
@RequestMapping("/rest/serverConfig")
public class ServerConfigController{
	@Autowired
	private ServerConfigService serverConfigService;

	@RequestMapping(value = "/initHost", method = RequestMethod.POST)
	public Map<String, Object> initHost(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			ServletInputStream inStream = request.getInputStream();
			ObjectInputStream objInStream = new ObjectInputStream(inStream);
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) objInStream.readObject();

			ServerConfig config = getDsConfig(map);
			
			String appType = map.get("type");
			serverConfigService.saveServerData(config,appType);
			
			result.put("suc", "yes");
            result.put("data", "success");

		} catch (Exception e) {
			result.put("suc", "no");
            result.put("msg", "error");
            e.printStackTrace();
		}
		return result;
	}

	public ServerConfig getDsConfig(Map<String, String> map) {
		String dbUrl = map.get("dbUrl");
		String username = map.get("username");
		String password = map.get("password");
		String dbVersion = map.get("dbVersion");
		String serverName = map.get("serverName");
		String serverPort = map.get("port");
		String portalName = map.get("path");

		String[] urlElements = dbUrl.split(":");

		String type = "oracle";
		String ip = null;
		String port = null;
		String sid = null;
		if (dbUrl.toLowerCase().contains("oracle")) {
			type = "oracle";
			ip = urlElements[3].substring(1);
			port = urlElements[4];
			sid = urlElements[5];

		} else if (dbUrl.toLowerCase().contains("postgresql")) {
			type = "postgresql";
			ip = urlElements[2].substring(2);

			String temp = urlElements[3];
			String[] tempArray = temp.split("/");
			port = tempArray[0];
			sid = tempArray[1];

		} else if (dbUrl.toLowerCase().contains("mysql")) {
			type = "mysql";
			ip = urlElements[2].substring(2);

			String temp = urlElements[3];
			String[] tempArray = temp.split("/");
			port = tempArray[0];
			sid = tempArray[1];
		}

		ServerConfig config = new ServerConfig();
		config.setDbtype(type);
		config.setDbip(ip);
		config.setDbport(port);
		config.setDbsid(sid);
		config.setDbuser(username);
		config.setDbpassword(password);
		config.setDbversion(dbVersion);
		config.setPortalip(serverName);
		config.setPortalport(serverPort);
		config.setPortalname(portalName);

		return config;
	}

}
