package inproject.controller;


import com.fasterxml.jackson.annotation.JsonView;
import inproject.entity.InstagramAuthUser;
import inproject.entity.Tag;
import inproject.entity.UserType;
import inproject.service.TagService;
import inproject.view.Views;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/rest/tags")
public class TagController extends BaseController {
    @Autowired
    TagService tagService;


    @JsonView(Views.Tags.class)
    @RequestMapping(method = RequestMethod.GET)
    public List<Tag> getAll(HttpServletRequest request){
        return tagService.findAll((InstagramAuthUser) request.getAttribute("user"));
    }
    @RequestMapping(method = RequestMethod.GET, params = "confirmed=false")
    @JsonView(Views.Places.class)
    public List<Tag> getAll(HttpServletRequest request,
                            HttpServletResponse response){
        if (getUser(request).getUserType() != UserType.ADMIN){
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return null;
        }
        return tagService.findAll(false);
    }

    @JsonView(Views.Stores.class)
    @RequestMapping(method = RequestMethod.POST)
    public Tag add(@RequestBody Tag tag, HttpServletRequest request){
        return tagService.add(tag,(InstagramAuthUser) request.getAttribute("user"));
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

    @JsonView(Views.Tags.class)
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}", params = "confirmed")
    public Tag update(@PathVariable Long id,
                      @RequestParam boolean confirmed,
                      HttpServletRequest request,
                      HttpServletResponse response){
        if (getUser(request).getUserType() != UserType.ADMIN){
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return null;
        }

        return tagService.update(id, confirmed);
    }
}
