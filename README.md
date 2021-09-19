# kotlin-datagouvfr-client

![Build](https://github.com/BapNesS/kotlin-datagouvfr-client/actions/workflows/build.yml/badge.svg) [![Licence](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# Usage

La librairie permet de fournir les outils suffisants pour accéder à l'API de [data.gouv.fr](https://data.gouv.fr).

Elle est utilisable sur Android.

# Périmètre

Les API suivantes sont disponibles :

| API	           | Couverture                                                            |
| ---------------- | :-------------------------------------------------------------------- |
| `site`           | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `spatial`        | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `issues`         | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `discussions`    | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `users`          | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `me`             | ![7%](https://img.shields.io/badge/-7%25-red?style=flat-square) |
| `datasets`       | ![7%](https://img.shields.io/badge/-2%25-red?style=flat-square) |
| `reuses`         | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `organizations`  | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `workers`        | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `tags`           | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `topics`         | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `posts`          | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `transfer`       | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `notifications`  | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `avatars`        | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `harvest`        | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |

Un endpoint est manquant ? Vous pouvez [ajouter une issue](https://github.com/BapNesS/kotlin-datagouvfr-client/issues/new?labels=enhancement&title=Ajouter+le+endpoint+%5BNom+du+endpoint%5D) pour demander son ajout, ou soyez génial(e) en faisant une PR.

<details>
<summary><strong>API dépréciées :</strong></summary>
<p>

| API | Méthode | Chemin | Nom |
|---|---|---|---|
| site | `GET` | `/metrics/{id}` | `metrics_for` |

</p>
</details>

# Clé d'API

Il est [nécessaire](https://doc.data.gouv.fr/api/intro/#authentification) de fournir une clé d'API pour les opérations en écriture (`POST`, `PUT`, `PATCH`, `DELETE`) ou pour l'utilisation du endpoint `me`. Cette clé d'API est disponible dans votre profil.

Elle peut être fournie lors de l'initialisation du service ou après :
```kotlin
val dgfrService = DgfrService(apiKey = VOTRE_CLE)

// ou
dgfrService.setApiKey(apiKey = VOTRE_CLE)
```

# [Suspend](https://kotlinlang.org/docs/async-programming.html#futures-promises-and-others)

Les fonctions d'appel aux APIs sont asynchrones.

```kotlin
suspend fun getDataset(id: String): Flow<Dataset?>
```

L'utilisation des coroutines est requis afin de pouvoir attendre la fin des fonctions `suspend`.

# Liens
* Plus d'information sur le fonctionnement de l'API : [https://doc.data.gouv.fr/api/intro/](https://doc.data.gouv.fr/api/intro/)
* Documentation de référence de l’API : [https://doc.data.gouv.fr/api/reference/](https://doc.data.gouv.fr/api/reference/)
* [Sonar](https://sonarcloud.io/dashboard?id=com.baptistecarlier.kotlin.datagouvfr%3Adatagouvfr-client) du projet

# Pré-requis

* JDK 1.8
* Android SDK
* Android S (API 31)

# Langages, librairies et outils utilisés
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