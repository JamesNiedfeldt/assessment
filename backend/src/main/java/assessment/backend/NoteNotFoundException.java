package assessment.backend;

class NoteNotFoundException extends RuntimeException {

    NoteNotFoundException(long id) {
        super("Could not find note " + id);
    }
}