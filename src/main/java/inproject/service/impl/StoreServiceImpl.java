package inproject.service.impl;


import inproject.entity.Store;
import inproject.entity.StoreSearchResponse;
import inproject.repository.StoreRepository;
import inproject.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public List<Store> search(String tagsString, String place) {
        if (tagsString == null && place == null){
            return storeRepository.findAll();
        }

        if (tagsString != null){
            Set<String> tags = Stream.of(tagsString.split(",")).collect(Collectors.toSet());
            if (place == null){
                return storeRepository.search(tags).sorted().map(StoreSearchResponse::getStore).collect(Collectors.toList());
            }else{
                return storeRepository.search(tags, place).sorted().map(StoreSearchResponse::getStore).collect(Collectors.toList());
            }
        }else{
            return storeRepository.search(place);
        }

    }
}
