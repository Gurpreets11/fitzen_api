package com.infodart.fitzen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infodart.fitzen.dto.ApiResponseDto;
import com.infodart.fitzen.dto.EmailRequestDto;
import com.infodart.fitzen.util.EmailSender;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailSender emailSender;  // existing class

	/*
	 * @PostMapping("/send") public ResponseEntity<String> sendEmail(@RequestBody
	 * EmailRequestDto request) {
	 * 
	 * try { emailSender.sendEmail(request.getTo(), request.getSubject(),
	 * request.getMessage()); return ResponseEntity.ok("Email sent successfully!");
	 * } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	 * .body("Failed to send email: " + e.getMessage()); } }
	 */
    
    @GetMapping("/home")
    public String home() {
        return "Fitzen email Running";
    }
    
    @PostMapping("/send")
    public ResponseEntity<ApiResponseDto> sendEmail(@RequestBody EmailRequestDto request) {
        try {
            emailSender.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
            return ResponseEntity.ok(new ApiResponseDto(true, "Email sent successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDto(false, "Error sending email: " + e.getMessage()));
        }
    }
}
