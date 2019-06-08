package lifantou.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class LifantouApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifantouApplication.class, args);
		
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//System.out.println(passwordEncoder.encode("Lif@KZ@Wn3i"));
	}
}

