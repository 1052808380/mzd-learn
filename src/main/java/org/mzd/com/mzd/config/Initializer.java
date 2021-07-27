package org.mzd.com.mzd.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author: zhendong.mzd
 * @description:
 * @date: 2021/7/9 下午1:54
 */
public class Initializer extends AbstractHttpSessionApplicationInitializer {

    public Initializer() {
        super(SessionConfig.class);
    }

}