package com.softserve.actent.repository;

import com.softserve.actent.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findUserById(Long id);

    List<User> findAll();

    User deleteUserById(Long id);

}
