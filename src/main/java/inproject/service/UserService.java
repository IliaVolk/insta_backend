package inproject.service;

import inproject.entity.InstagramAuthUser;

public interface UserService extends Service<InstagramAuthUser, Long> {
    public boolean exists(InstagramAuthUser user);
    public InstagramAuthUser findOne(InstagramAuthUser user);
    public InstagramAuthUser saveOrUpdate(InstagramAuthUser user);
}
