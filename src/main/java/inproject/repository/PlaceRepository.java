package inproject.repository;

import inproject.entity.Place;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends BaseRepository<Place> {
    @Query("select p from Place p where p.confirmed = :confirmed")
    List<Place> findAll(@Param("confirmed")boolean confirmed);
    @Query("select p from Place p where p.user.id = :id")
    List<Place> findAllByUserId(@Param("id") Long id);
}
