package org.mzd.deploy.dao;

import org.apache.ibatis.annotations.Param;

/**
 */
public interface UserBaseInfoMapper {



    String getById(@Param("userId") Long userId);





}
