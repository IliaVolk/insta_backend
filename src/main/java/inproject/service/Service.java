package inproject.service;

import java.util.List;

public interface Service<T, ID> {
    public List<T> findAll();
    public T add(T toAdd);
    public T add(T toAdd, String userType, Long userId);
    public T update(T toUpdate);
    public Object deleteById(ID id);
}
