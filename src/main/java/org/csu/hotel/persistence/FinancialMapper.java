package org.csu.hotel.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.hotel.domain.Financial;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialMapper extends BaseMapper<Financial> {
}
