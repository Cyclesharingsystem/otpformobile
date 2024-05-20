package org.samyu.otpdemo.controller;
import lombok.extern.slf4j.Slf4j;
import org.samyu.otpdemo.dto.OtpRequest;
import org.samyu.otpdemo.dto.OtpResponseDto;
import org.samyu.otpdemo.dto.OtpValidationRequest;
import org.samyu.otpdemo.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@Slf4j
public class OtpController {

	@Autowired
	private SmsService smsService;
	
	@GetMapping("/process")
	public String processSMS() {
		return "SMS sent";
	}

	@PostMapping("/sendotp")
	public OtpResponseDto sendOtp(@RequestBody OtpRequest otpRequest) {
		log.info("inside sendOtp :: "+otpRequest.getMobileNumber());
		return smsService.sendSMS(otpRequest);
	}
	@PostMapping("/validateotp")
    public String validateOtp(@RequestBody OtpValidationRequest otpValidationRequest) {
		log.info("inside validateOtp :: "+otpValidationRequest.getMobileNumber()+" "+otpValidationRequest.getOtpNumber());
		return smsService.validateOtp(otpValidationRequest);
    }
}
  