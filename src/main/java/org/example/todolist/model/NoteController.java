package org.example.todolist.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        return "note/list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") Long id) {
        noteService.deleteNote(id);
        return "redirect:/note/list";
    }

    @PostMapping("/save")
    public String saveNote(@ModelAttribute Note note) {
        noteService.saveNote(note);
        return "redirect:/note/list";
    }
}


