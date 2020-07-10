package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.hotel.domain.Stay;
import org.springframework.stereotype.Repository;

@Repository
public interface StayMapper extends BaseMapper<Stay> {
}
