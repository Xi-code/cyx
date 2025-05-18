package com.itheima;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @Title: UserServiceTest
 * @Author 曦
 * @Date 2025/3/18 22:10
 * @description:
 */
@DisplayName("用户信息测试类")
public class UserServiceTest {
    /*@BeforeAll//测试类执行前只执行一次
    public static void beforeAll(){
        System.out.println("beforeAll");
    }
    @AfterAll//测试类执行后只执行一次
    public static void afterAll(){
        System.out.println("afterAll");
    }
    @BeforeEach
    public void beforeEach(){
        System.out.println("beforeEach");
    }
    @AfterEach
    public void afterEach(){
        System.out.println("afterEach");
    }*/
    @Test
    public void testGetAge(){
        UserService userService = new UserService();
        Integer age = userService.getAge("100000200010011011");
        System.out.println(age);
    }

    @Test
    public void testGenderWithAssert(){
        UserService uservice = new UserService();
        String gender = uservice.getGender("100000200010011011");

        // 断言
        Assertions.assertEquals("男", gender);
    }

    @DisplayName("测试用户性别")
    @ParameterizedTest
    @ValueSource(strings = {"100000200010011011", "100000200010011012"})
    public void testGetGender2(String idCard){
        UserService uservice = new UserService();
        String gender = uservice.getGender(idCard);
        Assertions.assertEquals("男", gender);
    }
}
