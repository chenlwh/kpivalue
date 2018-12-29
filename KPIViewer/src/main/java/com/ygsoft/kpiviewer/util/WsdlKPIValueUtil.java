package com.ygsoft.kpiviewer.util;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ygsoft.kpiviewer.entity.ServerKPIValue;

public class WsdlKPIValueUtil {
	
    //解析xml文档
	public static ServerKPIValue formatXml(String result){
    	Map<String,String> map = new LinkedHashMap<String,String>();
    	ServerKPIValue kpiValue = new ServerKPIValue();
    	if(result == null) {
    		return kpiValue;
    	}
    	try {
    		SAXReader reader = new SAXReader();
			Document document= reader.read(new ByteArrayInputStream(result.getBytes("UTF-8")));
			
			Element root =	document.getRootElement();
			List<Element> elemlist = root.elements();
			for (Element element : elemlist) {
				//遍历子节点
				String nodename = element.getName();
				String value = element.getText();
				
				if("status".equals(nodename) && !"success".equals(value)){
					break;
				}
				
				if("Corporation".equals(nodename)){
					List<Element> apinodes = element.elements("api");
					for(Element api : apinodes) {
						 Attribute attr = api.attribute("name");
						 if(attr==null) {
							 continue;
						 }
						 String apikey = attr.getValue();
						 Element valuenode = api.element("value");
						 String apivalue = valuenode.getText();
						 
						 map.put(apikey, apivalue);
					}
				}
			}
			
			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String key = it.next();
				String text = map.get(key);
				initKPIValue(kpiValue, key , text);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
  	
    	return kpiValue;
    }
	
	public static String portalData(String endPoint) {	
		String param = "<?xml version='1.0' encoding='gb2312'?>"
		+ "<info>"
		+ "<CorporationCode>68,99</CorporationCode>"
		+ "<Time>2016-12-19 00:00:00</Time>"
		// + "<api name='BusinessSystemOnlineRoll'></api>"
		// + "<api name='BusinessSystemLoginRoll'></api>"
		+ "<api name='BusinessUserRegNum'></api>"
		+ "<api name='BusinessSystemOnlineNum'></api>"
		+ "<api name='BusinessDayLoginNum'></api>"
		+ "<api name='BusinessVisitCount'></api>"
		+ "<api name='BusinessSystemSessionNum'></api>"
		+ "<api name='BusinessSystemResponseTime'></api>"
		+ "<api name='BusinessSystemRunningTime'></api>"
		+ "<api name='BusinessDataTableSpace'></api>"
		+ "<api name='BusinessSystemDBTime'></api>"
		+ "</info>";
 		// 命名空间
		String nameSpace = "http://api.webservice.matcloud.ygsoft.com/";
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			// url为调用webService的wsdl地址
			Client client = dcf.createClient(endPoint);
			// namespace是命名空间，methodName是方法名
			QName name = new QName(nameSpace, "getKPIValue");

			Object[] objects = client.invoke(name, param);
			return objects[0].toString();
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}
	
	public static void initKPIValue(ServerKPIValue kpiValue, String key, String value) {
		if("BusinessUserRegNum".equals(key)) {
			kpiValue.setRegisteredNum(Integer.parseInt(value));
			
		}else if("BusinessSystemOnlineNum".equals(key)) {
			kpiValue.setOnlineNum(Integer.parseInt(value));
			
		}else if("BusinessDayLoginNum".equals(key)) {
			kpiValue.setDailyLoginNum(Integer.parseInt(value));
			
		}else if("BusinessVisitCount".equals(key)) {
			kpiValue.setLoginNum(Integer.parseInt(value));
			
		}else if("BusinessSystemSessionNum".equals(key)) {
			kpiValue.setSessionNum(Integer.parseInt(value));
			
		}else if("BusinessSystemResponseTime".equals(key)) {
			kpiValue.setResponseTime(Integer.parseInt(value));
			
		}else if("BusinessSystemRunningTime".equals(key)) {
			kpiValue.setRunningTime(Integer.parseInt(value));
			
		}else if("BusinessDataTableSpace".equals(key)) {
			kpiValue.setTableSpaceSize(Double.valueOf(value));
			
		}else if("BusinessSystemDBTime".equals(key)) {
			kpiValue.setDbResponseTime(Integer.parseInt(value));
			
		}
		
	}

}
