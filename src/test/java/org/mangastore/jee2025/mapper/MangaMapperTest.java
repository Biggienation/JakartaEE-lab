package org.mangastore.jee2025.mapper;

import org.junit.jupiter.api.Test;
import org.mangastore.jee2025.dto.CreateMangaRequest;
import org.mangastore.jee2025.dto.ReturningMangaRequest;
import org.mangastore.jee2025.dto.UpdateMangaRequest;
import org.mangastore.jee2025.entity.Manga;

import static org.junit.jupiter.api.Assertions.*;

class MangaMapperTest {

    @Test
    void testToEntityFromCreateMangaRequest() {
        CreateMangaRequest request = new CreateMangaRequest();
        request.setTitle("Naruto");
        request.setPages(200);
        request.setAuthor("Masashi Kishimoto");
        request.setISBN("978-4-08-873546-4");
        request.setDescription("A story about a young ninja.");
        request.setDate ("1999, 9, 21");

        Manga manga = MangaMapper.toEntity(request);

        assertNotNull(manga);
        assertEquals("Naruto", manga.getTitle());
        assertEquals(200, manga.getPages());
        assertEquals("Masashi Kishimoto", manga.getAuthor());
        assertEquals("978-4-08-873546-4", manga.getISBN());
        assertEquals("A story about a young ninja.", manga.getDescription());
        assertEquals("1999, 9, 21", manga.getDate());
    }

    @Test
    void testToEntityFromUpdateMangaRequest() {
        Manga existingManga = new Manga();
        existingManga.setTitle("Naruto");
        existingManga.setPages(200);
        existingManga.setAuthor("Masashi Kishimoto");
        existingManga.setISBN("978-4-08-873546-4");
        existingManga.setDescription("A story about a young ninja.");
        existingManga.setDate("1999, 9, 21");

        UpdateMangaRequest request = new UpdateMangaRequest();
        request.setTitle("Naruto Shippuden");
        request.setPages(500);
        request.setAuthor("Masashi Kishimoto");
        request.setDescription("The continuation of Naruto's story.");
        request.setDate("2007, 2, 15");

        Manga updatedManga = MangaMapper.toEntity(request, existingManga);

        assertNotNull(updatedManga);
        assertEquals("Naruto Shippuden", updatedManga.getTitle());
        assertEquals(500, updatedManga.getPages());
        assertEquals("Masashi Kishimoto", updatedManga.getAuthor());
        assertEquals("978-4-08-873546-4", updatedManga.getISBN()); // ISBN should remain unchanged
        assertEquals("The continuation of Naruto's story.", updatedManga.getDescription());
        assertEquals("2007, 2, 15", updatedManga.getDate());
    }

    @Test
    void testToResponseFromManga() {
        Manga manga = new Manga();
        manga.setTitle("One Piece");
        manga.setPages(1000);
        manga.setAuthor("Eiichiro Oda");
        manga.setISBN("978-4-08-873562-4");
        manga.setDescription("A story about pirates.");
        manga.setDate("1997, 7, 22");

        ReturningMangaRequest response = MangaMapper.toResponse(manga);

        // Assert
        assertNotNull(response);
        assertEquals("One Piece", response.getTitle());
        assertEquals(1000, response.getPages());
        assertEquals("Eiichiro Oda", response.getAuthor());
        assertEquals("978-4-08-873562-4", response.getISBN());
        assertEquals("A story about pirates.", response.getDescription());
        assertEquals("1997, 7, 22", response.getDate());
    }
}