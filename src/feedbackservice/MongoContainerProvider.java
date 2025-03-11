package feedbackservice;

import jakarta.annotation.PreDestroy;
import org.testcontainers.containers.MongoDBContainer;

import java.util.List;

public class MongoContainerProvider {
    private final MongoDBContainer container;

    public MongoContainerProvider(String mongodbImage) {
        container = new MongoDBContainer("mongo:8"); // image name
        container.withCreateContainerCmdModifier(cmd -> cmd.withName("feedback-service")); // container name
        container.addEnv("MONGO_INITDB_DATABASE", "feedback_db"); // init database
        container.setPortBindings(List.of("27017:27017")); // expose port 27017
        container.start();
    }

    @PreDestroy
    public void tearDown() {
        container.stop();
    }
}
