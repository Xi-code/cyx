package com.itheima.controller;

import cn.hutool.core.io.IoUtil;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Title: UserController
 * @Author 曦
 * @Date 2025/3/19 19:27
 * @description:
 */

@RestController
//用到@ResponseBody注解
// 作用：将controller返回值直接作为响应体的数据直接响应；返回值是对象、集合-->json->响应
public class UserController {
    //方式1：属性注入
//    @Autowired //应用程序运行时会自动的查找该类型的bean对象 并赋值给该成员变量
//    private UserService userService ;
    //方式2：构造器注入
//    private final UserService userService;
//    //@Autowired -->如果当前类中只存在一个构造函数，@Autowired可以省略
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
    //方式3：setter注入
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /* @RequestMapping("/list")
            public List<User> list() throws FileNotFoundException {
                //1.加载并读取user.txt文件  获取用户数据
                //InputStream in = new FileInputStream(new File("src/main/resources/user.txt")); 不好 不推荐
                InputStream in = getClass().getClassLoader().getResourceAsStream("user.txt");
                ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8,new ArrayList<>());
                //2.解析用户信息，封装为User对象 ->list集合
                List<User> userList =lines.stream().map(line ->{
                    String[] parts = line.split(",");
                    Integer id = Integer.parseInt(parts[0]);
                    String username = parts[1];
                    String password = parts[2];
                    String name = parts[3];
                    Integer age = Integer.parseInt(parts[4]);
                    LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    return new User(id,username,password,name,age,updateTime);

                }).toList();
                //3.返回数据(json)
                return userList;
            }*/
   @RequestMapping("/list")
   public List<User> list() throws FileNotFoundException {
       //1.调用service 获取数据
       List<User> userList = userService.findAll();
       //2.返回数据(json)
       return userList;
   }
}
