package home.service.api;

import home.Interface.api.ApiRequest;
import home.Interface.user.UserApiResponse;
import home.model.UserModel;
import home.qo.api.UserApiQo;
import home.service.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qijie on 2016/4/6.
 */
@Service
public class UserApiService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public UserApiResponse queryUser(ApiRequest<UserApiQo> request){

        UserApiResponse userApiResponse = new UserApiResponse();
        //拼接Hql
        String hql = joinHql(request);

        List<UserModel> users = userService.queryListByHql(hql);

        userApiResponse.setUserModels(users);
        userApiResponse.setPageNo(1);
        userApiResponse.setPageSize(1);
        userApiResponse.setTotalCount(1);
        return userApiResponse;
    }

    /**
     * 拼接Hql
     * @param request
     * @return
     */
    private String joinHql(ApiRequest<UserApiQo> request){

        StringBuilder hqlBuilder = new StringBuilder("from User u ");

        UserApiQo userApiQo = request.getBody();
        if(StringUtils.isNotBlank(userApiQo.getUserName())){
            hqlBuilder.append("where u.userName = '"+userApiQo.getUserName()+"'");
        }

        return hqlBuilder.toString();
    }

}
