package com.cyx.controller;
import com.cyx.common.Result;
import com.cyx.common.RoleEnum;
import com.cyx.entity.Account;
import com.cyx.entity.Admin;
import com.cyx.entity.User;
import com.cyx.service.AdminService;
import com.cyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Title: WebController
 * @Author  曦
 * @Date  2025/6/4 12:32
 * @description: 
*/


@RestController
public class WebController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        //用确定的值去equals不确定的值 否则可能会出现空指针异常
        if(RoleEnum.ADMIN.name().equals(account.getRole())){
            account = adminService.login(account);
        }else if(RoleEnum.USER.name().equals(account.getRole())){
            account = userService.login(account);
        }else{
            return Result.error("您的参数错误！");
        }
        return Result.success(account);
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if(RoleEnum.USER.name().equals(user.getRole())){
            userService.register(user);
        }else{
            return Result.error("您的参数错误！");
        }
        return Result.success();
    }
}

