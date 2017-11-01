package com.model.goodss.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.model.goodss.domain.User;


@FeignClient(value = "ykuser", fallback = UserClientFallback.class)
public interface UserClient {
	
	@GetMapping("/user/{userid}")
	public User findById(@PathVariable("userid") String userid);
	
	
}

@Component 
class UserClientFallback implements UserClient {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public User findById(String userid) {
		log.info("failed......."+userid);
		return new User();
	}
	
}
