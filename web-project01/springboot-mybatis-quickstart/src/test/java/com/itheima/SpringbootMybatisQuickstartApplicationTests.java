package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest //SpringBoot单元测试的注解 -当前测试类中的测试方法运行时，会启动SpringBoot项目 -IOC容器
class SpringbootMybatisQuickstartApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll(){
        List<User> userList = userMapper.findAll();
        //userList.forEach(user -> System.out.println(user)); 下条语句为lamada表达式
        userList.forEach(System.out::println);
    }

    @Test
    public void testDeleteById(){
        Integer i = userMapper.deleteById(4);
        System.out.println("执行完毕后影响的记录数："+i);
    }

    @Test
    public void testInsert(){
        User user = new User(null,"gaoyuanyuan","666888","高圆圆",18);
        userMapper.insert(user);
    }

    @Test
    public void testUpdate(){
        User user = new User(1,"zhouyu","666888","周瑜",20);
        userMapper.update(user);
    }

    @Test
    public void testFindByUsernameAndPassword(){

        userMapper.findByUsernameAndPassword("zhouyu","666888");
    }

}
