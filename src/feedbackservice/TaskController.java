package feedbackservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostConstruct
    private void init() {
        repo.deleteAll();

        List<Feedback> feeds = List.of(
                new Feedback("1324", 5, "test", "person"),
                new Feedback("1111", 5, "test", "person"));
        repo.saveAll(feeds);
    }


    @GetMapping("/test")
    public int returnOne() {
        return 1;
    }

    @GetMapping("/getAllFeedback")
    public Object getAllFeedback() {
        return repo.findAll();
    }

    @GetMapping("/deleteDB")
    public ResponseEntity<Object> deleteDB() {
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
