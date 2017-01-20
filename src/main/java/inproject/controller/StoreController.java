package inproject.controller;


import com.fasterxml.jackson.annotation.JsonView;
import inproject.entity.InstagramAuthUser;
import inproject.entity.Store;
import inproject.entity.UserType;
import inproject.service.StoreService;
import inproject.view.Views;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/stores")
public class StoreController extends BaseController{
    @Autowired
    StoreService storeService;


    @JsonView(Views.Stores.class)
    @RequestMapping(method = RequestMethod.GET)
    public List<Store> getAll(HttpServletRequest request){
        return storeService.findAll((InstagramAuthUser) request.getAttribute("user"));
    }
    @RequestMapping(method = RequestMethod.GET, params = "confirmed=false")
    @JsonView(Views.Stores.class)
    public List<Store> getAll(HttpServletRequest request,
                              HttpServletResponse response){
        if (getUser(request).getUserType() != UserType.ADMIN){
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return null;
        }
        return storeService.findAll(false);
    }

    @JsonView(Views.Stores.class)
    @RequestMapping(method = RequestMethod.POST)
    public Store add(@RequestBody Store store, HttpServletRequest request){
        return storeService.add(store,getUser(request));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Object delete(@PathVariable Long id){
        return storeService.deleteById(id);
    }

    @JsonView(Views.Stores.class)
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Store update(@RequestBody Store store,@PathVariable Long id){
        return storeService.update(store);
    }


    @JsonView(Views.Stores.class)
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public List<Store> search(@RequestParam(value = "tags", required = false) String tags,
                              @RequestParam(value = "place", required = false) String place,
                              @RequestParam(value = "skip", required = false, defaultValue = "0") int skip,
                              @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){

        return this.storeService.search(tags, place, skip, limit);
    }
    @JsonView(Views.Stores.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}", params = "confirmed")
    public Store update(
            @PathVariable Long id,
            @RequestParam boolean confirmed,
            HttpServletRequest request,
            HttpServletResponse response){
        if (getUser(request).getUserType() != UserType.ADMIN){
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return null;
        }
        return storeService.update(id, confirmed);
    }
}
