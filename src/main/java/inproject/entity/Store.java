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




    @ManyToOne(fetch = FetchType.EAGER)
    @JsonView(Views.Stores.class)
    private Place place;


    @JsonView(Views.Stores.class)
    @ManyToMany(fetch = FetchType.EAGER)
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
