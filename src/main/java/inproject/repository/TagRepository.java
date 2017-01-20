package inproject.repository;

import inproject.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends BaseRepository<Tag> {
    @Query("select t from Tag t where t.confirmed = :confirmed")
    List<Tag> findAll(@Param("confirmed") boolean confirmed);
}
