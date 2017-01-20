import com.fasterxml.jackson.databind.ObjectMapper;
import inproject.Application;
import inproject.entity.InstagramAuthUser;
import inproject.entity.Tag;
import inproject.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestPropertySource(locations="classpath:test.properties")
public class ConfirmedPropertyTest {
    private TestRestTemplate template = new TestRestTemplate();

    @Autowired
    UserRepository userRepository;
    InstagramAuthUser user;
    ObjectMapper mapper = new ObjectMapper();
    @Before
    public void before() throws Exception{
        userRepository.deleteAll();
        user = new InstagramAuthUser();
        user.setId(12345l);
        userRepository.save(user);
    }


    @Test
    public void testAdd() throws Exception {
        Tag toPost = new Tag();
        toPost.setName("tag1");
        //mvc.perform(patch("/rest/tags?id="));
    }
}
