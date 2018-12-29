package com.ygsoft.kpiviewer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PV_SERVER")
public class Server implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7992651089371279156L;
	
	@Id
	private String id;
	
	private String address;
	
	private String status;
	
	private String type;
	
	private Date inserttime;
	
	private Date updatetime;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the inserttime
	 */
	public Date getInserttime() {
		return inserttime;
	}

	/**
	 * @param inserttime the inserttime to set
	 */
	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}

	/**
	 * @return the updatetime
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
