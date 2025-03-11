package feedbackservice;

import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Document(collection = "feedback")
public class Feedback {
    @Id
    @Generated
    private String id;
    private int rating;
    private String feedback; //optional
    private String customer; //optional
    private String product;
    private String vendor;

    public Feedback(int rating, String product, String vendor) {
        this.rating = rating;
        this.product = product;
        this.vendor = vendor;
    }

    public Feedback(int rating, String product, String vendor, String feedback, String customer) {
        this(rating, product, vendor);
        this.feedback = feedback;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getCustomer() {
        return customer;
    }

    public String getProduct() {
        return product;
    }

    public String getVendor() {
        return vendor;
    }
}
