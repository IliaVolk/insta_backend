package inproject.service.impl;

import inproject.entity.Tag;
import inproject.repository.TagRepository;
import inproject.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository tagRepository;
    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag add(Tag toAdd) {
        return tagRepository.save(toAdd);
    }

    @Override
    public Tag update(Tag toUpdate) {
        return tagRepository.saveAndFlush(toUpdate);
    }

    @Override
    public Object deleteById(Long id) {
        tagRepository.delete(id);
        return id;
    }
}
