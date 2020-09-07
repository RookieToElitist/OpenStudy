package com.study.responsejsondemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")

public class TestController {
    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    //测试使用 @ResponseResultBody
    @ResponseResultBody
    @GetMapping("/info")
    public Map<String, Object> info() {
        return INFO;
    }

    //不使用 @ResponseResultBody
    @GetMapping("/info1")
    public Map<String, Object> info1() {
        return INFO;
    }

    @GetMapping("/infoPlus")
    @ResponseBody
    public Result<Map<String, Object>> infoPlus() {
        return Result.success(INFO);
    }

    @GetMapping("error")
    public HashMap<String, Object> error() throws Exception {
        throw new Exception("helloError");
    }
    @GetMapping("MyError")
    public HashMap<String, Object> MyError() throws Exception {
        throw new ResultException(ResultStatus.BAD_REQUEST);
    }
}
