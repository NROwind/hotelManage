package org.csu.hotel.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.hotel.domain.Finance;
import org.csu.hotel.persistence.FinanceMapper;
import org.csu.hotel.service.FinanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements FinanceService {
    

}
