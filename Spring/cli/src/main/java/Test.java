import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Caller implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
      system.out.println("Everething bad");
    }
}
