package com.personal.book.library.datalayer.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personal.book.library.datalayer.entity.UserRoleList;

@Repository
public interface RoleListRepository extends CrudRepository<UserRoleList, Long>{
	
	public UserRoleList findById(Long id);

	@Query(value = "FROM UserRoleList url WHERE url.userId = :userId and url.status = true ORDER BY url.priority ASC")
	public List<UserRoleList> findActiveRoleListByUserId(@Param("userId") Long userId);
}
