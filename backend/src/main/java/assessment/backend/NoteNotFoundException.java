/**
 * An exception thrown when a note cannot be found in the repository
 * by its ID number.
 * 
 * @author James Niedfeldt
 */

package assessment.backend;

class NoteNotFoundException extends RuntimeException {

    NoteNotFoundException(long id) {
        super("Could not find note " + id);
    }
}