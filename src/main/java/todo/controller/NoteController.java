package todo.controller;

import org.example.todolist.model.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    private List<Note> notes = new ArrayList<>();

    @GetMapping("/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", notes);
        return "note_list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") int id) {
        notes.remove(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editNotePage(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", notes.get(id));
        model.addAttribute("id", id);
        return "note_edit";
    }

    @PostMapping("/edit")
    public String editNote(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("content") String content) {
        Note note = notes.get(id);
        note.setTitle(title);
        note.setContent(content);
        return "redirect:/note/list";
    }
}

