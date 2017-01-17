package inproject.repository;

import inproject.entity.Store;
import inproject.entity.StoreSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(
    // "select new inproject.entity.StoreSearchResponse(s, count(s.id))" +
            "select s from Store s join s.tags t where s.place.name = :place " +
                    "and t.name in :tags group by s.id order by count(s.id) desc ")
    //public Stream<StoreSearchResponse> search(
    public List<Store> search(
            @NotNull@Param("tags")Set<String> tags,
            @NotNull@Param("place") String place);

    @Query("select s from Store s where s.place.name = :place")
    public List<Store> search(
            @NotNull@Param("place") String place);

    @Query(
            //"select new inproject.entity.StoreSearchResponse(s, count(s.id))" +
            "select s from Store s join s.tags t where t.name in :tags group by s.id order by count(s.id) desc")
    public List<Store> search(@NotNull@Param("tags") Set<String> tags);
}
