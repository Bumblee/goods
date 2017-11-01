package com.model.goodss.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.goodss.domain.User;
import com.model.goodss.util.Line;

@Service
public class UserService {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserClient userClient;

	public User findById(String id) {
		try {
			return userClient.findById(id);
		} catch (Exception e) {
			log.error(e.getMessage()+",at "+Line.getNumber()+" line.");
			return new User();
		}
	}

}
