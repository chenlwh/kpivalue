package com.ygsoft.kpiviewer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PV_SERVER_CONFIG")
public class ServerConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7582128452969398147L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	@Column(name = "ID")
	private String id;
	
	/**portal服务id*/
	private String serverid;
	
	/**portal服务的ip*/
	private String portalip;
	
	/**portal服务的端口*/
	private String portalport;
	
	/**portal服务的名称*/
	private String portalname;
	
	/**portal服务的的数据库版本*/
	private String dbversion;
	
	/**portal服务的数据库类型*/
	private String dbtype;
	
	/**portal服务的数据库ip*/
	private String dbip;
	
	/**portal服务的数据库端口*/
	private String dbport;
	
	/**portal服务的数据库sid*/
	private String dbsid;
	
	/**portal服务的数据库用户*/
	private String dbuser;
	
	/**portal服务的数据库密码*/
	private String dbpassword;
	
	/**记录插入时间*/
	private Date inserttime;

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
	 * @return the portalip
	 */
	public String getPortalip() {
		return portalip;
	}

	/**
	 * @param portalip the portalip to set
	 */
	public void setPortalip(String portalip) {
		this.portalip = portalip;
	}

	/**
	 * @return the portalport
	 */
	public String getPortalport() {
		return portalport;
	}

	/**
	 * @param portalport the portalport to set
	 */
	public void setPortalport(String portalport) {
		this.portalport = portalport;
	}

	/**
	 * @return the portalname
	 */
	public String getPortalname() {
		return portalname;
	}

	/**
	 * @param portalname the portalname to set
	 */
	public void setPortalname(String portalname) {
		this.portalname = portalname;
	}

	/**
	 * @return the dbversion
	 */
	public String getDbversion() {
		return dbversion;
	}

	/**
	 * @param dbversion the dbversion to set
	 */
	public void setDbversion(String dbversion) {
		this.dbversion = dbversion;
	}

	/**
	 * @return the dbtype
	 */
	public String getDbtype() {
		return dbtype;
	}

	/**
	 * @param dbtype the dbtype to set
	 */
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}

	/**
	 * @return the dbip
	 */
	public String getDbip() {
		return dbip;
	}

	/**
	 * @param dbip the dbip to set
	 */
	public void setDbip(String dbip) {
		this.dbip = dbip;
	}

	/**
	 * @return the dbport
	 */
	public String getDbport() {
		return dbport;
	}

	/**
	 * @param dbport the dbport to set
	 */
	public void setDbport(String dbport) {
		this.dbport = dbport;
	}

	/**
	 * @return the dbsid
	 */
	public String getDbsid() {
		return dbsid;
	}

	/**
	 * @param dbsid the dbsid to set
	 */
	public void setDbsid(String dbsid) {
		this.dbsid = dbsid;
	}

	/**
	 * @return the dbuser
	 */
	public String getDbuser() {
		return dbuser;
	}

	/**
	 * @param dbuser the dbuser to set
	 */
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	/**
	 * @return the dbpassword
	 */
	public String getDbpassword() {
		return dbpassword;
	}

	/**
	 * @param dbpassword the dbpassword to set
	 */
	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
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
	
	

}
