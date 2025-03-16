package org.mangastore.jee2025.service;

import org.mangastore.jee2025.dto.CreateMangaRequest;
import org.mangastore.jee2025.dto.ReturningMangaRequest;
import org.mangastore.jee2025.dto.UpdateMangaRequest;

import java.util.List;
import java.util.Optional;

public interface MangaService {

    List<ReturningMangaRequest> getAllMangas();

    Optional<ReturningMangaRequest> getMangaById(Long id);

    Optional<ReturningMangaRequest> getMangaByISBN(String ISBN);

    ReturningMangaRequest createManga(CreateMangaRequest request);

    Optional<ReturningMangaRequest> updateManga(Long id, UpdateMangaRequest request);

    boolean deleteManga(Long id);
}