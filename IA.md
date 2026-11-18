# Utilisation de l’IA dans le TP3 REST

**Yassine Atiki — 5ᵉ année, groupe G2, EMSI Casablanca**

J’ai démarré le TP avec le projet Jakarta EE dans IntelliJ et vérifié le point d’entrée Hello World. Pour la suite, j’ai précisé les consignes à suivre, les scénarios à tester et les résultats à relever. J’ai utilisé Codex pour clarifier les étapes et m’assister dans la mise en œuvre du code et les tests sur Payara.

## Prompts et réponses

Ces exemples reformulent les points techniques de ma demande initiale. Les réponses donnent l’explication correspondante.

### Lire les paramètres d’une requête REST

**Prompt**

> Comment récupérer le nom de la ville dans `/api/guide/lieu/Nice` et lire le nombre de lieux demandé avec `?nb=4` ?

**Réponse**

`@PathParam("ville_ou_pays")` récupère `Nice` dans le chemin. `@QueryParam("nb")` lit la valeur `4` dans la requête. `@DefaultValue("2")` fournit la valeur `2` quand le paramètre est omis.

### Choisir le statut HTTP

**Prompt**

> Comment utiliser le type `Response` pour tester un succès `200` et une erreur `500` dans `HelloResource` ?

**Réponse**

`Response.ok("Hello, " + nom).build()` renvoie le message avec le statut `200`. Pour l’essai d’erreur, `Response.serverError().build()` renvoie `500`. Les deux variantes se vérifient sur la même URL après chaque redéploiement.

### Relier le guide touristique à Gemini

**Prompt**

> Comment réutiliser le client Gemini du TP2 pour obtenir un guide en JSON avec LangChain4j ?

**Réponse**

`AiServices` relie l’interface `GuideTouristique` au modèle Gemini. Le client lit `GEMINI_KEY` dans l’environnement de Payara et demande une réponse JSON. La ressource REST expose cette réponse avec le type `application/json`.

## Vérifications sur Payara

La route pour Mohamed a renvoyé `200` avec `Hello, Mohamed`. La variante d’erreur a renvoyé `500`. Le guide préparatoire a renvoyé `["Nice"]`. Le guide final a renvoyé deux lieux pour Paris par défaut et quatre avec `?nb=4`.
