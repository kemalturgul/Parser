package com.turgul.kemal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.turgul.kemal.enums.Duration;

@Entity
@Table(name = "server_access_blocked")
public class ServerAccessBlocked {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String ip;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "access_start_time")
	private Date accessStartTime;

	@Enumerated(EnumType.ORDINAL)
	private Duration duration;

	private int threshold;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "ip_blocked_time")
	private Date ipBlockedTime;

	@Column(name = "blocking_comment", length = 250)
	private String blockingComment;

	public ServerAccessBlocked() {
	}

	public ServerAccessBlocked(String ip, Date accessStartTime, Duration duration, int threshold, Date ipBlockedTime,
			String blockingComment) {
		this.ip = ip;
		this.accessStartTime = accessStartTime;
		this.duration = duration;
		this.threshold = threshold;
		this.ipBlockedTime = ipBlockedTime;
		this.blockingComment = blockingComment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getAccessStartTime() {
		return accessStartTime;
	}

	public void setAccessStartTime(Date accessStartTime) {
		this.accessStartTime = accessStartTime;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public Date getIpBlockedTime() {
		return ipBlockedTime;
	}

	public void setIpBlockedTime(Date ipBlockedTime) {
		this.ipBlockedTime = ipBlockedTime;
	}

	public String getBlockingComment() {
		return blockingComment;
	}

	public void setBlockingComment(String blockingComment) {
		this.blockingComment = blockingComment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerAccessBlocked [id=");
		builder.append(id);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", accessStartTime=");
		builder.append(accessStartTime);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", threshold=");
		builder.append(threshold);
		builder.append(", ipBlockedTime=");
		builder.append(ipBlockedTime);
		builder.append(", blockingComment=");
		builder.append(blockingComment);
		builder.append("]");
		return builder.toString();
	}

}
