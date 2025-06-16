package com.cyx.service;

import com.cyx.common.RoleEnum;
import com.cyx.entity.Admin;
import com.cyx.exception.CustomException;
import com.cyx.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

    @Mock
    private AdminMapper adminMapper;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ==================== 测试 login 方法 ====================

    @Test
    public void testLogin_Success() {
        // 准备数据
        String username = "admin";
        String password = "123456";

        Admin dbAdmin = new Admin();
        dbAdmin.setUsername(username);
        dbAdmin.setPassword(password);

        // 设置mock行为
        when(adminMapper.selectByUsername(username)).thenReturn(dbAdmin);

        // 执行测试
        Admin account = new Admin();
        account.setUsername(username);
        account.setPassword(password);

        Admin result = (Admin) adminService.login(account);

        // 验证结果
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
    }

    @Test
    public void testLogin_UserNotFound() {
        // 设置mock行为
        when(adminMapper.selectByUsername("unknownUser")).thenReturn(null);

        // 执行测试并断言异常
        Admin account = new Admin();
        account.setUsername("unknownUser");

        CustomException exception = assertThrows(CustomException.class,
                () -> adminService.login(account));

        // 验证异常信息
        assertEquals("账号不存在", exception.getMessage());
    }

    @Test
    public void testLogin_WrongPassword() {
        // 准备数据
        String username = "admin";

        Admin dbAdmin = new Admin();
        dbAdmin.setUsername(username);
        dbAdmin.setPassword("123456");

        // 设置mock行为
        when(adminMapper.selectByUsername(username)).thenReturn(dbAdmin);

        // 执行测试并断言异常
        Admin account = new Admin();
        account.setUsername(username);
        account.setPassword("wrongPass");

        CustomException exception = assertThrows(CustomException.class,
                () -> adminService.login(account));

        // 验证异常信息
        assertEquals("账号或者密码错误", exception.getMessage());
    }

    // ==================== 测试 add 方法 ====================

    @Test
    public void testAddAdmin_UsernameExists() {
        // 准备数据
        Admin admin = new Admin();
        admin.setUsername("admin_cyx");

        // 设置mock行为
        when(adminMapper.selectByUsername("admin_cyx")).thenReturn(new Admin());

        // 执行测试并断言异常
        CustomException exception = assertThrows(CustomException.class,
                () -> adminService.add(admin));

        // 验证异常信息
        assertEquals("账号已存在", exception.getMessage());
    }

    @Test
    public void testAddAdmin_DefaultPassword() {
        // 准备数据
        Admin admin = new Admin();
        admin.setUsername("newUser");

        // 设置mock行为
        when(adminMapper.selectByUsername("newUser")).thenReturn(null);

        // 执行测试
        adminService.add(admin);

        // 验证insert方法被调用，并且参数符合预期
        verify(adminMapper, times(1)).insert(argThat(a ->
                a.getUsername().equals("newUser") &&
                        a.getPassword().equals("admin") &&
                        a.getName().equals("newUser") &&
                        a.getRole().equals(RoleEnum.ADMIN.name())
        ));
    }

    @Test
    public void testAddAdmin_CustomPassword() {
        // 准备数据
        Admin admin = new Admin();
        admin.setUsername("newUser");
        admin.setPassword("customPass");

        // 设置mock行为
        when(adminMapper.selectByUsername("newUser")).thenReturn(null);

        // 执行测试
        adminService.add(admin);

        // 验证insert方法被调用，并且密码未被修改
        verify(adminMapper, times(1)).insert(argThat(a ->
                a.getPassword().equals("customPass")
        ));
    }

    // ==================== 测试 deleteBatch 方法 ====================

    @Test
    public void testDeleteBatch_NormalCase() {
        // 准备数据
        List<Integer> ids = Arrays.asList(1, 2, 3);

        // 执行测试
        adminService.deleteBatch(ids);

        // 验证deleteById方法被调用3次
        verify(adminMapper, times(1)).deleteById(1);
        verify(adminMapper, times(1)).deleteById(2);
        verify(adminMapper, times(1)).deleteById(3);
    }

    @Test
    public void testDeleteBatch_EmptyList() {
        // 准备数据
        List<Integer> ids = Collections.emptyList();

        // 执行测试
        adminService.deleteBatch(ids);

        // 验证deleteById方法未被调用
        verify(adminMapper, never()).deleteById(anyInt());
    }

    @Test
    public void testDeleteBatch_NullList() {
        // 执行测试
        adminService.deleteBatch(null);

        // 验证deleteById方法未被调用
        verify(adminMapper, never()).deleteById(anyInt());
    }

    // ==================== 测试 selectPage 方法 ====================

    @Test
    public void testSelectPage() {
        // 准备数据
        List<Admin> adminList = Arrays.asList(
                new Admin(1, "user1", null, null, null, null),
                new Admin(2, "user2", null, null, null, null)
        );

        // 设置mock行为
        when(adminMapper.selectAll(null)).thenReturn(adminList);

        // 执行测试
        PageHelper.startPage(1, 10);
        PageInfo<Admin> pageInfo = adminService.selectPage(null, 1, 10);

        // 验证结果
        assertNotNull(pageInfo);
        assertEquals(2, pageInfo.getTotal());
        assertEquals(2, pageInfo.getList().size());
    }


}