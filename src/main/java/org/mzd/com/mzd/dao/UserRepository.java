package org.mzd.com.mzd.dao;

import org.mzd.com.mzd.domain.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository  {

     List<User> findByName(@Param("name")  String name);
    
     List<User> findByNameAndPassword(@Param("name") String name,@Param("password")  String password);

    int save(User user);

}