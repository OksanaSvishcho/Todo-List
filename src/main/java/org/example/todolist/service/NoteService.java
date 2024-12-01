package org.example.todolist.service;

import org.example.todolist.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteService {
    private final List<Note> notes = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Note> listAll() {
        return new ArrayList<>(notes);
    }

    public Note add(Note note) {
        note.setId(idGenerator.incrementAndGet());
        notes.add(note);
        return note;
    }

    public void deleteById(long id) {
        boolean removed = notes.removeIf(note -> note.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("Note with id " + id + " not found.");
        }
    }

    public void update(Note note) {
        Optional<Note> existingNoteOpt = notes.stream()
                .filter(n -> n.getId() == note.getId())
                .findFirst();

        if (existingNoteOpt.isPresent()) {
            Note existingNote = existingNoteOpt.get();
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
        } else {
            throw new IllegalArgumentException("Note with id " + note.getId() + " not found.");
        }
    }

    public Note getById(long id) {
        return notes.stream()
                .filter(note -> note.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Note with id " + id + " not found."));
    }
}
