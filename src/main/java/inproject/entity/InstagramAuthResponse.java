package inproject.entity;


public class InstagramAuthResponse {
    private String access_token;
    private InstagramAuthUser user;

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
