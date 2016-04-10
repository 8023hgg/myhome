package home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页
 * Created by qijie on 2016/3/30.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "home/index";
    }
}
