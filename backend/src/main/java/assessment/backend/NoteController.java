package assessment.backend;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class NoteController {

    private final NoteRepository repository;

    NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    //Create new note
    @PostMapping("/api/notes")
    Note createNote(@RequestBody Note newNote) {
        return repository.save(newNote);
    }
    
    //Get all notes or search by body
    @GetMapping("/api/notes")
    List<Note> getAllNotes(@RequestParam(required = false) String query) {
        if (query == null) {
            return repository.findAll();
        } else {
            return repository.findByBodyContaining(query);
        }
    }

    //Get single note by ID
    @GetMapping("/api/notes/{id}")
    Note getNote(@PathVariable long id) {
        return repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    //Modify existing note
    @PutMapping("/api/notes/{id}")
    Note updateNote(@RequestBody Note newNote, @PathVariable long id) {
        return repository.findById(id)
            .map(note -> {
                note.setBody(newNote.getBody());
                return repository.save(note);
            })
            .orElseThrow(() -> new NoteNotFoundException(id));
    }

    //Delete existing note
    @DeleteMapping("/api/notes/{id}")
    void deleteNote(@PathVariable long id) {
        repository.deleteById(id);
    }

}