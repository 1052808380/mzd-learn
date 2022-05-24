package org.mzd.websocket.util;

import cn.hutool.core.lang.Dict;
import org.mzd.websocket.model.Server;
import org.mzd.websocket.payload.ServerVO;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * 服务器转换工具类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-17 10:24
 */
public class ServerUtil {
    /**
     * 包装成 ServerVO
     *
     * @param server server
     * @return ServerVO
     */
    public static ServerVO wrapServerVO(Server server) {
        ServerVO serverVO = new ServerVO();
        serverVO.create(server);
        return serverVO;
    }

    /**
     * 包装成 Dict
     *
     * @param serverVO serverVO
     * @return Dict
     */
    @Bean
    public static Dict wrapServerDict(ServerVO serverVO) {
        Dict dict = Dict.create().set("cpu", serverVO.getCpu().get(0)).set("mem", serverVO.getMem().get(0)).set("sys", serverVO.getSys().get(0)).set("jvm", serverVO.getJvm().get(0)).set("sysFile", serverVO.getSysFile().get(0));
        return dict;
    }
}
