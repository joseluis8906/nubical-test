import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import nubical.Application;
import nubical.user.*;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String URL = "/api/v1/users";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mvc;

    private User user;

    @Before
    public void setup () {
        user = new User(
                "jhondoe01",
                "jhon",
                "dow",
                "jhondoe01@email.com",
                "jhon12345",
                "318000000",
                "active"
        );
    }

    @Test
    public void saveUserTest() throws Exception {
        JacksonJsonProvider gjs = new JacksonJsonProvider();


        mvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gjs.toJson(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(user.getUsername())));
    }


    @Test
    public void getUserByUsernameTest()
            throws Exception {

        User u_test = new User();
        u_test.setUsername("jhondoe01");


        mvc.perform(MockMvcRequestBuilders.get(URL+"/"+u_test.getUsername())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(u_test.getUsername()))
        );
    }

    @Test
    public void updateUserTest () throws Exception {
        User u_test = userRepository.findByUsername(user.getUsername());
        u_test.setUsername("doejhon01");

        JacksonJsonProvider gjs = new JacksonJsonProvider();

        String u_test_json = gjs.toJson(u_test);

        mvc.perform(MockMvcRequestBuilders.put(URL+"/"+u_test.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(u_test_json))
                .andExpect(MockMvcResultMatchers.status().isOk()
        );
    }
}
