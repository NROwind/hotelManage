package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.hotel.domain.Log;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMapper extends BaseMapper<Log> {
}
