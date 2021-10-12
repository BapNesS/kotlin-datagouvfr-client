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
| `users`          | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `me`             | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `datasets`       | ![95%](https://img.shields.io/badge/-95%25-yellowgreen?style=flat-square) |
| `reuses`         | ![95%](https://img.shields.io/badge/-95%25-yellowgreen?style=flat-square) |
| `organizations`  | ![90%](https://img.shields.io/badge/-90%25-yellowgreen?style=flat-square) |
| `workers`        | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `tags`           | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `topics`         | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `posts`          | ![90%](https://img.shields.io/badge/-90%25-yellowgreen?style=flat-square) |
| `transfer`       | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `notifications`  | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `avatars`        | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `harvest`        | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |

Un endpoint est manquant ? Vous pouvez [ajouter une issue](https://github.com/BapNesS/kotlin-datagouvfr-client/issues/new?labels=enhancement&title=Ajouter+le+endpoint+%5BNom+du+endpoint%5D) pour demander son ajout, ou soyez génial(e) en faisant une PR.

<details>
<summary><strong>API en cours d'implémentation :</strong></summary>
<p>

| API | Méthode |  Nom |
|---|---|---|
 `users` | `POST` | `user_avatar` |
 `datasets` | `POST` | `upload_new_dataset_resource` |
 `datasets` | `POST` | `upload_new_community_resource` |
 `reuses` | `POST` | `reuse_image` |
 `organizations` | `POST` | `organization_logo` |
 `organizations` | `PUT` | `resize_organization_logo` |
 `organizations` | `POST` | `post_membership_request_api` |
 `posts` | `POST` | `post_image` |
 `posts` | `PUT` | `resize_post_image` |


</p>
</details>
<details>
<summary><strong>API dépréciées :</strong></summary>
<p>

| API | Méthode | Chemin | Nom |
|---|---|---|---|
| `site` | `GET` | `/metrics/{id}` | `metrics_for` |

</p>
</details>

#  Outils

## Pré-requis

* JDK 1.8
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

## [Suspend](https://kotlinlang.org/docs/async-programming.html#futures-promises-and-others)

Les fonctions d'appel aux endpoints sont asynchrones.

```kotlin
suspend fun getDataset(dataset: String): Flow<DgfrResource<Dataset>>
```

L'utilisation des coroutines est requis afin de pouvoir attendre la fin des fonctions `suspend`.

## Flow & DgfrResource

Les functions d'appel aux endpoint retournent un `Flow` d'objet `DgfrResource`.
Vous pouvez accéder aux [sources](https://github.com/BapNesS/kotlin-datagouvfr-client/tree/develop/lib-client/src/main/java/com/baptistecarlier/kotlin/datagouvfr/client/exception/DgfrResource.kt) pour savoir comment les gérer dans votre app.


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

# Liens
* Plus d'information sur le fonctionnement de l'API : [https://doc.data.gouv.fr/api/intro/](https://doc.data.gouv.fr/api/intro/)
* Documentation de référence de l’API : [https://doc.data.gouv.fr/api/reference/](https://doc.data.gouv.fr/api/reference/)
* [Sonar](https://sonarcloud.io/dashboard?id=com.baptistecarlier.kotlin.datagouvfr%3Adatagouvfr-client) du projet
