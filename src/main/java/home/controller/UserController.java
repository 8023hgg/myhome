package home.controller;

import com.alibaba.fastjson.JSON;
import home.common.page.Pagination;
import home.entity.User;
import home.exception.ShowMessageException;
import home.manager.ThreadTestManager;
import home.model.UserModel;
import home.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by qijie on 2016/3/29.
 */
@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService         userService;

    //单个redis
//    @Autowired
//    private JedisPool           jedisPool;

    //集群redis 数据优先存放在比较空闲的redis中
//    @Autowired
//    private ShardedJedisPool    shardedJedisPool;

    @Autowired
    private ThreadTestManager   threadTestManager;


    /**
     * 我的简历主页
     * @return
     */
    @RequestMapping("/hgg")
    public String resume(){
        return "user/hgg";
    }
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value="/login")
    public String login(){
//        Jedis jedis = jedisPool.getResource();
//        jedis.set("name1","黄国刚");
        //jedisPool.returnResource(jedis);
//        ShardedJedis shardedJedis = shardedJedisPool.getResource();
//        shardedJedis.set("name3","黄国刚");
        return "user/login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value="/register")
    public String register(){
//        threadTestManager.threadTest();
//        ShardedJedis shardedJedis = shardedJedisPool.getResource();
//        String name = shardedJedis.get("name3");
//        System.out.print(name);
        return "user/register";
    }

    /**
     * 用户注册
     * @param userModel
     * @return
     */
    @RequestMapping(value = "/save")
    public ModelAndView save(UserModel userModel,Model model){

        try {
            userService.saveUser(userModel);
            return new ModelAndView("redirect:/user/login");
        }catch (ShowMessageException e){
            return new ModelAndView("redirect:/error/error?message="+e.getMessage());
        }
    }

    /**
     * 用户登录
     * @param userModel
     * @return
     */
    @RequestMapping("/home")
    @ResponseBody
    public String home(UserModel userModel,HttpServletRequest request){

        UserModel currentUser = userService.login(userModel);

        if(currentUser != null){
            //用户登录成功
            request.getSession().setAttribute("user",currentUser);
            return JSON.toJSONString("T");
        }
        return JSON.toJSONString("F");
    }

    /**
     * 检查名字是否重复
     * @param userModel
     * @return
     */
    @RequestMapping("/check/name")
    @ResponseBody
    public String checkName(UserModel userModel){

        UserModel currentUser = userService.checkName(userModel);
        if(currentUser != null){
            return JSON.toJSONString("F");
        }
        return JSON.toJSONString("T");
    }

    /**
     * 用户列表
     * @return
     */
    @RequestMapping("/list")
    public String list(UserModel userModel,Model model){

        UserModel userModels = new UserModel();
        userModels.setPageNo(1);
        userModels.setPageSize(20);
        Pagination pagination = userService.list(userModels);
        List<User> users = (List<User>)pagination.getList();

        model.addAttribute("pagination",pagination);
        model.addAttribute("users",users);
        return "user/list";
    }
    /**
     * 异常处理页面
     * @param model
     * @param message
     * @return
     */
    @RequestMapping("/error")
    public String error(Model model,String message){

        model.addAttribute("message",message);
        return "error/error";
    }
}
