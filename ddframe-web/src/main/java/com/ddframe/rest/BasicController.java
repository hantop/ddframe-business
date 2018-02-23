package com.ddframe.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class BasicController {

	@GetMapping("/hello_world")
	public Mono<String> sayHelloWorld() {
		return Mono.just("Hello World");
	}

}
