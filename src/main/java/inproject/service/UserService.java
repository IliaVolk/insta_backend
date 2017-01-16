package inproject.service;

import inproject.entity.InstagramAuthUser;

import java.util.List;

public interface UserService  {
    public boolean exists(InstagramAuthUser user);
    public InstagramAuthUser findOne(InstagramAuthUser user);
    public InstagramAuthUser saveOrUpdate(InstagramAuthUser user);
    public List<InstagramAuthUser> findAll();
    public InstagramAuthUser add(InstagramAuthUser toAdd);
    public InstagramAuthUser update(InstagramAuthUser toUpdate);
}
