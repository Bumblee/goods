package com.model.goodss.service;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.model.goodss.client.UserService;
import com.model.goodss.dao.CommentDao;
import com.model.goodss.dao.GoodssDao;
import com.model.goodss.domain.Comment;
import com.model.goodss.domain.Goodss;
import com.model.goodss.util.MyPage;
import com.model.goodss.util.ServiceException;

@Component
@Transactional
public class CommentSercive {
	@Autowired
	private GoodssDao goodssDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private UserService userService;
	
	public MyPage<Comment> findCommentById(String goodssid, String keyword, int pagesize, int page) {
		MyPage<Comment> ps = commentDao.findByGoodss(goodssid, keyword, pagesize, page);
		Iterator<Comment> it = ps.getItems().iterator();
		while (it.hasNext()) {
			Comment comment = it.next();
			if (StringUtils.isNotBlank(comment.getUserid()))
				comment.setUser(userService.findById(comment.getUserid()));
		}

		return ps;
	}
	
	public Integer saveComment(String sessuserid, String id, String content) {
		try {
			Goodss goodss = goodssDao.findById(id);
			if (null != goodss) {
			} else {
				throw new ServiceException("comment", "goodss.notexist");
			}

			Comment comment = new Comment(sessuserid, goodss, content);
			commentDao.save(comment);
			commentDao.setEmbed(id, "no");
			commentDao.setEmbed(id, 3, "yes");
			return goodss.getCmcount();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void refreshCmcount(String id) {
		goodssDao.refreshCmcount(id);
	}

}
