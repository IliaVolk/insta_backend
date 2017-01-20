package inproject.entity;

import com.fasterxml.jackson.annotation.JsonView;
import inproject.view.Views;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "places")
public class Place extends BaseEntity{

    @Column(unique = true, nullable = false)
    @JsonView(Views.General.class)
    private String name;


    //@JsonView(Views.Places.class)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place", cascade = CascadeType.DETACH)
    private Set<Store> stores;

    @JsonView({Views.UserInfo.class, Views.Users.class})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private InstagramAuthUser user;



    @JsonView(Views.Places.class)
    public int getSize(){
        if (stores == null)return 0;
        return stores.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;

        Place place = (Place) o;

        if (id != null ? !id.equals(place.id) : place.id != null) return false;
        if (name != null ? !name.equals(place.name) : place.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
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

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }
    public InstagramAuthUser getUser() {
        return user;
    }

    public void setUser(InstagramAuthUser user) {
        this.user = user;
    }
}
