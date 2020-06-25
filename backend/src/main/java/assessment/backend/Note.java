/**
 * A JPA entity representing a note object
 * 
 * @author James Niedfeldt
 */

package assessment.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Note {

    private @Id @GeneratedValue long id;
    private String body;

    public Note() {}

    public Note(long id, String body) {
        this.id = id;
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }
}