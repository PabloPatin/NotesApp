package com.app.notes;

import com.app.tags.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class NotesService {
    private final TagService tagService;
    private final NotesRepository repository;

    @Autowired
    public NotesService(NotesRepository notesRepository, TagService tagService) {
        this.repository = notesRepository;
        this.tagService = tagService;
    }

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public Note getNoteById(int id) {
        return repository.findById(id).get();
    }

    public ResponseEntity<?> saveNote(Note note) {
        repository.save(note);
        return ResponseEntity.status(201).body(note);
    }

    public void deleteNote(int noteId) {
        Note note = this.repository.findById(noteId).get();
        repository.delete(note);
    }
}
