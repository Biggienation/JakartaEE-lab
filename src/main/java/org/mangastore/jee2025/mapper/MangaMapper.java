package org.mangastore.jee2025.mapper;

import org.mangastore.jee2025.dto.CreateMangaRequest;
import org.mangastore.jee2025.dto.ReturningMangaRequest;
import org.mangastore.jee2025.dto.UpdateMangaRequest;
import org.mangastore.jee2025.entity.Manga;

public class MangaMapper {

    // Convert CreateMangaRequest to Manga entity
    public static Manga toEntity(CreateMangaRequest request) {
        Manga manga = new Manga();
        manga.setTitle(request.getTitle());
        manga.setPages(request.getPages());
        manga.setAuthor(request.getAuthor());
        manga.setISBN(request.getISBN());
        manga.setDescription(request.getDescription());
        manga.setDate(request.getDate());
        return manga;
    }

    // Convert UpdateMangaRequest to Manga entity
    public static Manga toEntity(UpdateMangaRequest request, Manga manga) {
        manga.setTitle(request.getTitle());
        manga.setPages(request.getPages());
        manga.setAuthor(request.getAuthor());
        manga.setDescription(request.getDescription());
        manga.setDate(request.getDate());
        return manga;
    }

    // Convert Manga entity to ReturningMangaRequest
    public static ReturningMangaRequest toResponse(Manga manga) {
        return new ReturningMangaRequest(
                manga.getTitle(),
                manga.getPages(),
                manga.getAuthor(),
                manga.getISBN(),
                manga.getDescription(),
                manga.getDate()
        );
    }
}
