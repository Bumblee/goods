package com.model.goodss.web;

import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.goodss.domain.Goodss;
import com.model.goodss.service.GoodssService;
import com.model.goodss.util.MyPage;
import com.model.goodss.util.Result;

@Controller
public class GoodssController extends BaseController{
	@Autowired
	private GoodssService goodssService;
	
	@PostMapping("/goodss")
	@ResponseBody
	public Map<String, Object> create(@Valid GoodssForm goodssForm){
		goodssService.save(sessuserid, goodssForm);
		return Result.success();
	}
	
	@GetMapping("/goodss")
	public String add(){
		return "add";
	}
	
	@PostMapping("/goodss/{id}/delete")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable String id){
		goodssService.delete(sessuserid, id);
		return Result.success();
	}
	
	@PostMapping("/goodss/{id}")
	@ResponseBody
	public Map<String, Object> update(@PathVariable String id, @Valid GoodssForm goodssForm){
		goodssService.update(sessuserid, id, goodssForm);
		return Result.success();
	}
	
	@GetMapping("/goodss/{id}/edit")
	public String update(@PathVariable String id, Model model){
		Goodss goodss = goodssService.findById(sessuserid, id);
		model.addAttribute("goodss", goodss);
		return "edit";
	}
	
	@GetMapping("/goodss/{id}/detail")
	public String detail(@PathVariable String id, Model model){
		Goodss goodss = goodssService.findById(sessuserid, id);
		model.addAttribute("goodss", goodss);
		return "detail";
	}
	
}
