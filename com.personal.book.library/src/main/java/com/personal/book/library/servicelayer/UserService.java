package com.personal.book.library.servicelayer;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.book.library.datalayer.entity.User;
import com.personal.book.library.datalayer.repository.jpa.UserRepository;
import com.personal.book.library.servicelayer.exception.ServiceLayerException;
import com.personal.book.library.util.CryptoUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional(rollbackFor = { SQLException.class, ServiceLayerException.class })
	public Long createUser(User user) {
		
		user.setPassword(CryptoUtil.cryptPassword(user.getPassword()));
		user = userRepository.save(user);
		
		if(user.getId() == null || !(user.getId() > 0)) {
			throw new ServiceLayerException("USER-REGISTRATION-ERROR", "User could not be saved into database!");
		}
		
		return user.getId();
	}
	
}
