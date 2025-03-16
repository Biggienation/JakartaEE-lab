package org.mangastore.jee2025.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mangastore.jee2025.dto.CreateMangaRequest;
import org.mangastore.jee2025.dto.ReturningMangaRequest;
import org.mangastore.jee2025.dto.UpdateMangaRequest;
import org.mangastore.jee2025.entity.Manga;
import org.mangastore.jee2025.exception.ResourceNotFoundException;
import org.mangastore.jee2025.exception.ValidationException;
import org.mangastore.jee2025.mapper.MangaMapper;
import org.mangastore.jee2025.repository.MangaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class MangaServiceImpl implements MangaService {

    @Inject
    private MangaRepository mangaRepository;

    @Override
    public List<ReturningMangaRequest> getAllMangas() {
        return mangaRepository.findAll()
                .map(MangaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReturningMangaRequest> getMangaById(Long id) {
        return mangaRepository.findById(id)
                .map(MangaMapper::toResponse);
    }

    @Override
    public Optional<ReturningMangaRequest> getMangaByISBN(String ISBN) {
        return mangaRepository.findByISBN(ISBN)
                .map(MangaMapper::toResponse);
    }

    @Override
    public List<ReturningMangaRequest> getMangasByAuthor(String author) {
        return mangaRepository.findByAuthor(author)
                .stream()
                .map(MangaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReturningMangaRequest> getMangasByTitle(String title) {
        return mangaRepository.findByTitleIgnoreCase(title)
                .stream()
                .map(MangaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReturningMangaRequest createManga(CreateMangaRequest request) {
        if (mangaRepository.findByISBN(request.getISBN()).isPresent()) {
            throw new ValidationException("A manga with this ISBN already exists.");
        }

        Manga manga = MangaMapper.toEntity(request);
        mangaRepository.save(manga);
        return MangaMapper.toResponse(manga);
    }

    @Override
    public Optional<ReturningMangaRequest> updateManga(Long id, UpdateMangaRequest request) {
        Optional<Manga> mangaOptional = mangaRepository.findById(id);
        if (mangaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Manga with ID " + id + " not found.");
        }

        Manga manga = mangaOptional.get();
        manga.setTitle(request.getTitle());
        manga.setPages(request.getPages());
        manga.setAuthor(request.getAuthor());

        mangaRepository.update(manga);
        return Optional.of(MangaMapper.toResponse(manga));
    }

    @Override
    public boolean deleteManga(Long id) {
        Optional<Manga> mangaOptional = mangaRepository.findById(id);
        if (mangaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Manga with ID " + id + " not found.");
        }

        mangaRepository.delete(mangaOptional.get());
        return true;
    }
}