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
import org.springframework.web.bind.annotation.DeleteMapping;
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
import team.project.dto.foot.CreateFootRequestDto;
import team.project.dto.foot.FootDto;
import team.project.dto.foot.UpdateFootRequestDto;
import team.project.dto.height.CreateHeightRequestDto;
import team.project.dto.height.HeightDto;
import team.project.dto.height.UpdateHeightRequestDto;
import team.project.dto.weight.CreateWeightRequestDto;
import team.project.dto.weight.UpdateWeightRequestDto;
import team.project.dto.weight.WeightDto;
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

    @GetMapping("/{childId}")
    @Operation(summary = "Get a child card by id",
            description = "Get a child card by id for parent")
    public ChildDto getChildById(@AuthenticationPrincipal User user,
                                 @PathVariable @Positive Long childId) {
        return childService.getChildByIdAndUserId(user.getId(), childId);
    }

    @GetMapping
    @Operation(summary = "Get all parent's children",
            description = "Get all children for parent")
    public List<ChildDto> getChilds(@AuthenticationPrincipal User user) {
        return childService.getChildsByUser(user.getId());
    }

    @PutMapping("/{childId}")
    @Operation(summary = "Update all information of child by id",
            description = "Update child information by parent")
    public ChildDto updateChild(@AuthenticationPrincipal User user,
                                @PathVariable @Positive Long childId,
                                @RequestBody @Valid UpdateChildRequestDto newRequestDto) {
        return childService.updateChildByIdAndUserId(user.getId(), childId, newRequestDto);
    }

    @DeleteMapping("/{childId}")
    @Operation(summary = "Delete child's card by id",
            description = "Delete child's card by id (parent)")
    public void deleteChild(@AuthenticationPrincipal User user,
                            @PathVariable @Positive Long childId) {
        childService.deleteChildByIdAndUserId(user.getId(), childId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/{childId}")
    @Operation(summary = "Get any child card by id",
            description = "Get any child card by id for admin")
    public ChildDto getAnyChildById(@PathVariable @Positive Long childId) {
        return childService.getChildById(childId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    @Operation(summary = "Get all children",
            description = "Get all children for admin")
    public List<ChildDto> getAll(@ParameterObject @PageableDefault Pageable pageable) {
        return childService.getAll(pageable);
    }

    @GetMapping("/{childId}/height")
    @Operation(summary = "Get all info about kid's height",
            description = "Get all info about kid's height for parent")
    public List<HeightDto> getAllHeight(@AuthenticationPrincipal User user,
                                        @PathVariable @Positive Long childId) {
        return childService.getAllHeightByChildId(user.getId(), childId);
    }

    @PostMapping("/{childId}/height")
    @Operation(summary = "Add info about kid's height",
            description = "Add info about kid's height by parent")
    public HeightDto createHeight(@AuthenticationPrincipal User user,
                                  @PathVariable @Positive Long childId,
                                  @RequestBody @Valid CreateHeightRequestDto requestDto) {
        return childService.saveHeight(user.getId(), childId, requestDto);
    }

    @PutMapping("/{childId}/height/{heightId}")
    @Operation(summary = "Update height by id",
            description = "Update kid's height by id")
    public HeightDto updateHeight(@AuthenticationPrincipal User user,
                                  @PathVariable @Positive Long childId,
                                  @PathVariable @Positive Long heightId,
                                  @RequestBody @Valid UpdateHeightRequestDto requestDto) {
        return childService.updateHeight(user.getId(), childId, heightId, requestDto);
    }

    @DeleteMapping("/{childId}/height/{heightId}")
    @Operation(summary = "Delete height by id",
            description = "Delete kid's height by id")
    public String deleteHeight(@AuthenticationPrincipal User user,
                                  @PathVariable @Positive Long childId,
                                  @PathVariable @Positive Long heightId) {
        return childService.deleteHeight(user.getId(), childId, heightId);
    }

    @GetMapping("/{childId}/height/{year}")
    @Operation(summary = "Get all heights for a year by child",
            description = "Get all heights for a year by child")
    public List<HeightDto> getHeightsByYear(@AuthenticationPrincipal User user,
                                        @PathVariable @Positive Long childId,
                                        @PathVariable @Positive int year) {
        return childService.getAllHeightByYearAndChildId(user.getId(), childId, year);
    }

    @GetMapping("/{childId}/weight")
    @Operation(summary = "Get all info about kid's weight",
            description = "Get all info about kid's weight for parent")
    public List<WeightDto> getAllWeight(@AuthenticationPrincipal User user,
                                        @PathVariable @Positive Long childId) {
        return childService.getAllWeightByChildId(user.getId(), childId);
    }

    @PostMapping("/{childId}/weight")
    @Operation(summary = "Add info about kid's weight",
            description = "Add info about kid's weight by parent")
    public WeightDto createWeight(@AuthenticationPrincipal User user,
                                  @PathVariable @Positive Long childId,
                                  @RequestBody @Valid CreateWeightRequestDto requestDto) {
        return childService.saveWeight(user.getId(), childId, requestDto);
    }

    @PutMapping("/{childId}/weight/{weightId}")
    @Operation(summary = "Update weight by id",
            description = "Update kid's weight by id")
    public WeightDto updateWeight(@AuthenticationPrincipal User user,
                                  @PathVariable @Positive Long childId,
                                  @PathVariable @Positive Long weightId,
                                  @RequestBody @Valid UpdateWeightRequestDto requestDto) {
        return childService.updateWeight(user.getId(), childId, weightId, requestDto);
    }

    @DeleteMapping("/{childId}/weight/{weightId}")
    @Operation(summary = "Delete weight by id",
            description = "Delete kid's weight by id")
    public void deleteWeight(@AuthenticationPrincipal User user,
                               @PathVariable @Positive Long childId,
                               @PathVariable @Positive Long weightId) {
        childService.deleteWeight(user.getId(), childId, weightId);
    }

    @GetMapping("/{childId}/weight/{year}")
    @Operation(summary = "Get all weights for a year by child",
            description = "Get all weights for a year by child")
    public List<WeightDto> getWeightsByYear(@AuthenticationPrincipal User user,
                                            @PathVariable @Positive Long childId,
                                            @PathVariable @Positive int year) {
        return childService.getAllWeightByYearAndChildId(user.getId(), childId, year);
    }

    @GetMapping("/{childId}/foot")
    @Operation(summary = "Get all info about kid's foot measurements",
            description = "Get all info about kid's foot measurements for parent")
    public List<FootDto> getAllFoot(@AuthenticationPrincipal User user,
                                      @PathVariable @Positive Long childId) {
        return childService.getAllFootByChildId(user.getId(), childId);
    }

    @PostMapping("/{childId}/foot")
    @Operation(summary = "Add info about kid's foot measurement",
            description = "Add info about kid's foot measurement by parent")
    public FootDto createFoot(@AuthenticationPrincipal User user,
                                  @PathVariable @Positive Long childId,
                                  @RequestBody @Valid CreateFootRequestDto requestDto) {
        return childService.saveFoot(user.getId(), childId, requestDto);
    }

    @PutMapping("/{childId}/foot/{footId}")
    @Operation(summary = "Update foot measurement by id",
            description = "Update kid's foot measurement by id")
    public FootDto updateFoot(@AuthenticationPrincipal User user,
                                  @PathVariable @Positive Long childId,
                                  @PathVariable @Positive Long footId,
                                  @RequestBody @Valid UpdateFootRequestDto requestDto) {
        return childService.updateFoot(user.getId(), childId, footId, requestDto);
    }

    @DeleteMapping("/{childId}/foot/{footId}")
    @Operation(summary = "Delete foot measurement by id",
            description = "Delete kid's foot measurement by id")
    public void deleteFoot(@AuthenticationPrincipal User user,
                             @PathVariable @Positive Long childId,
                             @PathVariable @Positive Long footId) {
        childService.deleteFoot(user.getId(), childId, footId);
    }

    @GetMapping("/{childId}/foot/{year}")
    @Operation(summary = "Get all foot measurements for a year by child",
            description = "Get all foot measurements for a year by child")
    public List<FootDto> getFootsByYear(@AuthenticationPrincipal User user,
                                            @PathVariable @Positive Long childId,
                                            @PathVariable @Positive int year) {
        return childService.getAllFootByYearAndChildId(user.getId(), childId, year);
    }
}
