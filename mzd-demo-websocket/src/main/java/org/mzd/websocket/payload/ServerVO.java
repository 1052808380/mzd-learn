package org.mzd.websocket.payload;

import com.alibaba.acm.shaded.com.google.common.collect.Lists;
import org.mzd.websocket.model.Server;
import org.mzd.websocket.payload.server.*;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务器信息VO
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-14 17:25
 */
public class ServerVO implements Serializable {
    List<CpuVO> cpu = Lists.newArrayList();
    List<JvmVO> jvm = Lists.newArrayList();
    List<MemVO> mem = Lists.newArrayList();
    List<SysFileVO> sysFile = Lists.newArrayList();
    List<SysVO> sys = Lists.newArrayList();

    @PostConstruct
    public ServerVO create(Server server) {
        cpu.add(CpuVO.create(server.getCpu()));
        jvm.add(JvmVO.create(server.getJvm()));
        mem.add(MemVO.create(server.getMem()));
        sysFile.add(SysFileVO.create(server.getSysFiles()));
        sys.add(SysVO.create(server.getSys()));
        return null;
    }

    public List<CpuVO> getCpu() {
        return cpu;
    }

    public void setCpu(List<CpuVO> cpu) {
        this.cpu = cpu;
    }

    public List<JvmVO> getJvm() {
        return jvm;
    }

    public void setJvm(List<JvmVO> jvm) {
        this.jvm = jvm;
    }

    public List<MemVO> getMem() {
        return mem;
    }

    public void setMem(List<MemVO> mem) {
        this.mem = mem;
    }

    public List<SysFileVO> getSysFile() {
        return sysFile;
    }

    public void setSysFile(List<SysFileVO> sysFile) {
        this.sysFile = sysFile;
    }

    public List<SysVO> getSys() {
        return sys;
    }

    public void setSys(List<SysVO> sys) {
        this.sys = sys;
    }
}
