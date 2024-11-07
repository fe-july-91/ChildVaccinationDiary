package team.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.project.dto.child.ChildDto;
import team.project.dto.child.CreateChildRequestDto;
import team.project.dto.child.UpdateChildRequestDto;
import team.project.model.User;
import team.project.service.ChildService;

@Tag(name = "Children manager", description = "Endpoints for managing children")
@RequiredArgsConstructor
@RestController
@RequestMapping("/children")
@Validated
public class ChildController {
    private final ChildService childService;

    @PostMapping
    @Operation(summary = "Create a new child card",
            description = "Create a new child card by parent")
    public ChildDto createChild(@AuthenticationPrincipal User user,
                                @RequestBody @Valid CreateChildRequestDto requestDto) {
        return childService.save(user, requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a child card by id",
            description = "Get a child card by id for parent")
    public ChildDto getChildById(@AuthenticationPrincipal User user,
                                 @PathVariable @Positive Long id) {
        return childService.getChildByIdAndUserId(user.getId(), id);
    }

    @GetMapping
    @Operation(summary = "Get parent's children",
            description = "Get all children for parent")
    public List<ChildDto> getChilds(@AuthenticationPrincipal User user) {
        return childService.getChildsByUser(user.getId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update all information of child by id",
            description = "Update child information by parent")
    public ChildDto updateChild(@AuthenticationPrincipal User user,
                                @PathVariable @Positive Long id,
                                @RequestBody @Valid UpdateChildRequestDto newRequestDto) {
        return childService.updateChildByIdAndUserId(user.getId(), id, newRequestDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/{id}")
    @Operation(summary = "Get any child card by id",
            description = "Get any child card by id for admin")
    public ChildDto getAnyChildById(@PathVariable @Positive Long id) {
        return childService.getChildById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    @Operation(summary = "Get all children",
            description = "Get all children for admin")
    public List<ChildDto> getAll(@ParameterObject @PageableDefault Pageable pageable) {
        return childService.getAll(pageable);
    }
}
