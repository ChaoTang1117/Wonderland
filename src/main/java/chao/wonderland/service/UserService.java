package chao.wonderland.service;

import chao.wonderland.bo.User;
import chao.wonderland.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

}
