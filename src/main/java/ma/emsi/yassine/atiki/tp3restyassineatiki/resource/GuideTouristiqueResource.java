package ma.emsi.yassine.atiki.tp3restyassineatiki.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.emsi.yassine.atiki.tp3restyassineatiki.llm.LlmClient;

@Path("/guide")
public class GuideTouristiqueResource {
    @Inject
    private LlmClient llmClient;

    /**
     * Renvoie deux endroits à visiter et le prix moyen d'un repas dans le lieu demandé.
     *
     * @param lieu ville ou pays sur lequel porte la recherche
     * @return les informations touristiques au format JSON
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String endroitsDeVisite(@PathParam("ville_ou_pays") String lieu) {
        return llmClient.envoyer(lieu);
    }
}
