package com.model.goodss.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.alibaba.fastjson.annotation.JSONField;
import com.model.goodss.util.ID;
import com.model.goodss.util.Time;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;

	@Column(name = "userid", length = 32)
	private String userid;
	@Transient
	private User user;

	@NotNull(message = "comment.content.notnull")
	@Size(min = 3, message = "comment.content.too_little")
	private String content;
	private String status;
	private Timestamp ctime;
	private String embed;// yes,只有6条通过过滤器关联查询出来
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goodss")
	@NotFound(action = NotFoundAction.IGNORE)
	@JSONField(serialize = false)
	private Goodss goodssid;

	public Comment() {
	}

	public Comment(String userid, Goodss goodssid, String content) {
		this.id = ID.uuid();
		this.userid = userid;
		this.goodssid = goodssid;
		this.content = content;
		this.ctime = Time.timestamp();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Goodss getGoodss() {
		return this.goodssid;
	}

	public void setGoodss(Goodss goodssid) {
		this.goodssid = goodssid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getEmbed() {
		return embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

}