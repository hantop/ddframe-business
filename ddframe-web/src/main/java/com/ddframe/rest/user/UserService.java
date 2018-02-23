package com.ddframe.rest.user;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.ddframe.exception.ResourceNotFoundException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@CacheConfig(cacheNames = "users")
class UserService {
	private final Map<Long, User> data = new ConcurrentHashMap<>();
	/*@Autowired
	private CacheManager cacheManager;*/
	@Autowired
	private UserRepository usersRepository;

	Flux<User> list() {
		return Flux.fromIterable(this.data.values());
	}

	Flux<User> getById(final Flux<String> ids) {
		return (Flux<User>)ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
	}

	Mono<User> getById(final Long id) {
		System.out.println(id);
		return Mono.justOrEmpty(this.usersRepository.getById(id)).switchIfEmpty(Mono.error(new ResourceNotFoundException()));
	}

	Flux<User> createOrUpdate(final Flux<User> users) {
		return null;//users.doOnNext(user -> this.usersRepository.save(user));
	}

	Mono<User> createOrUpdate(final User user) {
		this.data.put(user.getId(), user);
		return Mono.just(user);
	}

	void delete(final Long id) {
		//this.usersRepository.deleteById(id);
	}
}