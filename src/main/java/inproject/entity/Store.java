package inproject.entity;
import com.fasterxml.jackson.annotation.JsonView;
import inproject.view.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "stores")
public class Store implements Serializable{
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;

        Store store = (Store) o;

        if (!id.equals(store.id)) return false;
        if (!name.equals(store.name)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @JsonView(Views.Stores.class)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)

    private Set<Tag> tags;


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
