package com.gsh.controller;

import com.gsh.pojo.Schedule;
import com.gsh.service.ScheduleService;
import com.gsh.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ScheduleController
 * Package: com.gsh
 * Description:
 *
 * @Author Gush
 * @Create 2024-02-01 16:43
 */
@RestController
@RequestMapping("schedule")
@Slf4j
@CrossOrigin // 允许跨域访问! 运行其他源访问我们的controller 浏览器不再拦截
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    /*
    * 表述层工作:
    *   1. 接收参数
    *   2. 响应结果
    * 具体的实现过程交给业务层
    */
    @GetMapping("/{pageSize}/{currentPage}")
    public R page(@PathVariable int pageSize, @PathVariable int currentPage) {
        R r = scheduleService.page(pageSize, currentPage);
        // slf4j 日志输出
        log.info("查询的数据为:{}",r);
        return r;
    }

    @DeleteMapping("/{id}")
    public R remove(@PathVariable int id) {
        R r = scheduleService.reomve(id);
        return r;
    }

    @PostMapping
    public R save(@Validated @RequestBody Schedule schedule, BindingResult result) {
        if(result.hasErrors()) {
            return R.fail("参数不能为空!");
        }
        R r = scheduleService.save(schedule);
        return r;
    }

    @PutMapping
    public R update(@Validated @RequestBody Schedule schedule, BindingResult result) {
        if(result.hasErrors()) {
            return R.fail("参数不能为空!");
        }
        R r = scheduleService.update(schedule);
        return r;
    }
}
