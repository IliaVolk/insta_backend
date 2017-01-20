package inproject.service.impl;

import inproject.entity.Tag;
import inproject.repository.BaseRepository;
import inproject.repository.TagRepository;
import inproject.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends BaseService<Tag> implements TagService {
    @Autowired
    TagRepository tagRepository;


    @Override
    public BaseRepository<Tag> getRepository() {
        return tagRepository;
    }
}
