package home.service.user;

import home.common.page.Pagination;
import home.entity.User;
import home.exception.ShowMessageException;
import home.model.UserModel;
import home.service.base.BaseServiceImpl;
import home.util.UUIDGenerator;
import home.util.md5.Md5Utils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by qijie on 2016/3/30.
 */
@Service(value="userService")
@Transactional
public class UserService extends BaseServiceImpl<User>{

    /**
     * HQL 查询用户列表
     * @param hql
     * @return
     */
    public List<UserModel> queryListByHql(String hql){

        List<UserModel> userModels = new ArrayList<UserModel>();

        List<User> users = (List<User>)this.findList(hql);
        for (User user : users){
            userModels.add(doToModel(user));
        }

        return userModels;
    }
    /**
     * 注册用户
     * @param userModel
     */
    public void saveUser(UserModel userModel) throws ShowMessageException{

        User user = modelToDo(userModel);
        //设置一些参数
        user.setUserId(UUIDGenerator.getUUID());
        user.setRole(0);
        user.setCreateDate(new Date());
        user.setLastLoginDate(new Date());
        //密码加密
        Md5Utils md5Utils = new Md5Utils();
        user.setPassword(md5Utils.encryptionString(user.getPassword()));

        save(user);
    }

    /**
     * 用户登录
     * @param userModel
     * @return
     */
    public UserModel login(UserModel userModel){

        String hql = "from User u where u.userName = '"+userModel.getUserName()+"'";

        List<User> users = (List<User>) this.findList(hql);
        if(CollectionUtils.isNotEmpty(users)){
            User user = users.get(0);
            //密码加密
            Md5Utils md5Utils = new Md5Utils();
            String passWord = md5Utils.encryptionString(userModel.getPassWord());
            //比较密码
            if(StringUtils.equals(passWord,user.getPassword())){
                return doToModel(user);
            }
        }
        return null;
    }

    /**
     * 检查用户名是否可用
     * @param userModel
     * @return
     */
    public UserModel checkName(UserModel userModel){

        String hql = "from User u where u.userName = '"+userModel.getUserName()+"'";

        List<User> users = (List<User>) this.findList(hql);
        if(CollectionUtils.isNotEmpty(users)){
            User user = users.get(0);
            return doToModel(user);
        }
        return null;
    }

    public Pagination list(UserModel userModel){

        String hql = "from User u";
        return this.paginationList(hql,userModel.getPageNo(),userModel.getPageSize());
    }

    public User modelToDo(UserModel userModel){
        User user = new User();
        user.setUserName(userModel.getUserName());
        user.setUserId(userModel.getUserId());
        user.setPassword(userModel.getPassWord());
        user.setRole(userModel.getRole());
        return user;
    }

    public UserModel doToModel(User user){
        UserModel userModel = null;
        if(user != null){
            userModel = new UserModel();
            userModel.setRole(user.getRole());
            userModel.setUserId(user.getUserId());
            userModel.setUserName(user.getUserName());
        }
        return userModel;
    }
}
