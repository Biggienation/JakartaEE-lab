package org.mangastore.jee2025;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import org.mangastore.jee2025.entity.Manga;

@Repository
public interface MangaRepository extends CrudRepository<Manga, Long> {
    /*@Override
    default <S extends Manga> S save(S entity) {
        return null;
    }

    @Override
    default Optional<Manga> findById(Long id) {
        return Optional.empty();
    }*/
}
