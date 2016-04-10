package user;

import home.Interface.client.ApiClient;
import home.Interface.user.UserApiResponse;
import home.model.UserModel;
import home.qo.api.UserApiQo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by qijie on 2016/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserApiTest {

    @Autowired
    private ApiClient   apiClient;

//    @Test
    public void testUserApi(){
        UserApiQo userApiQo = new UserApiQo();
        userApiQo.setUserName("admin");
        UserApiResponse userApiResponse = apiClient.send(userApiQo, UserApiResponse.class);
        List<UserModel> userModels = userApiResponse.getUserModels();
        for (UserModel userModel : userModels){
            System.out.print(userModel.getUserName());
        }
    }
}
