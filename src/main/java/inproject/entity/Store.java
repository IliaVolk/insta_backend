package inproject.entity;
import com.fasterxml.jackson.annotation.JsonView;
import inproject.view.Views;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stores")
public class Store extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.General.class)
    private Long id;

    @Column(name = "url")
    @JsonView(Views.General.class)
    private String url;

    @Column(name = "name")
    @JsonView(Views.General.class)
    private String name;


    @Column(name = "image")
    @JsonView(Views.Stores.class)
    private String image;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JsonView(Views.Stores.class)
    private Place place;


    @JsonView(Views.Stores.class)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private Set<Tag> tags;

    @JsonView({Views.UserInfo.class, Views.Users.class})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private InstagramAuthUser user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;

        Store store = (Store) o;

        if (id != null ? !id.equals(store.id) : store.id != null) return false;
        if (name != null ? !name.equals(store.name) : store.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }




    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public InstagramAuthUser getUser() {
        return user;
    }

    public void setUser(InstagramAuthUser user) {
        this.user = user;
    }
    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
