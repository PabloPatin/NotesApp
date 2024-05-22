package com.app.notes;

import com.app.tags.Tag;
import com.app.tags.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/notes")
@RestController
public class NotesController {
    private NotesService service;
    private TagService tagService;

    @Autowired
    public NotesController(NotesService notesService, TagService tagService) {
        this.service = notesService;
        this.tagService = tagService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return service.getAllNotes();
    }

    @GetMapping(path = "{noteId}")
    public Note getNote(@PathVariable int noteId) {
        return service.getNoteById(noteId);
    }

    @PostMapping
    public ResponseEntity<?> addNote(@RequestBody Note note) {
        Tag tag = tagService.findOrCreateTag(note.getCategory());
        note.setCategory(tag);
        return service.saveNote(note);
    }

    @PutMapping(path = "{noteId}")
    public ResponseEntity<?> updateNote(@PathVariable int noteId, @RequestBody Note newNote) {
        Note note = this.service.getNoteById(noteId);
        Tag category = newNote.getCategory();
        note.setCategory(category);
        note.setTitle(newNote.getTitle());
        note.setContent(newNote.getContent());
        return service.saveNote(note);
    }

    @DeleteMapping(path = "{noteId}")
    public void deleteNote(@PathVariable int noteId) {
        service.deleteNote(noteId);
    }
}
