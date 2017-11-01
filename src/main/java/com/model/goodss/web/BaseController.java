package com.model.goodss.web;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class BaseController {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected String sessuserid = "";
	//protected User sessuser = new User();

	//@Resource(name = "baseUser")
	//protected BaseUser baseUser;

	protected String communityid;
	protected String courseid;
	protected String lessonid;
	protected String eventid;
	protected String taskid;
	protected String cmcg;
	protected Set<String> syscates;
	protected Set<String> cmcates;
	protected Set<String> tags;
	protected String keyword;
	protected String orderfield;
	protected String order;
	protected int pagesize;
	protected int page;

	@ModelAttribute
	public void getSessionUser(HttpSession session, Model model) {
		sessuserid = (String) session.getAttribute("userid");
		System.out.println("==============" + sessuserid);
		//TODO测试完删除
		sessuserid = "0042dd84ff4d4246a0e3d06095392a86";
		if (StringUtils.isNotBlank(sessuserid)) {
			log.debug("BaseController", sessuserid);
			System.out.println("==========e3333====" + sessuserid);
			//sessuser = baseUser.findById(sessuserid);
		} else {
			//sessuser = new User();
		}
		//model.addAttribute("sessuser", sessuser);
		log.debug("BaseController", "running...");
	}

	@ModelAttribute
	public void input_init(Model model, 
			@RequestParam(value = "communityid", required = false) String communityid,
			@RequestParam(value = "courseid", required = false) String courseid,
			@RequestParam(value = "lessonid", required = false) String lessonid,
			@RequestParam(value = "eventid", required = false) String eventid,
			@RequestParam(value = "taskid", required = false) String taskid,
			@RequestParam(value = "cmcg", required = false) String cmcg,
			@RequestParam(value = "syscates", required = false) Set<String> syscates,
			@RequestParam(value = "cmcates", required = false) Set<String> cmcates,
			@RequestParam(value = "tags", required = false) Set<String> tags,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "orderfield", required = false) String orderfield,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "pagesize", defaultValue = "20", required = false) Integer pagesize,
			@RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
		System.out.println("======community2========" + sessuserid);
		this.communityid = communityid;
		this.courseid = courseid;
		this.lessonid = lessonid;
		this.eventid = eventid;
		this.taskid = taskid;
		this.cmcg = cmcg;
		this.syscates = syscates;
		this.cmcates = cmcates;
		this.tags = tags;
		this.keyword = keyword;
		this.orderfield = orderfield;
		this.order = order;
		this.pagesize = pagesize;
		this.page = page;
	}
}
