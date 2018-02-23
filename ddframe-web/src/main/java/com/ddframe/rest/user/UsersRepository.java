package com.ddframe.rest.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsersRepository extends CrudRepository<User, Long> {
	List<User> findByName(String name);
}
