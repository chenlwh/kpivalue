/*
* Copyright 2000-2020 YGSoft Inc. All Rights Reserved.
*/

package com.ygsoft.kpiviewer.test;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * desc<br>
 * 
 * @author chenlin3 <br>
 * @version 1.0.0 2019年8月1日 上午11:52:02<br>
 * @since JDK 1.8
 */

public class TestWebService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(portalData());

	}
	
	public static String portalData() {	
		String param = "chenlin";
 		// 命名空间
		String nameSpace = "http://api.ws.ext.impl.invoicemanage.tax.gris.ygsoft.com/";
		String endPoint = "http://10.122.2.37:9080/FMISWeb/services/InvoiceSchedulerService?wsdl";
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			// url为调用webService的wsdl地址
			Client client = dcf.createClient(endPoint);
			// namespace是命名空间，methodName是方法名
			QName name = new QName(nameSpace, "getValue");

			Object[] objects = client.invoke(name, param);
			return objects[0].toString();
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

}
