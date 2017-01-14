package inproject.entity;

import com.fasterxml.jackson.annotation.JsonView;
import inproject.view.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.General.class)
    private Long id;


    @Column(name = "name")
    @JsonView(Views.General.class)
    private String name;


    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags", cascade = CascadeType.DETACH)
    //@JsonView(Views.Tags.class)
    private Set<Store> stores;

    @JsonView(Views.Tags.class)
    public int getSize(){
        if (stores == null)return 0;
        return this.stores.size();
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
}
