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


    @Column(name = "name", unique = true, nullable = false)
    @JsonView(Views.General.class)
    private String name;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags", cascade = CascadeType.DETACH)
    //@JsonView(Views.Tags.class)
    private Set<Store> stores;

    @JsonView(Views.Tags.class)
    public int getSize(){
        if (stores == null)return 0;
        return this.stores.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (!id.equals(tag.id)) return false;
        if (!name.equals(tag.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
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
}
