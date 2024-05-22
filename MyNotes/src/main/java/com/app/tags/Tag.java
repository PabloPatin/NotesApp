package com.app.tags;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;

@Entity
@Table(indexes = @Index(name = "tag_id", columnList = "name"))
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, updatable = false, unique = true)
    private String title;

    public Tag() {
    }

    public Tag(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
