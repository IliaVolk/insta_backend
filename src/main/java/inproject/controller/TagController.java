package inproject.controller;


import com.fasterxml.jackson.annotation.JsonView;
import inproject.entity.Tag;
import inproject.service.TagService;
import inproject.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(method = RequestMethod.POST)
    public Tag add(@RequestBody Tag tag){
        return tagService.add(tag);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Tag delete(@PathVariable Long id){
        return tagService.deleteById(id);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Tag update(@RequestBody Tag tag){
        return tagService.update(tag);
    }
}
