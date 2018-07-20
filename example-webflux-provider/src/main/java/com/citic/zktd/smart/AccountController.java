package com.citic.zktd.smart;

import com.citic.zktd.smart.entry.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@GetMapping("/{id}")
	public User findById(@PathVariable("id") String id) {
		LOGGER.info("findById: id={}", id);
		User user = new User();
		user.setUserAccount("findByid");
		user.setUserName(id);
		return user;
	}

	@GetMapping("/flux/{customer}")
	public User findByAccount(@PathVariable("customer") String customerId) {
		LOGGER.info("findByCustomer: customerId={}", customerId);
		User user = new User();
		user.setUserAccount("11232321");
		user.setUserName(customerId);
		return user;
	}

	//webflux 无法与feign整合
//	@GetMapping("/{id}")
//	public Mono<User> findById(@PathVariable("id") String id) {
//		LOGGER.info("findById: id={}", id);
//		User user = new User();
//		user.setUserAccount("findByid");
//		user.setUserName(id);
//		return Mono.just(user);
//	}
//
//	@GetMapping("/flux/{customer}")
//	public Flux<User> findByAccount(@PathVariable("customer") String customerId) {
//		LOGGER.info("findByCustomer: customerId={}", customerId);
//		User user = new User();
//		user.setUserAccount("11232321");
//		user.setUserName(customerId);
//		return Flux.just(user);
//	}


	
}