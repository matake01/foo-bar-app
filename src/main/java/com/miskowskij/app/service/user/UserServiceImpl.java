package com.miskowskij.app.service.user;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miskowskij.app.persistence.user.User;
import com.miskowskij.app.persistence.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {
 
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDTO addUser(UserDTO newUserEntity) {		
    	User user = new User();
    	user.setUsername(newUserEntity.getUsername());
    	user.setFirstname(newUserEntity.getFirstname());
    	user.setLastname(newUserEntity.getLastname());
    	
    	user = userRepository.save(user);
    	
    	return UserMapper.mapEntityIntoDTO(user);
    }
    

    /*@Transactional
    public void addRoleToAllUsers(String roleName) {

      Role role = roleRepository.findByName(roleName);

      for (User user : userRepository.findAll()) {
        user.addRole(role);
        userRepository.save(user);
      }
    }*/
    
}