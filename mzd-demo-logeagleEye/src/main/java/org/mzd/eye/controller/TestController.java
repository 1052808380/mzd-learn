package org.mzd.eye.controller;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 控制层
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2020/03/13 16:14
 */
@RestController
@RequestMapping("/api/eye")
public class TestController {

    private final AtomicLong counter = new AtomicLong(0);

    /**
     * Request http://localhost:8080/httpclient?name=
     * @param name name
     * @return Map of Result
     */
    @RequestMapping("/httpclient")
    public Map<String, Object> greeting(@RequestParam(value = "name", defaultValue = "httpclient") String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", counter.incrementAndGet());
        map.put("name", name);
        return map;
    }
    /**
     * Request http://localhost:8080/httpclient?name=
     * @param name name
     * @return Map of Result
     */
    @RequestMapping("/httpclient1")
    public Map<String, Object> httpclient1(@RequestParam(value = "name", defaultValue = "httpclient") String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", counter.incrementAndGet());
        map.put("name", name);
        return map;
    }


}
