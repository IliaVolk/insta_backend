package inproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<E> extends JpaRepository<E, Long> {
    List<E> findAll(@Param("confirmed")boolean confirmed);
}
