package inproject.controller;

import com.fasterxml.jackson.annotation.JsonView;
import inproject.entity.Place;
import inproject.service.PlaceService;
import inproject.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/places")
public class PlaceController {
    @Autowired
    PlaceService placeService;


    @RequestMapping(method = RequestMethod.GET)
    @JsonView(Views.Places.class)
    public List<Place> getAll(){
        return placeService.findAll();
    }


    @RequestMapping(method = RequestMethod.POST)
    public Place add(@RequestBody Place place){
        return placeService.add(place);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Place delete(@PathVariable Long id){
        return placeService.deleteById(id);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Place update(@RequestBody Place place){
        return placeService.update(place);
    }
}
