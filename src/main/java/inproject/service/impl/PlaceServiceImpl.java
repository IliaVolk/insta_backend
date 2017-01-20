package inproject.service.impl;

import inproject.entity.Place;
import inproject.repository.BaseRepository;
import inproject.repository.PlaceRepository;
import inproject.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PlaceServiceImpl extends BaseService<Place> implements PlaceService  {
    @Autowired
    PlaceRepository placeRepository;


    @Override
    public BaseRepository<Place> getRepository() {
        return placeRepository;
    }
}
