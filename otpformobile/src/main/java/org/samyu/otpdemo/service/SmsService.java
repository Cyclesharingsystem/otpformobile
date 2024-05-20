//package org.samyu.otpdemo.service;
//
//
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import lombok.extern.slf4j.Slf4j;
//import org.samyu.otpdemo.config.TwilioConfig;
//import org.samyu.otpdemo.dto.OtpRequest;
//import org.samyu.otpdemo.dto.OtpResponseDto;
//import org.samyu.otpdemo.dto.OtpStatus;
//import org.samyu.otpdemo.dto.OtpValidationRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.text.DecimalFormat;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//import java.util.Set;
//
//@Service
//@Slf4j
//public class SmsService {
//
//	@Autowired
//	private TwilioConfig twilioConfig;
//    Map<String, String> otpMap = new HashMap<>();
//
//
//	public OtpResponseDto sendSMS(OtpRequest otpRequest) {
//		OtpResponseDto otpResponseDto = null;
//		try {
//			PhoneNumber to = new PhoneNumber(otpRequest.getMobileNumber());//to
//			PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber()); // from
//			String otp = generateOTP();
//			String otpMessage = "Dear Customer , Your OTP is  " + otp + " for sending sms through Spring boot application. Thank You.";
//			Message message = Message
//			        .creator(to, from,
//			                otpMessage)
//			        .create();
//			otpMap.put(otpRequest.getMobileNumber(), otp);
//			otpResponseDto = new OtpResponseDto(OtpStatus.DELIVERED, otpMessage);
//		} catch (Exception e) {
//			e.printStackTrace();
//			otpResponseDto = new OtpResponseDto(OtpStatus.FAILED, e.getMessage());
//		}
//		return otpResponseDto;
//	}
//
//	public String validateOtp(OtpValidationRequest otpValidationRequest) {
//		Set<String> keys = otpMap.keySet();
//		String mobileNumber = null;
//		for(String key : keys)
//			mobileNumber = key;
//        if (otpValidationRequest.getMobileNumber().equals(mobileNumber)) {
//            otpMap.remove(mobileNumber,otpValidationRequest.getOtpNumber());
//            return "OTP is valid!";
//        } else {
//            return "OTP is invalid!";
//        }
//	}
//
//	private String generateOTP() {
//        return new DecimalFormat("000000")
//                .format(new Random().nextInt(999999));
//    }
//
//}
package org.samyu.otpdemo.service;
import lombok.extern.slf4j.Slf4j;
import org.samyu.otpdemo.dto.OtpRequest;
import org.samyu.otpdemo.dto.OtpResponseDto;
import org.samyu.otpdemo.dto.OtpStatus;
import org.samyu.otpdemo.dto.OtpValidationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
@Slf4j
public class SmsService {

	private Map<String, String> otpMap = new HashMap<>();

	public OtpResponseDto sendSMS(OtpRequest otpRequest) {
		try {
			String mobileNumber = otpRequest.getMobileNumber();
			String otp = generateOTP();
			// You would integrate Twilio or another SMS service provider here to actually send the SMS
			log.info("OTP Sent to {}: {}", mobileNumber, otp);
			otpMap.put(mobileNumber, otp);
			return new OtpResponseDto(OtpStatus.DELIVERED, "OTP sent successfully");
		} catch (Exception e) {
			log.error("Error sending OTP: {}", e.getMessage());
			return new OtpResponseDto(OtpStatus.FAILED, e.getMessage());
		}
	}

	public String validateOtp(OtpValidationRequest otpValidationRequest) {
		String mobileNumber = otpValidationRequest.getMobileNumber();
		String otpNumber = otpValidationRequest.getOtpNumber();
		String storedOtp = otpMap.get(mobileNumber);
		if (storedOtp != null && storedOtp.equals(otpNumber)) {
			otpMap.remove(mobileNumber);
			return "OTP is valid!";
		} else {
			return "OTP is invalid!";
		}
	}

	private String generateOTP() {
		return new DecimalFormat("000000").format(new Random().nextInt(999999));
	}
}
