package com.ygsoft.kpiviewer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PV_OFFLINE_LOG")
public class ServerOfflineLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8123144282132841486L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	@Column(name = "ID")
	private String id;
	
	private String serverid;
	
	private Date updatetime;
	
	private String status;
	
	private Date offlinetime;
	
	private Date connecttime;
	
	@Transient
	private String address;

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
	 * @return the serverid
	 */
	public String getServerid() {
		return serverid;
	}

	/**
	 * @param serverid the serverid to set
	 */
	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	/**
	 * @return the offlinetime
	 */
	public Date getOfflinetime() {
		return offlinetime;
	}

	/**
	 * @param offlinetime the offlinetime to set
	 */
	public void setOfflinetime(Date offlinetime) {
		this.offlinetime = offlinetime;
	}

	/**
	 * @return the connecttime
	 */
	public Date getConnecttime() {
		return connecttime;
	}

	/**
	 * @param connecttime the connecttime to set
	 */
	public void setConnecttime(Date connecttime) {
		this.connecttime = connecttime;
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
	

}
