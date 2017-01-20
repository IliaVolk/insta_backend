package inproject.service.impl;

import inproject.entity.InstagramAuthUser;
import inproject.repository.UserRepository;
import inproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<InstagramAuthUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public InstagramAuthUser add(InstagramAuthUser toAdd) {
        return userRepository.save(toAdd);
    }

    @Override
    public InstagramAuthUser update(InstagramAuthUser toUpdate) {
        return userRepository.save(toUpdate);
    }
    @Override
    public boolean exists(InstagramAuthUser user) {
        return user != null && userRepository.exists(user.getId());
    }
    @Override
    public InstagramAuthUser findOne(InstagramAuthUser user){
        return userRepository.findOne(user.getId());
    }
    @Override
    public InstagramAuthUser saveOrUpdate(InstagramAuthUser user){
        if (exists(user)){
            InstagramAuthUser newUser = findOne(user);
            newUser.setBio(user.getBio());
            newUser.setFull_name(user.getFull_name());
            newUser.setProfile_picture(user.getProfile_picture());
            newUser.setWebsite(user.getWebsite());
            newUser.setUsername(user.getUsername());
            return newUser;
        }else{
            return add(user);
        }
    }

}
