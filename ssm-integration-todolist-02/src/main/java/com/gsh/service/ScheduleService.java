package com.gsh.service;

import com.gsh.pojo.Schedule;
import com.gsh.utils.R;

/**
 * ClassName: ScheduleService
 * Package: com.gsh.service
 * Description:
 *
 * @Author Gush
 * @Create 2024-02-01 16:49
 */
public interface ScheduleService {

    R page(int pageSize, int currentPage);

    R reomve(int id);

    R save(Schedule schedule);

    R update(Schedule schedule);
}
