package feedbackservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    }


    @GetMapping("/test")
    public int returnOne() {
        return 1;
    }

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

    @GetMapping("/deleteDB")
    public ResponseEntity<Object> deleteDB() {
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
