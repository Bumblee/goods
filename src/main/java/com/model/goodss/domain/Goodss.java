package com.model.goodss.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.alibaba.fastjson.JSON;
import com.model.goodss.util.ID;
import com.model.goodss.util.Time;

@Entity
@Table(name = "goodss")
@FilterDefs({ @FilterDef(name = "comments", parameters = { @ParamDef(name = "embed", type = "string") }) })
public class Goodss implements Serializable {
	// 商品id
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;
	// 模板用户的id
	private String userid;
	@Transient
	private User user;
	// 商品的名字
	private String name;
	// 商品的标题
	private String title;
	// 商品的简介
	private String brief;
	// 商品的内容
	private String content;
	// 商品的图片
	private String image;
	@Transient
	private List<String> images;
	// 商品的市场价格
	private String mprice;
	// 商品的价格
	private Double price;
	// 商品的销量
	private Integer sales;
	// 商品的规格json
	private String spec;
	// 商品的编号
	private String pcode;
	// 商品参数json
	private String param;
	// 商品的库存
	private Integer inventory;
	// 商品的售后说明
	private String aftersales;
	// 商品分享的数量
	private Integer sharecount;
	// 商品访问的数量
	private Integer readcount;
	// 商品的点赞
	private Integer ilike;
	// 商品的评论
	private Integer cmcount;
	// 商品的发票
	private String invoice;
	// 商品的担保
	private String warranty;
	// 商品上架的时间
	private Date starttime;
	// 商品下架的时间
	private Date endtime;
	// 商品创建的时间
	private Timestamp ctime;
	// 商品更新的时间
	private Timestamp utime;
	// 商品的状态
	private String status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goodssid")
	@OrderBy(value = "ctime DESC")
	@Filter(name = "comments", condition = "embed=:embed")
	private Set<Comment> comments = new HashSet<Comment>(0);

	public Goodss() {
		this.id = ID.uuid();
		this.ctime = Time.timestamp();
		this.ilike = 0;
		this.cmcount = 0;
		this.sharecount = 0;
		this.readcount = 0;
		this.cmcount = 0;
		this.sales = 0;
		this.status = "normal";
		this.userid = userid;
	}

	public Goodss(String userid, String name, String title, String brief, String content, String image, String mprice,
			Double price, String spec, String pcode, String param, Integer inventory, String aftersales, String invoice,
			String warranty, Timestamp starttime, Timestamp endtime, Timestamp utime) {
		this.id = ID.uuid();
		this.ctime = Time.timestamp();
		this.ilike = 0;
		this.cmcount = 0;
		this.sharecount = 0;
		this.readcount = 0;
		this.cmcount = 0;
		this.sales = 0;
		this.status = "normal";
	}

	public Goodss(String userid) {
		this.id = ID.uuid();
		this.ctime = Time.timestamp();
		this.ilike = 0;
		this.cmcount = 0;
		this.sharecount = 0;
		this.readcount = 0;
		this.cmcount = 0;
		this.sales = 0;
		this.status = "normal";
		this.userid = userid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<String> getImages() {
		images = JSON.parseArray(this.getImage(), String.class);
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
		this.image = JSON.toJSONString(images);
	}

	public String getMprice() {
		return mprice;
	}

	public void setMprice(String mprice) {
		this.mprice = mprice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getAftersales() {
		return aftersales;
	}

	public void setAftersales(String aftersales) {
		this.aftersales = aftersales;
	}

	public Integer getSharecount() {
		return sharecount;
	}

	public void setSharecount(Integer sharecount) {
		this.sharecount = sharecount;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getStarttime() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateformat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return dateformat.format(starttime);
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateformat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return dateformat.format(endtime);
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getUtime() {
		return utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIlike() {
		return ilike;
	}

	public void setIlike(Integer ilike) {
		this.ilike = ilike;
	}

	public Integer getCmcount() {
		return cmcount;
	}

	public void setCmcount(Integer cmcount) {
		this.cmcount = cmcount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
