import java.io.Serializable;
import java.time.LocalDateTime;

public class Session implements Serializable {
    private static final long serialVersionUID = 1L;
    String subject;
    LocalDateTime startTime;
    LocalDateTime endTime;
}