package inproject.service.impl;

import inproject.entity.BaseEntity;
import inproject.entity.InstagramAuthUser;
import inproject.repository.BaseRepository;
import inproject.service.Service;

import java.util.List;

abstract public class BaseService<T extends BaseEntity> implements Service<T, Long> {
    @Override
    public T add(T toAdd, InstagramAuthUser user) {
        toAdd.setUser(user);
        switch (user.getUserType()) {
            case ADMIN:
                toAdd.setConfirmed(true);
                break;
            case USER:
                toAdd.setConfirmed(false);
                break;
            case ANONYMOUS:

        }
        return add(toAdd);
    }

    public abstract BaseRepository<T> getRepository();

    @Override
    public List<T> findAll(InstagramAuthUser user) {
        switch (user.getUserType()) {
            case ADMIN:
                return getRepository().findAll();
            case USER:
                return getRepository().findAllByUserId(user.getId());
            case ANONYMOUS:
                return getRepository().findAll(true);
        }
        throw new IllegalStateException("unknown user type " + user.getUserType());
    }

    @Override
    public List<T> findAll(boolean confirmed) {
        return getRepository().findAll(confirmed);
    }

    @Override
    public T add(T toAdd) {
        return getRepository().save(toAdd);
    }

    @Override
    public T update(T toUpdate) {
        return getRepository().saveAndFlush(toUpdate);
    }

    @Override
    public T update(Long id, boolean confirmed){
        T entity = getRepository().findOne(id);
        entity.setConfirmed(confirmed);
        return getRepository().saveAndFlush(entity);
    }


    @Override
    public Object deleteById(Long id) {
        getRepository().delete(id);
        return id;
    }
}
