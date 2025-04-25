package feedbackservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private final FeedbackRepo repo;

    @Autowired
    public TaskController(FeedbackRepo repo) {
        this.repo = repo;
    }

    //Resets the database and inserts some test variables into the database
    @PostConstruct
    private void init() {
        repo.deleteAll();
    }

    //Checks the health of the service and returns 1 if it is running
    @GetMapping("/health")
    public int returnOne() {
        return 1;
    }

    //Retrieves all the information from the database/repo
    @GetMapping("/getAllFeedback")
    public Object getAllFeedback() {
        return repo.findAll();
    }

    @PostMapping("/feedback")
    public Object saveFeedback(@RequestBody Feedback feedback) {
        Feedback savedFeedback = repo.save(feedback);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/feedback/" + savedFeedback.getId());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @GetMapping("/feedback")
    public List<Feedback> getFeedback() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @GetMapping("/feedback/{id}")
    public Object getFeedback(@PathVariable("id") String id) {
        Feedback queryFeedback = repo.findById(id).orElse(null);
        if (queryFeedback == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return queryFeedback;
    }

    @GetMapping("/deleteDB")
    public ResponseEntity<Object> deleteDB() {
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
