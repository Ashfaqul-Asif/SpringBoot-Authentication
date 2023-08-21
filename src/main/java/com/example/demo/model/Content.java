package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Entity
@Table(name="content")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;
    private  String title;
    private  String description;
    private  Type contentType;
    private  Status status;
    @CreationTimestamp
    private  LocalDateTime dateCreated;
    @UpdateTimestamp
    private  LocalDateTime dateUpdated;
    private  String url;

}
