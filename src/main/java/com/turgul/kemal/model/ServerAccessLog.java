
package com.turgul.kemal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author kemalturgul
 * @date Jan 6, 2018
 */
@Entity
@Table(name = "server_access_log")
public class ServerAccessLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "server_access_time")
	private Date serverAccessTime;

	@Column(length = 50, nullable = false)
	private String ip;

	@Column(length = 50, nullable = false)
	private String request;

	private int status;

	@Column(name = "user_agent", length = 250)
	private String userAgent;

	public ServerAccessLog() {
	}

	public ServerAccessLog(Date serverAccessTime, String ip, String request, int status, String userAgent) {
		this.serverAccessTime = serverAccessTime;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.userAgent = userAgent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getServerAccessTime() {
		return serverAccessTime;
	}

	public void setServerAccessTime(Date serverAccessTime) {
		this.serverAccessTime = serverAccessTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerAccessLog [id=");
		builder.append(id);
		builder.append(", serverAccessTime=");
		builder.append(serverAccessTime);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", request=");
		builder.append(request);
		builder.append(", status=");
		builder.append(status);
		builder.append(", userAgent=");
		builder.append(userAgent);
		builder.append("]");
		return builder.toString();
	}

}
