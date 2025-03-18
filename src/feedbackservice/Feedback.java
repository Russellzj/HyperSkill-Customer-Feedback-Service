package feedbackservice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Document(collection = "feedback")
public class Feedback {
    @Getter
    @Id
    private String id;
    private int rating;
    private String feedback; //optional
    private String customer; //optional
    private String product;
    private String vendor;
}
