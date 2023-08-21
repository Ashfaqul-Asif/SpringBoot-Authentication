package com.example.demo.controller;

import com.example.demo.model.Content;
import com.example.demo.repository.ContentCollectionRepository;
import com.example.demo.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@CrossOrigin

@RequestMapping("/api/content")

public class ContentController {
    private ContentService service;

    private ContentCollectionRepository repository;
    public ContentController(ContentService service, ContentCollectionRepository repository) {
        this.service = service;
        this.repository = repository;
    }


    @GetMapping()

    public ResponseEntity <List<Content>> getAllContent() {
       return service.getContent();
    }

    @GetMapping("/{id}")

    public ResponseEntity<Content> findById(@PathVariable Long id) {
        return  service.getById(id);

    }


    @PostMapping("")
    public ResponseEntity<Content> createContent(@Valid  @RequestBody Content content) {
        return service.addContent(content);


    }

    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@RequestBody Content content, @PathVariable Long id) {
        return service.updateContent(content,id);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteContent(@PathVariable Long id) {
        return service.deleteContent(id);

    }
}
