package org.smart4j.chapter3.service;

import java.util.List;
import java.util.Map;

import org.smart4j.chapter3.model.customer;
import scs.tju.framework.annotation.Service;
import scs.tju.framework.annotation.Transaction;
import scs.tju.framework.helper.DatabaseHelper;

/**
 * 提供客户数据服务
 */
@Service
public class CustomerService {

    /**
     * 获取客户列表
     */
    public List<customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(customer.class, sql);
    }

    /**
     * 获取客户
     */
    public customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return DatabaseHelper.queryEntity(customer.class, sql, id);
    }

    /**
     * 创建客户
     */
    @Transaction
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(customer.class, fieldMap);
    }

    /**
     * 更新客户
     */
    @Transaction
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(customer.class, id, fieldMap);
    }

    /**
     * 删除客户
     */
    @Transaction
    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(customer.class, id);
    }
}
