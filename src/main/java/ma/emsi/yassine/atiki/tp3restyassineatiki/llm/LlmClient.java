package ma.emsi.yassine.atiki.tp3restyassineatiki.llm;

import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.Dependent;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;

import java.io.StringReader;
import java.time.Duration;
import java.util.Set;

@Dependent
public class LlmClient {
    private static final Set<String> CHAMPS_DU_GUIDE =
            Set.of("ville_ou_pays", "endroits_a_visiter", "prix_moyen_repas");

    private final GuideTouristique guideTouristique;

    public LlmClient() {
        String apiKey = System.getenv("GEMINI_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("La variable d'environnement GEMINI_KEY est absente.");
        }
        GoogleGenAiChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-3.5-flash-lite")
                .timeout(Duration.ofSeconds(60))
                .maxRetries(0)
                .logRequestsAndResponses(false)
                .responseFormat(ResponseFormat.JSON)
                .build();
        guideTouristique = AiServices.builder(GuideTouristique.class)
                .chatModel(model)
                .build();
    }

    public String envoyer(String lieu) {
        try {
            return verifierReponse(guideTouristique.envoyer(lieu));
        } catch (RuntimeException erreur) {
            // Les exceptions du fournisseur peuvent contenir des détails de la requête.
            throw new IllegalStateException("Gemini n'a pas renvoyé de guide valide.");
        }
    }

    private String verifierReponse(String reponse) {
        try (JsonReader lecteur = Json.createReader(new StringReader(reponse))) {
            JsonObject guide = lecteur.readObject();
            if (!guide.keySet().equals(CHAMPS_DU_GUIDE)
                    || !estTexte(guide.get("ville_ou_pays"))
                    || !(guide.get("endroits_a_visiter") instanceof JsonArray endroits)
                    || endroits.size() != 2
                    || !endroits.stream().allMatch(LlmClient::estTexte)
                    || !estTexte(guide.get("prix_moyen_repas"))) {
                throw new IllegalArgumentException("Format de guide incorrect.");
            }
            return guide.toString();
        }
    }

    private static boolean estTexte(JsonValue valeur) {
        return valeur instanceof JsonString texte && !texte.getString().isBlank();
    }
}
