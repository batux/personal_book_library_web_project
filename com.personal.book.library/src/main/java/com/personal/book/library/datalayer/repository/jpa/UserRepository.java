package com.personal.book.library.datalayer.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personal.book.library.datalayer.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	public User findById(Long id);
	
	@Query(value = "FROM User u WHERE u.nickName = :userName")
	public List<User> findByUserName(@Param("userName") String userName, Pageable pageable);

}
