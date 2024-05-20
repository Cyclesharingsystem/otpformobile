# otpformobile
otpsend,validate

## Project Structure

### Controllers
- **OtpController**: Provides REST API endpoints for sending OTPs to mobile numbers and validating the received OTPs.

### DTOs
- **OtpRequest**: Data Transfer Object used for requesting an OTP to be sent to a mobile number.
- **OtpResponseDto**: Data Transfer Object used for the response of an OTP request.
- **OtpValidationRequest**: Data Transfer Object used for validating an OTP against a mobile number.

### Services
- **SmsService**: Contains business logic for sending OTPs via SMS and validating OTPs.

## Endpoints

### Process SMS
- **Endpoint**: `GET /otp/process`
- **Description**: A test endpoint to check if the SMS process is working.
- **Response**:
  ```json
  {
    "message": "SMS sent"
  }
### Send OTP
Endpoint: POST /otp/sendotp
Description: Sends an OTP to the specified mobile number.
Request Body:
 ```json
{
  "mobileNumber": "0774589989"
}
Response Body:
 ```json
{
  "otp": "123456",
  "message": "OTP sent successfully"
}

### Validate OTP
Endpoint: POST /otp/validateotp
Description: Validates the OTP for the specified mobile number.
Request Body:
 ```json
{
  "mobileNumber": "0774589989",
  "otpNumber": "123456"
}
Response Body:
 ```json
{
  "message": "OTP is valid"
}
 ```json
or
{
  "message": "OTP is invalid"
}




