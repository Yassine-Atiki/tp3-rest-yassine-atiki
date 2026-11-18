package ma.emsi.yassine.atiki.tp3restyassineatiki.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/guide")
public class GuideTouristiqueResource {
    /**
     * Renvoie un tableau contenant le lieu demandé.
     *
     * @param lieu ville ou pays sur lequel porte la recherche
     * @return un tableau JSON contenant le nom du lieu demandé
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] endroitsDeVisite(@PathParam("ville_ou_pays") String lieu) {
        return new String[]{lieu};
    }
}
