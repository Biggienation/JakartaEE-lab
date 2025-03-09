package org.mangastore.jee2025;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.mangastore.jee2025.dto.MangaResponse;

import java.util.ArrayList;
import java.util.List;

@Path("mangas")
public class MangaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MangaResponse firstTest() {
        return new MangaResponse("Tomie",752,
                "Junji Ito","9781421590561");
    }

    @GET
    @Path("many")
    @Produces(MediaType.APPLICATION_JSON)
    public Mangas secondTest(){
        List<MangaResponse> mangas = new ArrayList<>();
        mangas.add(firstTest());

        return new Mangas(mangas);
    }

    public record Mangas(List<MangaResponse> mangas) {}

}
