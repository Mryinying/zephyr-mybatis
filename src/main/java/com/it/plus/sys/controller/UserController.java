package com.it.plus.sys.controller;


import com.it.data.Result;
import com.it.plus.sys.entity.User;
import com.it.plus.sys.service.IUserService;
import com.it.utils.PwdUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2020-05-22
 */
@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private IUserService userService;
    /**
     * 用户查询.
     * @return
     */
    @GetMapping("/userList")
    @RequiresPermissions("user:view")
    public Result listUsers(){
        List<User> users = userService.list();
        return Result.success("查询成功！", users);
    }

    /**
     * 用户添加;
     * @return
     */
    @PostMapping("/register")
    @RequiresPermissions("user:add")//权限管理;
    public Result userInfoAdd(String username, String password){
        String[] strings = PwdUtil.encryptPassword(password);
        User user = new User().setPassword(strings[1]).setSalt(strings[0]).setUserName(username).setName(username).setState(1);
        userService.save(user);
        return Result.success();
    }

    /**
     * 用户删除;
     * @return
     */
    @DeleteMapping("/userDel")
    @RequiresPermissions("user:del")//权限管理;
    public Result userDel(){
        return  Result.success();
    }

}

