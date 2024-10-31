package team.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.model.User;
import team.project.service.ChildService;

@Tag(name = "Child manager", description = "Endpoints for managing childs")
@RequiredArgsConstructor
@RestController
@RequestMapping("/childs")
@Validated
public class ChildController {
    private final ChildService childService;

    @PostMapping
    @Operation(summary = "Create a new child for parent",
            description = "Create a new child entity in the database")
    public ChildDto createChild(@AuthenticationPrincipal User user,
                                @RequestBody @Valid CreateChildRequestDto requestDto) {
        return childService.save(user, requestDto);
    }

    @GetMapping
    @Operation(summary = "Get all childs",
            description = "Get all childs for parent")
    public List<ChildDto> getChilds(@AuthenticationPrincipal User user) {
        return childService.getChildsByUser(user.getId());
    }
}
