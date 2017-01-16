package inproject.service.impl;

import inproject.entity.Place;
import inproject.repository.PlaceRepository;
import inproject.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlaceServiceImpl extends BaseService<Place, Long> implements PlaceService  {
    @Autowired
    PlaceRepository placeRepository;
    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public Place add(Place toAdd) {
        return placeRepository.save(toAdd);
    }

    @Override
    public Place update(Place toUpdate) {
        return placeRepository.saveAndFlush(toUpdate);
    }

    @Override
    public Object deleteById(Long id) {
        placeRepository.delete(id);
        return id;
    }
}
