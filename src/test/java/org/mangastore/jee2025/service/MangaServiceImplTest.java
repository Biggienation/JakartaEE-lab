package org.mangastore.jee2025.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mangastore.jee2025.dto.CreateMangaRequest;
import org.mangastore.jee2025.dto.ReturningMangaRequest;
import org.mangastore.jee2025.dto.UpdateMangaRequest;
import org.mangastore.jee2025.entity.Manga;
import org.mangastore.jee2025.exception.ResourceNotFoundException;
import org.mangastore.jee2025.exception.ValidationException;
import org.mangastore.jee2025.repository.MangaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MangaServiceImplTest {

    @Mock
    private MangaRepository mangaRepository;

    @InjectMocks
    private MangaServiceImpl mangaService;

    private Manga manga;
    private CreateMangaRequest createRequest;
    private UpdateMangaRequest updateRequest;

    @BeforeEach
    void setUp() {
        manga = new Manga();
        manga.setId(1L);
        manga.setTitle("Naruto");
        manga.setPages(200);
        manga.setAuthor("Masashi Kishimoto");
        manga.setISBN("978-4-08-873546-4");
        manga.setDescription("A story about a young ninja.");
        manga.setDate("1999, 9, 21");

        createRequest = new CreateMangaRequest();
        createRequest.setTitle("Naruto");
        createRequest.setPages(200);
        createRequest.setAuthor("Masashi Kishimoto");
        createRequest.setISBN("978-4-08-873546-4");
        createRequest.setDescription("A story about a young ninja.");
        createRequest.setDate("1999, 9, 21");

        updateRequest = new UpdateMangaRequest();
        updateRequest.setTitle("Naruto Shippuden");
        updateRequest.setPages(500);
        updateRequest.setAuthor("Masashi Kishimoto");
        updateRequest.setDescription("The continuation of Naruto's story.");
        updateRequest.setDate("2007, 2, 15");
    }

    @Test
    void testGetAllMangas() {
        when(mangaRepository.findAll()).thenReturn(List.of(manga).stream());
        List<ReturningMangaRequest> result = mangaService.getAllMangas();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Naruto", result.get(0).getTitle());
        verify(mangaRepository, times(1)).findAll();
    }

    @Test
    void testGetMangaById() {
        when(mangaRepository.findById(1L)).thenReturn(Optional.of(manga));
        Optional<ReturningMangaRequest> result = mangaService.getMangaById(1L);
        assertTrue(result.isPresent());
        assertEquals("Naruto", result.get().getTitle());
        verify(mangaRepository, times(1)).findById(1L);
    }

    @Test
    void testGetMangaByISBN() {
        when(mangaRepository.findByISBN("978-4-08-873546-4")).thenReturn(Optional.of(manga));
        Optional<ReturningMangaRequest> result = mangaService.getMangaByISBN("978-4-08-873546-4");
        assertTrue(result.isPresent());
        assertEquals("Naruto", result.get().getTitle());
        verify(mangaRepository, times(1)).findByISBN("978-4-08-873546-4");
    }

    @Test
    void testGetMangasByAuthor() {
        when(mangaRepository.findByAuthor("Masashi Kishimoto")).thenReturn(List.of(manga));
        List<ReturningMangaRequest> result = mangaService.getMangasByAuthor("Masashi Kishimoto");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Naruto", result.get(0).getTitle());
        verify(mangaRepository, times(1)).findByAuthor("Masashi Kishimoto");
    }

    @Test
    void testGetMangasByTitle() {
        when(mangaRepository.findByTitleIgnoreCase("Naruto")).thenReturn(List.of(manga));
        List<ReturningMangaRequest> result = mangaService.getMangasByTitle("Naruto");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Naruto", result.get(0).getTitle());
        verify(mangaRepository, times(1)).findByTitleIgnoreCase("Naruto");
    }

    @Test
    void testCreateManga() {
        when(mangaRepository.findByISBN(any())).thenReturn(Optional.empty());
        when(mangaRepository.findByTitleAndAuthor(any(), any())).thenReturn(Optional.empty());
        when(mangaRepository.save(any())).thenReturn(manga);
        ReturningMangaRequest result = mangaService.createManga(createRequest);
        assertNotNull(result);
        assertEquals("Naruto", result.getTitle());
        verify(mangaRepository, times(1)).save(any());
    }

    @Test
    void testCreateManga_ValidationException_ISBNExists() {
        when(mangaRepository.findByISBN(any())).thenReturn(Optional.of(manga));
        assertThrows(ValidationException.class, () -> mangaService.createManga(createRequest));
        verify(mangaRepository, never()).save(any());
    }

    @Test
    void testCreateManga_ValidationException_TitleAndAuthorExist() {
        when(mangaRepository.findByISBN(any())).thenReturn(Optional.empty());
        when(mangaRepository.findByTitleAndAuthor(any(), any())).thenReturn(Optional.of(manga));
        assertThrows(ValidationException.class, () -> mangaService.createManga(createRequest));
        verify(mangaRepository, never()).save(any());
    }

    @Test
    void testUpdateManga() {
        when(mangaRepository.findById(1L)).thenReturn(Optional.of(manga));
        when(mangaRepository.update(any())).thenReturn(manga);
        Optional<ReturningMangaRequest> result = mangaService.updateManga(1L, updateRequest);
        assertTrue(result.isPresent());
        assertEquals("Naruto Shippuden", result.get().getTitle());
        verify(mangaRepository, times(1)).update(any());
    }

    @Test
    void testUpdateManga_NotFound() {
        when(mangaRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> mangaService.updateManga(1L, updateRequest));
        verify(mangaRepository, never()).update(any());
    }

    @Test
    void testDeleteManga() {
        when(mangaRepository.findById(1L)).thenReturn(Optional.of(manga));
        doNothing().when(mangaRepository).delete(any());
        boolean result = mangaService.deleteManga(1L);
        assertTrue(result);
        verify(mangaRepository, times(1)).delete(any());
    }

    @Test
    void testDeleteManga_NotFound() {
        when(mangaRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> mangaService.deleteManga(1L));
        verify(mangaRepository, never()).delete(any());
    }
}