package inproject.repository;

import inproject.entity.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends BaseRepository<Store> {
    @Query("select s from Store s where s.confirmed = :confirmed")
    List<Store> findAll(@Param("confirmed") boolean confirmed);

    @Query("select s from Store s where s.user.id = :id")
    List<Store> findAllByUserId(@Param("id") Long id);
}
