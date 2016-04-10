package home.Interface.user;

import home.Interface.api.ApiPageResponse;
import home.model.UserModel;

import java.util.List;

/**
 * Created by qijie on 2016/4/6.
 */
public class UserApiResponse extends ApiPageResponse{

    private List<UserModel> userModels;

    public UserApiResponse(){
        super();
    }

    public List<UserModel> getUserModels() {
        return userModels;
    }

    public void setUserModels(List<UserModel> userModels) {
        this.userModels = userModels;
    }
}
