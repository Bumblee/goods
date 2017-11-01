package com.model.goodss.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.goodss.domain.Comment;
import com.model.goodss.domain.Goodss;
import com.model.goodss.service.CommentSercive;
import com.model.goodss.service.GoodssService;
import com.model.goodss.util.MyPage;
import com.model.goodss.util.Result;

@Controller
public class IndexController extends BaseController{
	@Autowired
	private GoodssService goodssService;
	@Autowired
	private CommentSercive commentSercive;
	
	@GetMapping("/")
	public String index(Model model,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "pagesize", defaultValue = "3", required = false) int pagesize,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword
			) {
	MyPage<Goodss> goodss = goodssService.findAll(page, pagesize, keyword);
	for(Goodss goods : goodss.getItems()){
		for (String image : goods.getImages()) {
			System.out.println(image);
		}
		//System.out.println(goodss.getItems().get(i).getImage());
	}
	model.addAttribute("goodss", goodss);
	return "index";
	}
	
	@PostMapping("/goodss/{id}/comment")
	@ResponseBody
	public Map<String, Object> saveComment(@PathVariable String id, @Valid Comment comment) {
		commentSercive.saveComment(sessuserid, id, comment.getContent());
		commentSercive.refreshCmcount(id);
		return Result.success();
	}
	
	@GetMapping("/goodss/{id}/comments/{page}")
	public String loadByInfo(Model model, @PathVariable String id, @PathVariable Integer page) {
		model.addAttribute("comments", commentSercive.findCommentById(id, keyword, 3, page));
		return "comments";
	}
}
