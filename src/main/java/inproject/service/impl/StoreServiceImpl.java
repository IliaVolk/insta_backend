package inproject.service.impl;


import inproject.entity.Store;
import inproject.repository.StoreRepository;
import inproject.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreRepository storeRepository;
    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store add(Store toAdd) {
        return storeRepository.save(toAdd);
    }

    @Override
    public Store update(Store toUpdate) {
        return storeRepository.saveAndFlush(toUpdate);
    }

    @Override
    public Object deleteById(Long id) {
        storeRepository.delete(id);
        return id;
    }
}
