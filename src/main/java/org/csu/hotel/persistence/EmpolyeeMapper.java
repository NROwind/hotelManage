package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.hotel.domain.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpolyeeMapper extends BaseMapper<Employee> {
}
