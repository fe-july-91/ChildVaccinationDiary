package team.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.model.User;
import team.project.service.ChildService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/childs")
@Validated
public class ChildController {
    private final ChildService childService;

    @PostMapping
    public ChildDto createChild(@AuthenticationPrincipal User user,
                                @RequestBody @Valid CreateChildRequestDto requestDto) {
        return childService.save(user, requestDto);
    }
}
