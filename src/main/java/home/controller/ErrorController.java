package home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qijie on 2016/3/31.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    /**
     * 异常页面
     * @param message
     * @return
     */
    @RequestMapping("/error")
    public String error(String message,Model model){

        model.addAttribute("message",message);
        return "error/error";
    }
}
