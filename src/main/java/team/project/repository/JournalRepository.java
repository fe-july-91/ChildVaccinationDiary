package team.project.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import team.project.model.Journal;

@NoRepositoryBean
public interface JournalRepository<T extends Journal> extends JpaRepository<T,Long> {

    Optional<T> findByIdAndChildId(Long id, Long childId);

    List<T> findAllByYearAndChildId(int year, Long childId);

    List<T> findAllByChildId(Long childId);
}
