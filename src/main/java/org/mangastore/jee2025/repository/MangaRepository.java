package org.mangastore.jee2025.repository;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import org.mangastore.jee2025.entity.Manga;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Repository
public interface MangaRepository extends CrudRepository<Manga, Long> {

    // Find all mangas
    @Find
    Stream<Manga> findAll();

    // Find mangas by title (case-insensitive)
    // Does not work as intended atm
    @Find
    @Query("SELECT m FROM Manga m WHERE LOWER(m.title) = LOWER(:title)")
    List<Manga> findByTitleIgnoreCase(String title);

    // Find mangas by author
    @Find
    @Query("SELECT m FROM Manga m WHERE m.author = :author")
    List<Manga> findByAuthor(String author);

    // Find mangas by ISBN
    @Query("SELECT m FROM Manga m WHERE m.ISBN = :ISBN")
    Optional<Manga> findByISBN(String ISBN);

    // FInd mangas by title and author
    @Query("SELECT m FROM Manga m WHERE m.title = :title AND m.author = :author")
    Optional<Manga> findByTitleAndAuthor(String title, String author);
}
