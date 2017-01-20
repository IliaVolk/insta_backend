package inproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import inproject.entity.InstagramAuthResponse;
import inproject.entity.InstagramAuthUser;
import inproject.service.UserService;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rest/auth/")
public class AuthController {
    @Value("${FRONTEND_ORIGIN}")
    private String FRONTEND_ORIGIN;
    private final String CLIENT_ID = "d5b5c40c8a5b4fff997b641da78b49d7";
    private final String CLIENT_SECRET = "d40602af48cd4534adf19ae5aca93b56";
    private final String GRANT_TYPE = "authorization_code";
    @Value("${INSTAGRAM_AUTH_CALLBACK}")
	private String INSTAGRAM_AUTH_CALLBACK;
    //private final String REDIRECT_URL = "http://localhost:8080/rest/auth/instagram/callback";
    private HttpClient client = new DefaultHttpClient();
    private final String URL = "https://api.instagram.com/oauth/access_token";
    private ObjectMapper mapper = new ObjectMapper();


    @Autowired
    UserService userService;
    @RequestMapping(value = "instagram/url")
    @ResponseBody
    public Object authInstagramUrl(HttpServletRequest request){
        class Url{
            public String url;
        }
        Url url = new Url();
        url.url = "https://api.instagram.com/oauth/authorize/?client_id="+
                CLIENT_ID+"&redirect_uri="+INSTAGRAM_AUTH_CALLBACK+"&response_type=code";
        return url;
    }
    @RequestMapping(value = "instagram/callback", method = RequestMethod.GET)
    @ResponseBody
    public String authInstagramCodeCallback(
            @RequestParam("code") String code,
            HttpServletRequest request) throws Exception {

        HttpPost post = new HttpPost(URL);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("client_id", CLIENT_ID));
        urlParameters.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
        urlParameters.add(new BasicNameValuePair("grant_type", GRANT_TYPE));
        urlParameters.add(new BasicNameValuePair("code", code));
        urlParameters.add(new BasicNameValuePair("redirect_uri", INSTAGRAM_AUTH_CALLBACK));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));


        HttpResponse response = client.execute(post);

        InstagramAuthResponse responseObject = mapper.readValue(response.getEntity().getContent(), InstagramAuthResponse.class);
        InstagramAuthUser user = responseObject.getUser();
        if (user != null){
            user = userService.saveOrUpdate(user);
            responseObject.setUser(user);
        }



        String json = mapper.writeValueAsString(responseObject);
        return "<script>\n" +
                "    var authCredentials = " + json+";\n"+
                "    window.opener.postMessage(authCredentials, '"+FRONTEND_ORIGIN+"');\n"+
                "    window.close();" +
                "</script>";
    }
}