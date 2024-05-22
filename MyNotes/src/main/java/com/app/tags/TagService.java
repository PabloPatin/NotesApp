package com.app.tags;

import com.app.notes.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TagService {
    private TagRepository repository;

    @Autowired
    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> getAllTags(){
        return repository.findAll();
    }

    public void createTag(Tag tag){
        repository.save(tag);
    }

    public Tag findOrCreateTag(Tag tag){
        if (repository.findByTitle(tag.getTitle())==null) {
            repository.save(tag);
            return tag;
        }
        else{
            return repository.findByTitle(tag.getTitle());
        }
    }

    public void checkTags(Set<Tag> tags){
        tags.forEach(this::findOrCreateTag);
    }
}
