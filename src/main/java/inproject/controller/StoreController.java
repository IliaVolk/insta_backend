package inproject.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.internal.Nullable;
import inproject.entity.Store;
import inproject.service.StoreService;
import inproject.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Store add(@RequestBody Store store){
        return storeService.add(store);
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
    public List<Store> search(@Nullable @RequestParam(value = "tags", required = false) String tags,
                              @Nullable @RequestParam(value = "place", required = false) String place){
        return this.storeService.search(tags, place);
    }
}
