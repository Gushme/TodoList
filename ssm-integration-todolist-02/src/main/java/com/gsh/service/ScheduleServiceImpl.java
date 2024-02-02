package com.gsh.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gsh.mapper.ScheduleMapper;
import com.gsh.pojo.Schedule;
import com.gsh.utils.PageBean;
import com.gsh.utils.R;
import jakarta.enterprise.inject.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ScheduleServiceImpl
 * Package: com.gsh.service
 * Description:
 *
 * @Author Gush
 * @Create 2024-02-01 16:49
 */
@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public R page(int pageSize, int currentPage) {
        /*
        * 需要的工作
        *   1. 分页
        *   2. 查询
        *   3. 分页数据装配
        * */
        // 1. 分页
        PageHelper.startPage(currentPage,pageSize); // 会在sql语句后面加limit x,y
        // 2. 查询
        List<Schedule> scheduleList = scheduleMapper.queryList();
        // 3. 分页数据装配
        PageInfo<Schedule> info = new PageInfo<>(scheduleList);
        // 装配pageBean
        PageBean<Schedule> pageBean = new PageBean<>(currentPage, pageSize, info.getTotal(), info.getList());

        // 装配R
        R ok = R.ok(pageBean);

        return ok;
    }

    @Override
    public R reomve(int id) {
        int rows = scheduleMapper.deleteById(id);
        if(rows > 0) {
            return R.ok(null);
        }
        return R.fail(null);
    }

    @Override
    public R save(Schedule schedule) {
        int rows = scheduleMapper.insert(schedule);
        if(rows > 0) {
            return R.ok(null);
        }
        return R.fail(null);
    }

    @Override
    public R update(Schedule schedule) {
        // 判断id不能为null
        if(schedule.getId() == null) {
            return R.fail("id参数为空，无法修改");
        }

        int rows = scheduleMapper.update(schedule);
        if(rows > 0) {
            return R.ok(null);
        }
        return R.fail(null);
    }
}
