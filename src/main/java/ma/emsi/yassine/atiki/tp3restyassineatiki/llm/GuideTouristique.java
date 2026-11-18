package ma.emsi.yassine.atiki.tp3restyassineatiki.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristique {
    @SystemMessage("""
            Tu es un guide touristique. Pour la ville ou le pays demandé, indique les deux
            principaux endroits à visiter et le prix moyen d'un repas dans la devise locale.
            Réponds uniquement avec un objet JSON, sans balises Markdown, contenant exactement ces champs.
            {
              "ville_ou_pays": "nom de la ville ou du pays",
              "endroits_a_visiter": ["nom du premier endroit", "nom du deuxième endroit"],
              "prix_moyen_repas": "montant et code de la devise locale"
            }
            """)
    @UserMessage("Donne les informations touristiques pour {{lieu}}.")
    String envoyer(@V("lieu") String lieu);
}
