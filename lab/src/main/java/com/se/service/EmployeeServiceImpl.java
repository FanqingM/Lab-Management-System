package com.se.service;

import com.se.entity.Employee;
import com.se.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: EmployeeServiceImpl
 * @Description: TODO
 * @Auther: wangqiang
 * @Date: 2020/2/25 20:14
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> selectAllEmployee() {
        return employeeMapper.selectAllEmployee();
    }

    @Override
    public Employee selectEmployeeById(int id) {
        return employeeMapper.selectEmployeeById(id);
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeMapper.addEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public int deleteEmployee(int id) {
        return employeeMapper.deleteEmployee(id);
    }
}
