package ma.emsi.yassine.atiki.tp3restyassineatiki.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import ma.emsi.yassine.atiki.tp3restyassineatiki.llm.LlmClient;

@Path("/guide")
public class GuideTouristiqueResource {
    @Inject
    private LlmClient llmClient;

    /**
     * Renvoie les endroits à visiter et le prix moyen d'un repas dans le lieu demandé.
     *
     * @param lieu ville ou pays sur lequel porte la recherche
     * @param nb nombre d'endroits à visiter, deux par défaut
     * @return les informations touristiques au format JSON
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String endroitsDeVisite(@PathParam("ville_ou_pays") String lieu,
                                  @QueryParam("nb") @DefaultValue("2") int nb) {
        if (nb < 1) {
            throw new BadRequestException("Le nombre d'endroits doit être supérieur à zéro.");
        }
        return llmClient.envoyer(lieu, nb);
    }
}
