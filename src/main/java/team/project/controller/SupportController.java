package team.project.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.project.dto.suppport.CreateEmailRequestDto;
import team.project.service.EmailService;

@Tag(name = "Support manager", description = "Endpoints for managing support")
@RequiredArgsConstructor
@RestController
@RequestMapping("/support")
@Validated
public class SupportController {
    private final EmailService emailService;

    @PostMapping("/send-request-to-email")
    public String sendEmailToSupport(@RequestBody @Valid CreateEmailRequestDto requestDto) {
        emailService.sendEmailToSupport(requestDto);
        return "Ваше повідомлення було надіслано";
    }
}
