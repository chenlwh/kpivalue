package com.ygsoft.kpiviewer.vo;

import java.io.Serializable;

public class ServerNumVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1694674424429900569L;
	
	private String addPeriod;
	
	private int onlineNum;
	
	private int offlineNum;


	/**
	 * @return the addPeriod
	 */
	public String getAddPeriod() {
		return addPeriod;
	}

	/**
	 * @param addPeriod the addPeriod to set
	 */
	public void setAddPeriod(String addPeriod) {
		this.addPeriod = addPeriod;
	}

	/**
	 * @return the onlineNum
	 */
	public int getOnlineNum() {
		return onlineNum;
	}

	/**
	 * @param onlineNum the onlineNum to set
	 */
	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}

	/**
	 * @return the offlineNum
	 */
	public int getOfflineNum() {
		return offlineNum;
	}

	/**
	 * @param offlineNum the offlineNum to set
	 */
	public void setOfflineNum(int offlineNum) {
		this.offlineNum = offlineNum;
	}
	
	

}
