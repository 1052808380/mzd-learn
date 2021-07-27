package org.mzd.com.mzd.service;

import org.mzd.com.mzd.dao.UserRepository;
import org.mzd.com.mzd.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean verifyUser(User user) {
        List<User> userList = userRepository.findByNameAndPassword(user.getName(), user.getPassword());

        if (CollectionUtils.isEmpty(userList)) {
            return false;
        } else {
            return true;
        }
    }


    public User getUser(User user) {
       return userRepository.findByNameAndPassword(user.getName(), user.getPassword()).get(0);

    }


    public String registerUser(User user) {

        if (userRepository.findByName(user.getName()).isEmpty()) {
            userRepository.save(user);
            return "用户名  " + user.getName() + " 注册成功";

        } else {

            return "用户名 " + user.getName() + "已被占用！";
        }

    }
}
