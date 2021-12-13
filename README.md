# kotlin-datagouvfr-client

[![Licence](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) ![Build](https://github.com/BapNesS/kotlin-datagouvfr-client/actions/workflows/build.yml/badge.svg) [![Codefactor.io](https://www.codefactor.io/repository/github/bapness/kotlin-datagouvfr-client/badge?style=plastic)](https://www.codefactor.io/repository/github/bapness/kotlin-datagouvfr-client) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.baptistecarlier.kotlin.datagouvfr%3Adatagouvfr-client&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=com.baptistecarlier.kotlin.datagouvfr%3Adatagouvfr-client) [![Maven](https://img.shields.io/maven-metadata/v.svg?label=maven-central&metadataUrl=https://repo1.maven.org/maven2/com/baptistecarlier/kotlin/datagouvfr/kotlin-datagouv-client/maven-metadata.xml)](https://search.maven.org/artifact/com.baptistecarlier.kotlin.datagouvfr/kotlin-datagouv-client)

# 📥 Usage

La librairie permet de fournir les outils pour accéder à l'API de [data.gouv.fr](https://data.gouv.fr).

Elle est utilisable sur Android à travers la dépendance disponible sur [Maven Central](https://search.maven.org/artifact/com.baptistecarlier.kotlin.datagouvfr/kotlin-datagouv-client).

**Kotlin DSL :**

```
implementation("com.baptistecarlier.kotlin.datagouvfr:kotlin-datagouv-client:1.x")
```

<details>
<summary><strong>Gradle DSL :</strong></summary>
<p>

```
implementation 'com.baptistecarlier.kotlin.datagouvfr:kotlin-datagouv-client:1.x'
```

</p>
</details>

# 🗃️ Périmètre

## API

Toutes les API sont disponibles mais peut-être avez-vous vu un endpoint est manquant ?

Dans ce cas, vous pouvez [ajouter une issue](https://github.com/BapNesS/kotlin-datagouvfr-client/issues/new?labels=enhancement&title=Ajouter+le+endpoint+%5BNom+du+endpoint%5D) pour demander son ajout, ou soyez génial(e) en faisant une PR.

<details>
<summary><strong>Certaines API sont dépréciées :</strong></summary>
<p>

| API | Méthode | Chemin | Nom |
|---|---|---|---|
| `site` | `GET` | `/metrics/{id}` | `metrics_for` |

</p>
</details>

## Modèles

L'annotation `MissingFieldMapping` indique qu'un le modèle ne correspond pas (encore) exactement au modèle défini par l'API.

# 🧰 Outils

## Pré-requis

* Gradle JDK Java 11
* Android SDK
* Android S (API 31)

## Clé d'API

Il est [nécessaire](https://doc.data.gouv.fr/api/intro/#authentification) de fournir une clé d'API pour les opérations en écriture (`POST`, `PUT`, `PATCH`, `DELETE`) ou pour l'utilisation des endpoints `me`,  `notifications`. Cette clé d'API est disponible dans votre profil.

Elle peut être fournie lors de l'initialisation du service ou après :
```kotlin
val dgfrService = DgfrService(apiKey = VOTRE_CLE)

// ou
dgfrService.setApiKey(apiKey = VOTRE_CLE)
```

## Flow & DgfrCallState

Les functions d'appel aux endpoint retournent un `Flow` d'objet `DgfrCallState`.
Vous pouvez accéder aux [sources](https://github.com/BapNesS/kotlin-datagouvfr-client/tree/develop/lib-client/src/main/java/com/baptistecarlier/kotlin/datagouvfr/client/DgfrCallState.kt) pour savoir comment les gérer dans votre app.

## Langages & librairies

**Lib :**
* `Kotlin`
* `Kotlin Coroutines`
* `Kotlinx DateTime`
* `Ktor`

**App :**
* `Google Material Components`
* `AndroidX Navigation library`
* `AndroidX Paging3`
* `AndroidX DataStore`
* `Hilt`
* `Jetpack Compose`
* `Timber`

# 🔗 Liens
* Liste des versions sur [Maven Central](https://search.maven.org/artifact/com.baptistecarlier.kotlin.datagouvfr/kotlin-datagouv-client)
* Plus d'information sur le fonctionnement de l'API : [https://doc.data.gouv.fr/api/intro/](https://doc.data.gouv.fr/api/intro/)
* Documentation de référence de l’API : [https://doc.data.gouv.fr/api/reference/](https://doc.data.gouv.fr/api/reference/)
* [Sonar](https://sonarcloud.io/dashboard?id=com.baptistecarlier.kotlin.datagouvfr%3Adatagouvfr-client) du projet