package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: DeptController
 * @Author 曦
 * @Date 2025/3/29 20:31
 * @description:
 */

@Slf4j//private static final Logger log = LoggerFactory.getLogger(DeptController.class);
@RequestMapping("/depts") //把这个公共的路径 /depts 抽取到类上,那在各个方法上就可以省略了这个/depts路径
@RestController
public class DeptController {

    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    @GetMapping
    //@RequestMapping(path = "/depts",method = RequestMethod.GET)  //method用来指定请求方式
    public Result list() {
        //System.out.println("查询全部的部门数据");
        log.info("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //删除部门
    //方式一：HttpServletRequest获取请求参数
    /*@DeleteMapping("/depts")
    public Result delete(HttpServletRequest request){
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("根据ID删除部门：" + id);
        return Result.success();
    }*/

    //方式二：@RequestParam获取请求参数
    //注意事项：一旦声明了@RequestParram,该参数在请求时必须传递，如果不传递将会报错（默认required为true）
   /* @DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id",required = false) Integer deptId){
        System.out.println("根据ID删除部门：" + deptId);
        return Result.success();
    }*/

    //删除部门
    //方式三：省略@RequestParam注解，直接获取请求参数（前端传递的请求参数名与服务端方法形参名称一致）
    @DeleteMapping
    public Result delete(Integer id) {
        //System.out.println("根据ID删除部门：" + id);
        //log.info("根据ID删除部门：" + id);
        log.info("根据ID删除部门：{}", id); //占位符
        deptService.deleteById(id);
        return Result.success();
    }

    //添加部门
    @PostMapping
    //接收json格式的请求体数据 规则：JSON数据的键名与方法形参的对象的属性名相同 并需要使用@RequestBody注解
    public Result add(@RequestBody Dept dept) {
        //System.out.println("添加部门：" + dept);
        log.info("添加部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }


    //根据id查询部门
   /* @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){
        System.out.println("根据ID查询部门：" + deptId);
        return Result.success();
    }*/
    //如果路径参数名与controller方法形参名称一致，@PathVariable注解的value属性是可以省略的。
     @GetMapping("{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根据ID查询部门：" + id);
         log.info("根据ID查询部门：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //根据id修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("根据ID修改部门：" + dept);
         log.info("根据ID修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
