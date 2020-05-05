package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.common.util.ParameterUtil;
import com.ruoyi.project.storage.domain.Customer;
import com.ruoyi.project.storage.mapper.CustomerMapper;
import com.ruoyi.project.storage.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :lihao
 * @date :2020/04/30
 * @description :客户service接口实现类
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    /**
     * 用户Mapper接口
     */
    private final CustomerMapper customerMapper;

    /**
     * 通过构造方法注入
     * @param customerMapper
     */
    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    /**
     * 用户列表（分页）
     *
     * @param customer
     * @return 用户列表
     */
    @Override
    public List<Customer> queryCustomerList(Customer customer) {
        // 返回查询结果
        return customerMapper.queryCustomerList(customer);
    }

    /**
     * 用户新增
     * @param customer
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer) {
        // 设置创建时间
        customer.setCreateTime(DateUtils.getNowDate());
        // 设置创建人
        customer.setCreateBy(SecurityUtils.getUsername());
        // 设置初始密码
        customer.setPassword(SecurityUtils.encryptPassword("12345678"));
        // 设置启用
        customer.setStatus("0");
        // 设置类型
        customer.setUserType("02");
        // 设置版本号
        customer.setVersion(0L);
        // 设置未删除
        customer.setDelFlag("0");
        // 返回插入条数
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 用户编辑
     *
     * @param customer
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer) {
        // 设置更新时间
        customer.setUpdateTime(DateUtils.getNowDate());
        // 设置更新人
        customer.setUpdateBy(SecurityUtils.getUsername());
        // 修改条数
        int count = customerMapper.updateCustomer(customer);
        // 乐观锁判断
        if (count == 0){
            log.error("AdvertisementServiceImpl.updateAdvertisement failed: 乐观锁");
            // 抛出异常标记乐观锁
            throw new CustomException("用户信息已被他人率先修改，请刷新后重试", HttpStatus.ERROR);
        }
        // 返回修改条数
        return count;
    }

    /**
     * 用户删除 : 通过ID批量操作 （0代表存在 2代表删除）
     *
     * @param ids
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Long[] ids) {
        // 返回操作条数
        return customerMapper.deleteCustomerByIds(ParameterUtil.getBatchUpdateMapByIds(ids));
    }

    /**
     * 用户启用/停用 :
     *
     * @param operate  帐号状态（0正常 1停用）
     * @param ids
     * @return 结果
     */
    @Override
    public int operateCustomerByIds(String operate, Long[] ids) {
        // 返回操作条数
        return customerMapper.operateCustomerByIds(ParameterUtil.getBatchUpdateMapByOperateIds(operate, ids));
    }

    /**
     * 重置用户密码 : 重置为初始密码【Abc,123456】
     *
     * @param ids
     * @return
     */
    @Override
    public int resetCustomerPassword(Long[] ids) {
        // 返回操作条数
        return customerMapper.resetCustomerPassword(ParameterUtil.getBatchUpdateMapByOperateIds(SecurityUtils.encryptPassword("Abc,123456"),ids));
    }
}