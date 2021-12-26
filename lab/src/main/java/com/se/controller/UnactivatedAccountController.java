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
@RequestMapping("UnactivatedAccount")
public class UnactivatedAccountController {
    @Autowired
    private UnactivatedAccountService unactivatedAccountService;

    @GetMapping("findAllUnactivatedAccount")
    public List<UnactivatedAccountDTO> selectAllUnactivatedAccounts() {
        List<UnactivatedAccountDTO> res = unactivatedAccountService.selectAllUnactivatedAccounts();
        return res;
    }

    @GetMapping("activateUnactivatedAccount")
    public int activateAccount(String id) {
        int res = unactivatedAccountService.activateAccount(id);
        return res;
    }
    @GetMapping("findOneUnactivatedAccount")
    public UnactivatedAccount findOne(String id) {
        UnactivatedAccount unactivatedAccount = unactivatedAccountService.selectByPrimaryKey(id);
        return unactivatedAccount;
    }

    @PostMapping("/addUnactivatedAccount")
    public String add(@RequestBody UnactivatedAccount unactivatedAccount){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = unactivatedAccountService.insert(unactivatedAccount);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/updateUnactivatedAccount")
    public UnactivatedAccount updateUnactivatedAccount(@RequestBody UnactivatedAccount unactivatedAccount){
        unactivatedAccountService.updateByPrimaryKey(unactivatedAccount);
        //回到员工列表页面
        return unactivatedAccount;
    }

    @DeleteMapping("/deleteUnactivatedAccount/{id}")
    public String deleteUnactivatedAccount(@PathVariable("id")String id){
        //根据id删除员工
        unactivatedAccountService.deleteByPrimaryKey(id);
        return id.toString();
    }
}
