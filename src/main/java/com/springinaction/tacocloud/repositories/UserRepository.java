package com.springinaction.tacocloud.repositories;

import com.springinaction.tacocloud.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
