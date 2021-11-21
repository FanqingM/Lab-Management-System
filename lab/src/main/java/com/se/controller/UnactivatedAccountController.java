package com.se.controller;

import com.se.dto.TeacherDTO;
import com.se.dto.UnactivatedAccountDTO;
import com.se.entity.AdministratorAccount;
import com.se.entity.UnactivatedAccount;
import com.se.service.UnactivatedAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("unactivatedAccount")
public class UnactivatedAccountController {
    @Autowired
    private UnactivatedAccountService unactivatedAccountService;

    @GetMapping("findAll")
    public List<UnactivatedAccountDTO> selectAllUnactivatedAccounts() {
        List<UnactivatedAccountDTO> res = unactivatedAccountService.selectAllUnactivatedAccounts();
        return res;
    }

    @GetMapping("activate")
    public int activateAccount(String id) {
        int res = unactivatedAccountService.activateAccount(id);
        return res;
    }
    @GetMapping("findOne")
    public UnactivatedAccount findOne(String id) {
        UnactivatedAccount unactivatedAccount = unactivatedAccountService.selectByPrimaryKey(id);
        return unactivatedAccount;
    }

    @PostMapping("/add")
    public String add(@RequestBody UnactivatedAccount unactivatedAccount){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = unactivatedAccountService.insert(unactivatedAccount);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/update")
    public UnactivatedAccount updateUnactivatedAccount(@RequestBody UnactivatedAccount unactivatedAccount){
        unactivatedAccountService.updateByPrimaryKey(unactivatedAccount);
        //回到员工列表页面
        return unactivatedAccount;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUnactivatedAccount(@PathVariable("id")String id){
        //根据id删除员工
        unactivatedAccountService.deleteByPrimaryKey(id);
        return id.toString();
    }
}
