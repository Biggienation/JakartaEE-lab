package org.mangastore.jee2025;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.mangastore.jee2025.dto.ReturningMangaRequest;
import org.mangastore.jee2025.entity.Manga;

@Path("mangas")
@Log
public class MangaResource {

    private MangaRepository mangaRepository;

    @Inject
    public MangaResource(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    public MangaResource() {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ReturningMangaRequest firstTest() {
        // Example response
        mangaRepository.save(new Manga("Test", 10, "Test", "1234567890"));
        return new ReturningMangaRequest("Test", 10, "Test", "1234567890");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Manga manga) {
        try {
            // Save the manga entity to the database
            mangaRepository.save(manga);

            // Return a success response with the created manga
            return Response.status(Response.Status.CREATED)
                    .entity(new ReturningMangaRequest("Test", 10, "Test", "1234567890"))
                    .build();
        } catch (Exception e) {
            // Log the error and return an error response
            log.severe("Error creating manga: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating manga: " + e.getMessage())
                    .build();
        }
    }
}