package com.personal.book.library.servicelayer;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.personal.book.library.datalayer.entity.User;
import com.personal.book.library.datalayer.entity.UserRoleList;
import com.personal.book.library.datalayer.repository.jpa.RoleListRepository;
import com.personal.book.library.datalayer.repository.jpa.UserRepository;
import com.personal.book.library.servicelayer.exception.ServiceLayerException;
import com.personal.book.library.util.CryptoUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleListRepository roleListRepository;
	
	
	@Transactional(rollbackFor = { SQLException.class, ServiceLayerException.class })
	public Long createUser(User user) {
		
		user.setPassword(CryptoUtil.cryptPassword(user.getPassword()));
		user.setOtpSecret(CryptoUtil.generateOtpSecret());
		user.setCreateDate(new Date());
		user = userRepository.save(user);
		
		if(user.getId() == null || !(user.getId() > 0)) {
			throw new ServiceLayerException("USER-REGISTRATION-ERROR", "User could not be saved into database!");
		}
		
		return user.getId();
	}
	
	@Transactional(rollbackFor = { SQLException.class })
	public List<UserRoleList> prepareActiveRoleList(Long userId) {
		
		return roleListRepository.findActiveRoleListByUserId(userId);
	}
	
	public String generateOTPProtocol(Long userId) {
		
        User user = userRepository.findById(userId);
        return String.format("otpauth://totp/%s:%s?secret=%s&issuer=SpringBootTOTP", user.getNickName(), user.getNickName() + "@domain.com", user.getOtpSecret());
    }
	
}
