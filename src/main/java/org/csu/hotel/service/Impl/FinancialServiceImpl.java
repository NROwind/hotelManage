package org.csu.hotel.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.csu.hotel.domain.Finance;
import org.csu.hotel.persistence.FinancialMapper;
import org.csu.hotel.service.FinancialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FinancialServiceImpl extends ServiceImpl<FinancialMapper, Finance> implements FinancialService {
    

}
