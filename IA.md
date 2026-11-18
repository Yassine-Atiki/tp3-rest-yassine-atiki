# Utilisation de l’IA dans le TP3 REST

**Yassine Atiki — 5ᵉ année, groupe G2, EMSI Casablanca**

J’ai démarré le TP avec le projet Jakarta EE dans IntelliJ et vérifié le point d’entrée Hello World. Pour la suite, j’ai précisé les consignes à suivre, les scénarios à tester et les résultats à relever. J’ai utilisé Codex pour clarifier les étapes et m’assister dans la mise en œuvre du code et les tests sur Payara.

## Points techniques

- **Paramètres REST** — `@PathParam` récupère une valeur dans le chemin de l’URL. `@QueryParam` lit une valeur comme `nb=4`, et `@DefaultValue("2")` permet de conserver deux lieux par défaut.
- **Réponses HTTP** — Le type `Response` permet de choisir le statut envoyé au client. Le guide utilise `application/json` pour exposer le résultat.
- **Intégration de Gemini** — `AiServices` relie l’interface `GuideTouristique` au modèle Gemini. Le client reprend la configuration du TP2 et lit la clé dans l’environnement de Payara.

## Vérifications sur Payara

La route pour Mohamed a renvoyé `200` avec `Hello, Mohamed`. La variante d’erreur a renvoyé `500`. Le guide préparatoire a renvoyé `["Nice"]`. Le guide final a renvoyé deux lieux pour Paris par défaut et quatre avec `?nb=4`.
