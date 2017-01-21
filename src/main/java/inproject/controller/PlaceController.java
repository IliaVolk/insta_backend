package inproject.controller;

import com.fasterxml.jackson.annotation.JsonView;
import inproject.entity.Place;
import inproject.entity.UserType;
import inproject.service.PlaceService;
import inproject.view.Views;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping("/rest/places")
public class PlaceController extends BaseController {
    @Autowired
    PlaceService placeService;


    @RequestMapping(method = RequestMethod.GET)
    @JsonView(Views.Places.class)
    public List<Place> getAll(HttpServletRequest request){
        return placeService.findAll(getUser(request));
    }

    @RequestMapping(method = RequestMethod.GET, params = "confirmed=false")
    @JsonView(Views.Places.class)
    public List<Place> getAll(HttpServletRequest request,
                              HttpServletResponse response) {
        if (getUser(request).getUserType() != UserType.ADMIN) {
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return null;
        }
        return placeService.findAll(false);
    }

    @JsonView(Views.Places.class)
    @RequestMapping(method = RequestMethod.POST)
    public Place add(@RequestBody Place place, HttpServletRequest request){
        return placeService.add(place,getUser(request));
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

    @JsonView(Views.Places.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}", params = "confirmed")
    public Place update(@PathVariable Long id,
                        @RequestParam boolean confirmed,
                        HttpServletRequest request,
                        HttpServletResponse response){
        if (getUser(request).getUserType() != UserType.ADMIN){
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        }
        return placeService.update(id, confirmed);
    }
}
