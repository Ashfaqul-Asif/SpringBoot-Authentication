package com.example.demo.service;


import com.example.demo.model.Content;
import com.example.demo.repository.ContentCollectionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    private final ContentCollectionRepository repository;

    public ContentService(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<Content>> getContent() {
        try {
            List<Content> contentList = new ArrayList<>();
            repository.findAll().forEach(contentList::add);
            if (contentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contentList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Content> getById(Long id) {
        Optional<Content> contentData = repository.findById(id);
        if (contentData.isPresent()) {
            return new ResponseEntity<>(contentData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Content> addContent(Content content) {
        Content contentObj = repository.save(content);
        return new ResponseEntity<>(contentObj,HttpStatus.OK);
    }

    public ResponseEntity<Content> updateContent(Content content, Long id) {
        Content  contentData = repository.findById(id).orElse(null);
        if (contentData != null){
            contentData.setContentType(content.getContentType());

            Content contentObj = repository.save(contentData);

            return new ResponseEntity<>(contentObj,HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HttpStatus> deleteContent(Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
