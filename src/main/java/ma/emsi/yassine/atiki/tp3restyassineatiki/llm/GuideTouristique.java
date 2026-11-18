package ma.emsi.yassine.atiki.tp3restyassineatiki.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristique {
    @SystemMessage("""
            Tu es un guide touristique. Pour la ville ou le pays demandé, recommande les
            principaux endroits à visiter et donne le prix moyen d'un repas dans la devise locale.
            Réponds uniquement avec un objet JSON, sans balises Markdown, contenant exactement ces champs.
            - "ville_ou_pays" contient le nom de la ville ou du pays.
            - "endroits_a_visiter" est un tableau de noms avec exactement le nombre d'éléments demandé.
            - "prix_moyen_repas" contient le montant et le code de la devise locale dans une chaîne.
            """)
    @UserMessage("Pour {{lieu}}, donne exactement {{nb}} endroits à visiter.")
    String envoyer(@V("lieu") String lieu, @V("nb") int nb);
}
