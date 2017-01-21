package inproject.entity;


import com.fasterxml.jackson.annotation.JsonView;
import inproject.view.Views;

public class InstagramAuthResponse {
    @JsonView(Views.InstagramAuthResponse.class)
    private String code;
    @JsonView(Views.InstagramAuthResponse.class)
    private String access_token;
    @JsonView(Views.InstagramAuthResponse.class)
    private InstagramAuthUser user;
    @JsonView(Views.InstagramAuthResponse.class)
    private String error_message;
    @JsonView(Views.InstagramAuthResponse.class)
    private String error_type;


    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getError_type() {
        return error_type;
    }

    public void setError_type(String error_type) {
        this.error_type = error_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public InstagramAuthUser getUser() {
        return user;
    }

    public void setUser(InstagramAuthUser user) {
        this.user = user;
    }
}
