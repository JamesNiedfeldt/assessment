/**
 * This program implements the Spring framework to do basic CRUD commands
 * over a REST API on Note objects.
 * 
 * @author James Niedfeldt
 */

package assessment.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}

}
