package assessment.backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByBodyContaining(@Param("query") String query);
}