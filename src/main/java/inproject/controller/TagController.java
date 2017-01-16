package inproject.controller;


import com.fasterxml.jackson.annotation.JsonView;
import inproject.entity.Tag;
import inproject.service.TagService;
import inproject.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/rest/tags")
public class TagController {
    @Autowired
    TagService tagService;


    @JsonView(Views.Tags.class)
    @RequestMapping(method = RequestMethod.GET)
    public List<Tag> getAll(){
        return tagService.findAll();
    }


    @JsonView(Views.Stores.class)
    @RequestMapping(method = RequestMethod.POST)
    public Tag add(@RequestBody Tag tag, HttpServletRequest request){
        return tagService.add(tag,
                (String)request.getAttribute("USER_TYPE"),
                (Long)request.getAttribute("USER_ID"));
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Object delete(@PathVariable Long id){
        return tagService.deleteById(id);
    }


    @JsonView(Views.Stores.class)
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Tag update(@RequestBody Tag tag, @PathVariable Long id){
        return tagService.update(tag);
    }
}
