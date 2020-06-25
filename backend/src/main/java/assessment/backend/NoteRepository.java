/**
 * An interface for the Spring framework to implement on a
 * JPA repository containing Note objects.
 * 
 * @author James Niedfeldt
 */

package assessment.backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

interface NoteRepository extends JpaRepository<Note, Long> {
    /**
     * Implemented by the Spring framework to search the repository
     * and return all Notes where its body contains the supplied query.
     * 
     * @param query the query to be searched for in note bodies
     * @return      a list of Note objects whose bodies contain the given query
     */
    List<Note> findByBodyContaining(@Param("query") String query);
}