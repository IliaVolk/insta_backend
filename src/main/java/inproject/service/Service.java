package inproject.service;

import inproject.entity.InstagramAuthUser;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface Service<T, ID> {
    public List<T> findAll(@NotNull InstagramAuthUser user);
    public List<T> findAll(boolean confirmed);
    public T add(T toAdd);
    public T add(T toAdd, InstagramAuthUser user);
    public T update(T toUpdate);
    public T update(ID id, boolean confirmed);
    public Object deleteById(ID id);
}
