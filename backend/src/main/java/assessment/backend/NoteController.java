/**
 * The controller for basic CRUD operations on the NoteRepository.
 * 
 * @author James Niedfeldt
 */

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

    /**
     * Adds a new note into the repository and returns the added note upon
     * successful save.
     * 
     * @param newNote   The note to add to the repository
     * @return          The note returned by saving to the repository
     */
    @PostMapping("/api/notes")
    Note createNote(@RequestBody Note newNote) {
        return repository.save(newNote);
    }
    
    /**
     * Returns all notes saved in the repository. If the optional query
     * argument is given, instead return all notes whose bodies contain
     * the given query.
     * 
     * @param query The query to search for within the repository's notes
     * @return      All notes from repository or all notes with query in body
     */
    @GetMapping("/api/notes")
    List<Note> getAllNotes(@RequestParam(required = false) String query) {
        if (query == null) {
            return repository.findAll();
        } else {
            return repository.findByBodyContaining(query);
        }
    }

    /**
     * Returns a specific note by its ID number
     * 
     * @param id    The ID number of the note to return
     * @return      The note object with the given ID number
     * @throws      NoteNotFoundException if no note is found with given ID number
     */
    @GetMapping("/api/notes/{id}")
    Note getNote(@PathVariable long id) {
        return repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    /**
     * Replaces an existing note with a given one. 
     * 
     * @param newNote   The replacement Note. The id field is ignored.
     * @param id        The ID number of the note to replace.
     * @return          The note returned by saving the replacement note to the repository.
     * @throws          NoteNotFoundException if no note is found with given ID number
     */
    @PutMapping("/api/notes/{id}")
    Note updateNote(@RequestBody Note newNote, @PathVariable long id) {
        return repository.findById(id)
            .map(note -> {
                note.setBody(newNote.getBody());
                return repository.save(note);
            })
            .orElseThrow(() -> new NoteNotFoundException(id));
    }

    /**
     * Deletes a note from the repository.
     * 
     * @param id    The ID number of the note to delete
     */
    @DeleteMapping("/api/notes/{id}")
    void deleteNote(@PathVariable long id) {
        repository.deleteById(id);
    }

}