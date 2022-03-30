package edu.sgu.delicatessen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sgu.delicatessen.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    Optional<User> findByUsername(String username);
}
