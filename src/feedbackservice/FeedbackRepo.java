package feedbackservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends MongoRepository<Feedback, String> {
}
