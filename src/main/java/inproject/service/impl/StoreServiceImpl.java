package inproject.service.impl;


import inproject.entity.Store;
import inproject.repository.BaseRepository;
import inproject.repository.StoreRepository;
import inproject.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StoreServiceImpl extends BaseService<Store> implements StoreService {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    EntityManager entityManager;




    @SuppressWarnings("unchecked")
    public List<Store> search(
            @NotNull Set<String> tags,
            @NotNull String place,
            int skip, int limit) {
        return (List<Store>) entityManager.createQuery(
                "select s from Store s join s.tags t where s.place.name = :place " +
                        "and t.name in :tags group by s.id order by count(s.id) desc")
                .setFirstResult(skip)
                .setMaxResults(limit)
                .setParameter("tags", tags)
                .setParameter("place", place)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Store> search(
            @NotNull String place,
            int skip, int limit) {
        return (List<Store>) entityManager.createQuery(
                "select s from Store s where s.place.name = :place")
                .setParameter("place", place)
                .setFirstResult(skip)
                .setMaxResults(limit)
                .getResultList();

    }

    @SuppressWarnings("unchecked")
    public List<Store> search(
            @NotNull Set<String> tags,
            int skip, int limit) {
        return (List<Store>) entityManager.createQuery(
                "select s from Store s join s.tags t where t.name in :tags" +
                        " group by s.id order by count(s.id) desc ")
                .setParameter("tags", tags)
                .setMaxResults(limit)
                .setFirstResult(skip)
                .getResultList();
    }


    @Override
    public List<Store> search(String tagsString, String place, int skip, int limit) {
        if (tagsString == null && place == null) {
            return storeRepository.findAll();
        }

        if (tagsString != null) {
            Set<String> tags = Stream.of(tagsString.split(",")).collect(Collectors.toSet());
            if (place == null) {
                return search(tags, skip, limit);
            } else {
                return search(tags, place, skip, limit);
            }
        } else {
            return search(place, skip, limit);
        }
    }

    @Override
    public BaseRepository<Store> getRepository() {
        return storeRepository;
    }
}
