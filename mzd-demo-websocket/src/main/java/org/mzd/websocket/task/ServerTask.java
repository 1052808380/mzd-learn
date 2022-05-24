package org.mzd.websocket.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import org.mzd.websocket.common.WebSocketConsts;
import org.mzd.websocket.model.Server;
import org.mzd.websocket.payload.ServerVO;
import org.mzd.websocket.util.ServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * 服务器定时推送任务
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-14 16:04
 */
@Component
public class ServerTask {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    @Autowired
    private SimpMessagingTemplate wsTemplate;

    /**
     * 按照标准时间来算，每隔 2s 执行一次
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void websocket() throws Exception {
        logger.info("【推送消息】开始执行：{}", DateUtil.formatDateTime(new Date()));
        // 查询服务器状态
        Server server = new Server();
        server.copyTo();
        ServerVO serverVO = ServerUtil.wrapServerVO(server);
        Dict dict = ServerUtil.wrapServerDict(serverVO);
        wsTemplate.convertAndSend(WebSocketConsts.PUSH_SERVER, JSONUtil.toJsonStr(dict));
        logger.info("【推送消息】执行结束：{}", DateUtil.formatDateTime(new Date()));
    }
}
