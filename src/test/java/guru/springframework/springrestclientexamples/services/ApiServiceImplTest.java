package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceImplTest extends TestCase {

    @Autowired
    ApiService apiService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testApi() throws Exception {

        List<User> users = apiService.getUsers(3);

        assertEquals(3, users.size());
    }

    @Test
    public void testIngest() {
        User user = apiService.getUsers(1).get(0);
        assertNotNull(user);
    }
}