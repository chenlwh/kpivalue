//package com.ygsoft.kpiviewer.scheduled;
//
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import javax.servlet.ServletRequestEvent;
//import javax.servlet.ServletRequestListener;
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
//
//@WebListener
//public class WebserviceListener implements ServletRequestListener {
//	private static final Logger logger = LoggerFactory.getLogger(WebserviceListener.class);
//	private static boolean isFirst = true;
//	@Value("${webservice.centerAddress:http://127.0.0.1:8080}")
//	private String centerAddress;
//	@Autowired
//	private Environment env;
//	
//	@Override
//	public void requestInitialized(ServletRequestEvent requestEvent) {
//		if(isFirst) {
//			if(!(requestEvent.getServletRequest() instanceof HttpServletRequest)){
//	            throw new IllegalArgumentException((new StringBuilder()).append("Request is not an HttpServletRequest: ").append(requestEvent.getServletRequest()).toString());
//	        } else{
//	            HttpServletRequest request = (HttpServletRequest)requestEvent.getServletRequest();
//
//	    		String serverName = request.getServerName();
//	    		int port = request.getServerPort();
//	    		String path = request.getContextPath();
//	    		if(path!=null && path.length()>0) {
//	    			path = path.substring(1);
//	    		}
//
//		        String dbUrl = env.getProperty("spring.datasource.url");
//		        String username = env.getProperty("spring.datasource.username");
//		        String password = env.getProperty("spring.datasource.password");
//		        
////		        centerAddress = AESUtil.deCodeData(centerAddress);
//		        if(!centerAddress.startsWith("http")) {
//		        	isFirst = false;
//		        	return;
//		        }
//		        centerAddress = centerAddress+"/rest/serverConfig/initHost";
//		            
//		        Map<String,String> configMap = new HashMap<String,String>();
//		        configMap.put("dbUrl", dbUrl);
//		        configMap.put("username", username);
//		        configMap.put("password", password);
//		        configMap.put("serverName", serverName);
//		        configMap.put("port", String.valueOf(port));
//		        configMap.put("path", path);
//		        configMap.put("type", "EDT");
//		        
//		        logger.info("dbUrl:"+dbUrl+" username:"+username+" password:"+password+" serverName:"+serverName +" port:"+port+" path:"+path);
//		            
//		        sendMessage(centerAddress, configMap);	
//		        isFirst = false;
//	        }			
//		}		
//	}
//	
//	public void sendMessage(final String portalCenterAddress, final Map<String,String> configMap) {
//		final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//		Runnable runnable = new Runnable() {
//			public void run() {
//				try {					
//					URL url = new URL(portalCenterAddress);
//					HttpURLConnection con = (HttpURLConnection) url.openConnection();
//					con.setConnectTimeout(3000);
//					con.setReadTimeout(3000);
//					con.setDoOutput(true);
//					con.setDoInput(true);
//					con.setRequestProperty("Content-type", "application/x-java-serialized-object");			
//					con.setRequestMethod("POST");
//					
//					con.connect();
//					
//					OutputStream outputStream = con.getOutputStream();
//					ObjectOutputStream objOutStream = new ObjectOutputStream(outputStream);
//					
//					objOutStream.writeObject(configMap);
//					
//					objOutStream.flush();
//					objOutStream.close();
//					
//					if(con.getResponseCode()==200) {
//						service.shutdown();
//					}
//					
//				}catch(Exception e) {
//					e.getMessage();
//				}				
//			}
//		};
//		
//		service.scheduleWithFixedDelay(runnable, 0, 1, TimeUnit.HOURS);	
//		service.execute(runnable);
//	}	
//	
//	@Override
//	public void requestDestroyed(ServletRequestEvent servletrequestevent) {
//
//	}
//}
