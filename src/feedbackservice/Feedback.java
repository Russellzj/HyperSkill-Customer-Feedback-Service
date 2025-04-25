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
    @Getter
    private int rating;
    @Getter
    private String feedback; //optional
    @Getter
    private String customer; //optional
    @Getter
    private String product;
    @Getter
    private String vendor;


}
