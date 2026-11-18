# Utilisation de l’IA dans le TP3 REST

**Yassine Atiki — 5ᵉ année, groupe G2, EMSI Casablanca**

J’ai demandé à Codex de reprendre mon projet Jakarta EE, de suivre l’énoncé de Richard Grin et de tester chaque jalon sur Payara. Les demandes ci-dessous sont des extraits d’un même message, présentés dans l’ordre du TP. Les réponses résument les modifications et les vérifications effectuées par Codex. Gemini est le modèle interrogé par l’application pour produire le guide touristique.

## 1. Vérifier le projet de départ

**Ma demande**

> Vérifie l’état réel de ma copie et reprends à la première étape incomplète.

**Réponse de Codex, résumée**

Le dépôt pointait vers le TP3 et partait du commit `7b275ef`. La méthode `hello` prenait déjà le nom en paramètre, mais il manquait la déclaration de la classe et son annotation `@Path("/hello-world")`. Codex a rétabli la classe et conservé la compilation avec Java 25.

## 2. Tester le paramètre de chemin et les statuts HTTP

**Ma demande**

> Vérifie que `GET /api/hello-world/personnes/Mohamed` affiche `Hello, Mohamed`, puis fais le commit et le push demandés. Passe ensuite au type `Response` et teste les variantes HTTP 200 et 500, en redéployant entre les deux.

**Réponse de Codex, résumée**

La route a répondu `200 OK` avec `Hello, Mohamed`. Après passage à `Response`, Codex a déployé la variante `Response.ok(...)` et observé `200`, puis a déployé `Response.serverError()` et observé `500`. La version finale utilise `Response.ok(...)`.

## 3. Construire la ressource du guide

**Ma demande**

> Teste d’abord le retour du lieu, puis le tableau `String[]` à un élément, sérialisé en JSON.

**Réponse de Codex, résumée**

La méthode `endroitsDeVisite` lit le lieu avec `@PathParam("ville_ou_pays")`. Deux déploiements successifs ont donné `200 OK` avec `Nice` en texte, puis `200 OK` avec `["Nice"]` et le type `application/json` à la même adresse. [URL testée pour Nice](http://localhost:8080/tp3-rest-yassine-atiki-1.0-SNAPSHOT/api/guide/lieu/Nice)

## 4. Réutiliser le client Gemini du TP2

**Ma demande**

> Retrouve mon projet `tp2-web-yassine-atiki`, vérifie son `pom.xml` et réutilise son `LlmClient`.

**Réponse de Codex, résumée**

Codex a repris le client du TP2 et l’a adapté à `GuideTouristique` avec `AiServices`, l’injection CDI et un rôle système fixe en français. Le client lit la clé dans la variable d’environnement `GEMINI_KEY` du processus Payara. L’appel réel pour Nice a renvoyé les trois clés attendues, deux lieux et un prix annoncé de `25 EUR`. Les pages de correction Gemini ont renvoyé `403`, donc cette étape s’appuie sur l’énoncé et le client du TP2. [Énoncé du TP3](http://richard.grin.free.fr/emsi/casablanca-ia/tp/tp3-rest/index.html#apillm)

## 5. Vérifier le bonus

**Ma demande**

> Implémente la variante prévue par le prof avec `?nb=4` et une valeur par défaut de `2`. Transmets le nombre au service Gemini.

**Réponse de Codex, résumée**

Codex a ajouté `@QueryParam("nb")` et `@DefaultValue("2")`, puis vérifié le nombre d’éléments dans les réponses de Gemini. Sur Payara, Paris a renvoyé deux lieux avec la valeur par défaut et quatre avec `nb=4`. Les deux requêtes ont répondu `200 OK`. [URL testée pour le bonus](http://localhost:8080/tp3-rest-yassine-atiki-1.0-SNAPSHOT/api/guide/lieu/Paris?nb=4)

Corps JSON observé pour le bonus

```json
{"ville_ou_pays":"Paris","endroits_a_visiter":["Tour Eiffel","Musée du Louvre","Cathédrale Notre-Dame de Paris","Avenue des Champs-Élysées"],"prix_moyen_repas":"25 EUR"}
```

Le WAR final a passé `mvn clean verify` avec Java 25 et a été testé sur Payara 7.2026.10. Les contrôles portent sur les statuts HTTP, les clés du JSON et le nombre de lieux renvoyés. [Historique des commits du TP3](https://github.com/Yassine-Atiki/tp3-rest-yassine-atiki/commits/master/)
