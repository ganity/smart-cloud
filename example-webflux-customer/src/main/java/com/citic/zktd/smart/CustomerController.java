package com.citic.zktd.smart;

import com.citic.zktd.smart.entry.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	

	@Autowired
	private AccountApi accountApi;

	@GetMapping("/flux/{customer}")
	public Flux<User> findByCustomer(@PathVariable("customer") String customerId) {
		//feign用不了
		LOGGER.info("findByCustomer: customerId={}", customerId);
//		User user = new User();
//		user.setUserAccount("11232321");
//		user.setUserName(customerId);
//		return Flux.just(user);
		return Flux.just(accountApi.findByAccount(customerId));
	}

	@GetMapping("/{id}")
	public Mono<User> findById(@PathVariable("id") String id) {
		LOGGER.info("findById: id={}", id);
		return Mono.just(accountApi.findById(id));
	}


	
}