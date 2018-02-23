package com.ddframe.rest.user;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class UserRepository  {
	@Cacheable("users")
	public User getById(Long id) {
		System.out.println("UserRepository.getById");
		simulateSlowService();
		return new User(id);
	}

	private void simulateSlowService() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
