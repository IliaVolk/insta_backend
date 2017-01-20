package inproject.repository;

import inproject.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends BaseRepository<Place> {
    @Query("select p from Place p where p.confirmed = :confirmed")
    List<Place> findAll(@Param("confirmed")boolean confirmed);
}
