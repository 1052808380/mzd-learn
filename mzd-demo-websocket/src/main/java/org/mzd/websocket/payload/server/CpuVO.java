package org.mzd.websocket.payload.server;

import com.alibaba.acm.shaded.com.google.common.collect.Lists;
import lombok.Data;
import org.mzd.websocket.model.server.Cpu;
import org.mzd.websocket.payload.KV;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * CPU相关信息实体VO
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-14 17:27
 */
@Data
public class CpuVO implements Serializable {
    List<KV> data = Lists.newArrayList();

    public static CpuVO create(Cpu cpu) {
        CpuVO vo = new CpuVO();
        vo.data.add(new KV("核心数", cpu.getCpuNum()));
        vo.data.add(new KV("CPU总的使用率", cpu.getTotal()));
        vo.data.add(new KV("CPU系统使用率", cpu.getSys() + "%"));
        vo.data.add(new KV("CPU用户使用率", cpu.getUsed() + "%"));
        vo.data.add(new KV("CPU当前等待率", cpu.getWait() + "%"));
        vo.data.add(new KV("CPU当前空闲率", cpu.getFree() + "%"));
        return vo;
    }
}
