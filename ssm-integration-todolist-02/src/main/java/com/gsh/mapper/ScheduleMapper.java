package com.gsh.mapper;

import com.gsh.pojo.Schedule;
import com.gsh.utils.PageBean;

import java.security.PublicKey;
import java.util.List;

/**
 * ClassName: ScheduleMapper
 * Package: com.gsh.mapper
 * Description:
 *
 * @Author Gush
 * @Create 2024-02-01 16:50
 */

public interface ScheduleMapper {

    public List<Schedule> queryList();

    public int deleteById(int id);

    public int insert(Schedule schedule);

    public int update(Schedule schedule);
}
