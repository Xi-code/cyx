package com.cyx.service;

import cn.hutool.core.util.ObjectUtil;
import com.cyx.common.RoleEnum;
import com.cyx.entity.Account;
import com.cyx.entity.Admin;
import com.cyx.exception.CustomException;
import com.cyx.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: AdminServiceImpl
 * @Author 曦
 * @Date 2025/6/3 19:55
 * @description:
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Account login(Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())) {
            throw new CustomException("用户名或密码不能为空");
        }

        Admin dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (dbAdmin == null) {
            throw new CustomException("账号不存在");
        }

        if (!dbAdmin.getPassword().equals(account.getPassword())) {
            throw new CustomException("账号或密码错误");
        }

        return dbAdmin;
    }


    @Override
    public void add(Admin admin) {
        if (ObjectUtil.isEmpty(admin.getUsername())) {
            throw new CustomException("用户名不能为空");
        }
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if(dbAdmin != null){
            throw new CustomException("账号已存在");
        }
        if(ObjectUtil.isEmpty(admin.getPassword())){
            admin.setPassword("admin");//默认密码
        }
        if(ObjectUtil.isEmpty(admin.getName())){
            admin.setName(admin.getUsername());//设置用户名称为账号名称
        }
        admin.setRole(RoleEnum.ADMIN.name());
        adminMapper.insert(admin);
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null || id <= 0) {
            throw new CustomException("ID非法");
        }

        Admin dbAdmin = adminMapper.selectById(id);
        if (dbAdmin == null) {
            throw new CustomException("删除失败：用户不存在");
        }
        adminMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        if (ObjectUtil.isEmpty(ids)) {
            throw new CustomException("ID列表不能为空");
        }
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public void updateById(Admin admin) {
        if (admin == null || admin.getId() == null) {
            throw new CustomException("更新失败：ID不能为空");
        }

        Admin dbAdmin = adminMapper.selectById(admin.getId());
        if (dbAdmin == null) {
            throw new CustomException("更新失败：用户不存在");
        }
        adminMapper.updateById(admin);
    }

    @Override
    public Admin selectById(Integer id) {
        if (id == null || id <= 0) {
            throw new CustomException("ID非法");
        }

        Admin admin = adminMapper.selectById(id);
        if (admin == null) {
            throw new CustomException("用户不存在");
        }
        return admin;
    }

    @Override
    public List<Admin> selectAll(String name) {
        return adminMapper.selectAll(name);
    }


    @Override
    public PageInfo<Admin> selectPage(String name,Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }

        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }


        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = this.selectAll(name);
        return PageInfo.of(list);
    }
}
