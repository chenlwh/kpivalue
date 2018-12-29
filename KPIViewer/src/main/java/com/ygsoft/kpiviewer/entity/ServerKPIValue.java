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
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "PV_SERVER_KPIVALUE")
public class ServerKPIValue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6135749234328489326L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	@Column(name = "ID")
	private String id;
	
	/**
     * portal服务ID，外键
     * 关联PServerConfig
     */
	@Column(name = "SERVER_ID")
	private String serverId;
	
	/**
     * 注册用户数
     */
	@Column(name = "REGISTERED_NUM")
    private Integer registeredNum;

    /**
     * 在线用户数
     */
	@Column(name = "ONLINE_NUM")
    private Integer onlineNum;

    /**
     * 日登陆人数
     */
	@Column(name = "DAILY_LOGIN_NUM")
    private Integer dailyLoginNum;

    /**
     * 累计访问人数
     */
	@Column(name = "TOTAL_LOGIN_NUM")
    private Integer loginNum;

    /**
     * 页面会话连接数
     */
	@Column(name = "SESSION_NUM")
    private Integer sessionNum;

    /**
     * 系统服务响应时长
     */
	@Column(name = "SERVER_RESPONSE_TIME")
    private Integer responseTime;

    /**
     * 系统健康运行时长
     */
	@Column(name = "RUNNING_TIME")
    private Integer runningTime;

    /**
     * 业务应用系统占用表空间大小
     */
	@Column(name = "TABLE_SPACE_SIZE")
    private Double tableSpaceSize;

    /**
     * 数据库平均响应时长
     */
	@Column(name = "DB_RESPONSE_TIME")
    private Integer dbResponseTime;

    /**
     * 数据统计时间
     */
	@Column(name = "INSERT_DATE")
	@Field(type = FieldType.Date, format = DateFormat.date_optional_time)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZ",timezone="GMT+8")
    private Date insertDate;
	
	@Transient
	@Field(type = FieldType.Date, format = DateFormat.custom,pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd",timezone="GMT+8")
    private Date insertDay;

	/**
	 * @return the registeredNum
	 */
	public Integer getRegisteredNum() {
		return registeredNum;
	}

	/**
	 * @param registeredNum the registeredNum to set
	 */
	public void setRegisteredNum(Integer registeredNum) {
		this.registeredNum = registeredNum;
	}

	/**
	 * @return the onlineNum
	 */
	public Integer getOnlineNum() {
		return onlineNum;
	}

	/**
	 * @param onlineNum the onlineNum to set
	 */
	public void setOnlineNum(Integer onlineNum) {
		this.onlineNum = onlineNum;
	}

	/**
	 * @return the dailyLoginNum
	 */
	public Integer getDailyLoginNum() {
		return dailyLoginNum;
	}

	/**
	 * @param dailyLoginNum the dailyLoginNum to set
	 */
	public void setDailyLoginNum(Integer dailyLoginNum) {
		this.dailyLoginNum = dailyLoginNum;
	}

	/**
	 * @return the loginNum
	 */
	public Integer getLoginNum() {
		return loginNum;
	}

	/**
	 * @param loginNum the loginNum to set
	 */
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	/**
	 * @return the sessionNum
	 */
	public Integer getSessionNum() {
		return sessionNum;
	}

	/**
	 * @param sessionNum the sessionNum to set
	 */
	public void setSessionNum(Integer sessionNum) {
		this.sessionNum = sessionNum;
	}

	/**
	 * @return the responseTime
	 */
	public Integer getResponseTime() {
		return responseTime;
	}

	/**
	 * @param responseTime the responseTime to set
	 */
	public void setResponseTime(Integer responseTime) {
		this.responseTime = responseTime;
	}

	/**
	 * @return the runningTime
	 */
	public Integer getRunningTime() {
		return runningTime;
	}

	/**
	 * @param runningTime the runningTime to set
	 */
	public void setRunningTime(Integer runningTime) {
		this.runningTime = runningTime;
	}

	/**
	 * @return the tableSpaceSize
	 */
	public Double getTableSpaceSize() {
		return tableSpaceSize;
	}

	/**
	 * @param tableSpaceSize the tableSpaceSize to set
	 */
	public void setTableSpaceSize(Double tableSpaceSize) {
		this.tableSpaceSize = tableSpaceSize;
	}

	/**
	 * @return the dbResponseTime
	 */
	public Integer getDbResponseTime() {
		return dbResponseTime;
	}

	/**
	 * @param dbResponseTime the dbResponseTime to set
	 */
	public void setDbResponseTime(Integer dbResponseTime) {
		this.dbResponseTime = dbResponseTime;
	}

	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

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

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public Date getInsertDay() {
		return insertDay;
	}

	public void setInsertDay(Date insertDay) {
		this.insertDay = insertDay;
	}

	
	
}
