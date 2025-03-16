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
import org.mangastore.jee2025.service.MangaService;
import org.mangastore.jee2025.service.MangaServiceImpl;

import java.util.List;
import java.util.Optional;

@Path("mangas")
@Log
public class MangaResource {

    private MangaService mangaService;

    @Inject
    public MangaResource(MangaServiceImpl mangaService) {
        this.mangaService = mangaService;
    }

    @SuppressWarnings("unused")
    public MangaResource() {}

    //GET Http
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<ReturningMangaRequest> mangas = mangaService.getAllMangas();
        return Response.ok(mangas).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByTitle(@QueryParam("title") String title) {
        List<ReturningMangaRequest> mangas = mangaService.getMangasByTitle(title);
        return Response.ok(mangas).build();
    }

    @GET
    @Path("/author/{author}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByAuthor(@PathParam("author") String author) {
        List<ReturningMangaRequest> mangas = mangaService.getMangasByAuthor(author);
        return Response.ok(mangas).build();
    }

    @GET
    @Path("/isbn/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByISBN(@PathParam("isbn") String isbn) {
        Optional<ReturningMangaRequest> manga = mangaService.getMangaByISBN(isbn);
        if (manga.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(manga).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        Optional<ReturningMangaRequest> manga = mangaService.getMangaById(id);
        if (manga.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(manga).build();
    }

    // POST http
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CreateMangaRequest request) {
        ReturningMangaRequest manga = mangaService.createManga(request);
        return Response.status(Response.Status.CREATED).entity(manga).build();
    }

    //PUT https
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, UpdateMangaRequest request) {
        Optional<ReturningMangaRequest> mangaResponse = mangaService.updateManga(id, request);
        if (mangaResponse.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mangaResponse.get()).build();
    }

    //DELETE http
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = mangaService.deleteManga(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
