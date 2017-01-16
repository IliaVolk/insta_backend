package inproject.service.impl;

import inproject.entity.BaseEntity;
import inproject.service.Service;

abstract public class BaseService<T extends BaseEntity, ID> implements Service<T, ID> {
    @Override
    public T add(T toAdd, String userType, Long userId) {
        /*InstagramAuthUser user = new InstagramAuthUser();
        user.setId(userId);
        toAdd.setUser(user);*/
        return add(toAdd);
    }
}
