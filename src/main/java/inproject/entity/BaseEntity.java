package inproject.entity;

import com.fasterxml.jackson.annotation.JsonView;
import inproject.view.Views;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class BaseEntity  implements Serializable {
    @JsonView({Views.UserInfo.class, Views.Users.class})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private InstagramAuthUser user;
    public InstagramAuthUser getUser() {
        return user;
    }

    public void setUser(InstagramAuthUser user) {
        this.user = user;
    }
}
