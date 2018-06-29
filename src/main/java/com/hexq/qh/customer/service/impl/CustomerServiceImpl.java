package com.hexq.qh.customer.service.impl;

import com.hexq.qh.customer.model.Customer;
import com.hexq.qh.customer.mapper.CustomerMapper;
import com.hexq.qh.customer.service.CustomerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 客户 ServiceImpl
 * @author hexq
 * @date 2018-06-06
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Override
    public String getCustomerYysId(String customerId) {
        Customer customer = selectById(customerId);
        return customer.getUserId();
    }
}
