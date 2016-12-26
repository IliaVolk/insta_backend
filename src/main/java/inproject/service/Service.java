package inproject.service;

import java.util.List;

public interface Service<T, ID> {
    public List<T> findAll();
    public T add(T toAdd);
    public T update(T toUpdate);
    public T deleteById(ID id);
}
