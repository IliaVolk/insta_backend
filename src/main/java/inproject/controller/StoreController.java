package inproject.controller;


import com.fasterxml.jackson.annotation.JsonView;
import inproject.entity.Store;
import inproject.service.StoreService;
import inproject.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/stores")
public class StoreController {
    @Autowired
    StoreService storeService;


    @JsonView(Views.Stores.class)
    @RequestMapping(method = RequestMethod.GET)
    public List<Store> getAll(){
        return storeService.findAll();
    }

    @JsonView(Views.Stores.class)
    @RequestMapping(method = RequestMethod.POST)
    public Store add(@RequestBody Store store, HttpServletRequest request){
        return storeService.add(store,
                (String)request.getAttribute("USER_TYPE"),
                (Long)request.getAttribute("USER_ID"));
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
}
