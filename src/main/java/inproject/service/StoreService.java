package inproject.service;


import inproject.entity.Store;

import java.util.List;

public interface StoreService extends Service<Store, Long> {
    public List<Store> search(String tags, String place, int skip, int limit);
}
