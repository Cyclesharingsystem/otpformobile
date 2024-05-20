package org.samyu.otpdemo.service;


import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.samyu.otpdemo.config.TwilioConfig;
import org.samyu.otpdemo.dto.OtpApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class OtpService {

	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}

	public static void main(String[] args) {
		SpringApplication.run(OtpApplication.class, args);
	}

}
