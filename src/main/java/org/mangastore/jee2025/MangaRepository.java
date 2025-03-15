package org.mangastore.jee2025;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import org.mangastore.jee2025.entity.Manga;


@Repository
public interface MangaRepository extends CrudRepository<Manga, Long> {

}
