package com.model.goodss.web;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.model.goodss.domain.Config;



public class GoodssForm {
	@NotNull(message = "商品名字不能为空")
	@Size(min = 2, message = "商品名字太短")
	private String name;
	private String title;
	private String brief;
	private String content;
	private List<MultipartFile> image;
	@Pattern(regexp="^[-\\+]?\\d+(\\.\\d*)?|\\.\\d+$",message="必须填写数字")
	@NotNull(message="市场价格不能为空")
	private String mprice;
	private Double price;
	private Integer sale;
	private String spec;
	private String pcode; 
	private String param; 
	private Integer inventory;
	private String aftersales;
	private String invoice;
	private String warranty;
	@DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss")
	@NotNull(message="上架时间不能为空")
	private Date starttime;
	@DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss")
	@NotNull(message="下架时间不能为空")
	private Date endtime;
	private Timestamp utime;
	
	private List<Config> configs;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<MultipartFile> getImage() {
		return image;
	}
	public void setImage(List<MultipartFile> image) {
		this.image = image;
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
	public Integer getSale() {
		return sale;
	}
	public void setSale(Integer sale) {
		this.sale = sale;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
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
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Timestamp getUtime() {
		return utime;
	}
	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}
	
	public List<Config> getConfigs() {
		return configs;
	}

	public void setConfigs(List<Config> configs) {
		this.configs = configs;
	}
	
}
