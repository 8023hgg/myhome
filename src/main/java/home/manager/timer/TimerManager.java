package home.manager.timer;

import home.model.UserModel;
import home.service.user.UserService;
import home.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 定时器管理Manager
 * Created by qijie on 2016/4/11.
 */
public class TimerManager {

    @Autowired
    private UserService userService;

    private Integer i = 0;

    /**
     * 定时保存一些用户
     */
    public void run(){
        UserModel userModel = new UserModel();
        userModel.setPassWord("123456"+i.toString());
        userModel.setRole(0);
        userModel.setUserId(UUIDGenerator.getUUID());
        userModel.setUserName("test-user"+i.toString());
        i++;
        userService.saveUser(userModel);
    }
}
