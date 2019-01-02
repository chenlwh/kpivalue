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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public Integer getRegisteredNum() {
		return registeredNum;
	}

	public void setRegisteredNum(Integer registeredNum) {
		this.registeredNum = registeredNum;
	}

	public Integer getOnlineNum() {
		return onlineNum;
	}

	public void setOnlineNum(Integer onlineNum) {
		this.onlineNum = onlineNum;
	}

	public Integer getDailyLoginNum() {
		return dailyLoginNum;
	}

	public void setDailyLoginNum(Integer dailyLoginNum) {
		this.dailyLoginNum = dailyLoginNum;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Integer getSessionNum() {
		return sessionNum;
	}

	public void setSessionNum(Integer sessionNum) {
		this.sessionNum = sessionNum;
	}

	public Integer getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Integer responseTime) {
		this.responseTime = responseTime;
	}

	public Integer getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(Integer runningTime) {
		this.runningTime = runningTime;
	}

	public Double getTableSpaceSize() {
		return tableSpaceSize;
	}

	public void setTableSpaceSize(Double tableSpaceSize) {
		this.tableSpaceSize = tableSpaceSize;
	}

	public Integer getDbResponseTime() {
		return dbResponseTime;
	}

	public void setDbResponseTime(Integer dbResponseTime) {
		this.dbResponseTime = dbResponseTime;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getInsertDay() {
		return insertDay;
	}

	public void setInsertDay(Date insertDay) {
		this.insertDay = insertDay;
	}

}
