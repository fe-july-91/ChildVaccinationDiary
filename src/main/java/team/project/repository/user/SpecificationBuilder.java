package team.project.repository.user;

import team.project.dto.child.ChildSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(ChildSearchParametersDto childSearchParametersDto);
}
