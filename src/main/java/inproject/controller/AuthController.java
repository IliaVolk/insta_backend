package inproject.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import inproject.entity.InstagramAuthResponse;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rest/auth/")
public class AuthController {
    private final String CLIENT_ID = "d5b5c40c8a5b4fff997b641da78b49d7";
    private final String CLIENT_SECRET = "d40602af48cd4534adf19ae5aca93b56";
    private final String GRANT_TYPE = "authorization_code";
    private final String REDIRECT_URL = "http://localhost:8080/rest/auth/instagram/callback";
    private HttpClient client = new DefaultHttpClient();
    private final String URL = "https://api.instagram.com/oauth/access_token";
    private ObjectMapper mapper = new ObjectMapper();


    @RequestMapping(value = "instagram/url")
    @ResponseBody
    public Object authInstagramUrl(){
        class Url{
            public String url;
        }
        Url url = new Url();
        url.url = "https://api.instagram.com/oauth/authorize/?client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URL+"&response_type=code";
        return url;
    }
    @RequestMapping(value = "instagram/callback", method = RequestMethod.GET)
    @ResponseBody
    public String authInstagramCodeCallback(@RequestParam("code") String code) throws Exception {

        HttpPost post = new HttpPost(URL);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("client_id", CLIENT_ID));
        urlParameters.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
        urlParameters.add(new BasicNameValuePair("grant_type", GRANT_TYPE));
        urlParameters.add(new BasicNameValuePair("code", code));
        urlParameters.add(new BasicNameValuePair("redirect_uri", REDIRECT_URL));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));


        HttpResponse response = client.execute(post);

        InstagramAuthResponse object = mapper.readValue(response.getEntity().getContent(), InstagramAuthResponse.class);
        String json = mapper.writeValueAsString(object);
        return "<script>\n" +
                "    var authCredentials = " + json+";\n"+
                "    window.opener.postMessage(authCredentials, 'http://localhost:6500');\n"+
                "    window.close();" +
                "</script>";
    }
}