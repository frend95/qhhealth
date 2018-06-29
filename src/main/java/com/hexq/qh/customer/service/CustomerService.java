package com.hexq.qh.customer.service;

import com.hexq.qh.customer.model.Customer;
import com.baomidou.mybatisplus.service.IService;

/**
 * 客户 Service
 * @author hexq
 * @date 2018-06-06
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 获取客户的营养师id
     * @param customerId 客户id
     * @return 营养师id
     */
    String getCustomerYysId(String customerId);
}
