package org.mangastore.jee2025;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.mangastore.jee2025.dto.CreateMangaRequest;
import org.mangastore.jee2025.dto.ReturningMangaRequest;
import org.mangastore.jee2025.dto.UpdateMangaRequest;
import org.mangastore.jee2025.entity.Manga;
import org.mangastore.jee2025.mapper.MangaMapper;
import org.mangastore.jee2025.repository.MangaRepository;

import java.util.List;
import java.util.Optional;

@Path("mangas")
@Log
public class MangaResource {

    private MangaRepository mangaRepository;

    @Inject
    public MangaResource(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    @SuppressWarnings("unused")
    public MangaResource() {}

    //GET Http
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Manga> mangas = mangaRepository.findAll().toList();
        return Response.ok(mangas).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByTitle(@QueryParam("title") String title) {
        List<Manga> mangas = mangaRepository.findByTitleIgnoreCase(title);
        return Response.ok(mangas).build();
    }

    @GET
    @Path("/author/{author}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByAuthor(@PathParam("author") String author) {
        List<Manga> mangas = mangaRepository.findByAuthor(author);
        return Response.ok(mangas).build();
    }

    @GET
    @Path("/isbn/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByISBN(@PathParam("isbn") String isbn) {
        Optional<Manga> manga = mangaRepository.findByISBN(isbn);
        if (manga.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(manga).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        Optional<Manga> manga = mangaRepository.findById(id);
        if (manga.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ReturningMangaRequest response = MangaMapper.toResponse(manga.orElse(null));
        return Response.ok(response).build();
    }

    // POST http
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreateMangaRequest request) {
        Manga manga = MangaMapper.toEntity(request);
        mangaRepository.save(manga);
        return Response.status(Response.Status.CREATED).build();
    }

    //PUT https
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, UpdateMangaRequest request) {
        Optional<Manga> manga = mangaRepository.findById(id);
        if (manga.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        MangaMapper.toEntity(request, manga.get());
        mangaRepository.update(manga.get());
        return Response.ok().build();
    }

    //DELETE http
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Optional<Manga> manga = mangaRepository.findById(id);
        if (manga.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        mangaRepository.delete(manga.get());
        return Response.noContent().build();
        //Should this be ok or noContent?
    }
}
