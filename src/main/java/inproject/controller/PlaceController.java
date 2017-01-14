package inproject.controller;

import com.fasterxml.jackson.annotation.JsonView;
import inproject.entity.Place;
import inproject.service.PlaceService;
import inproject.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
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


    @JsonView(Views.Places.class)
    @RequestMapping(method = RequestMethod.POST)
    public Place add(@RequestBody Place place){
        return placeService.add(place);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Object delete(@PathVariable Long id){
        return placeService.deleteById(id);
    }


    @JsonView(Views.Places.class)
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Place update(@RequestBody Place place, @PathVariable Long id){
        return placeService.update(place);
    }
}
