# kotlin-datagouvfr-client

![Build](https://github.com/BapNesS/kotlin-datagouvfr-client/actions/workflows/build.yml/badge.svg) [![Licence](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

# Usage

La librairie permet de fournir les outils suffisants pour accéder à l'API de [data.gouv.fr](https://data.gouv.fr).

# Périmètre

| API	           | Couverture                                                            |
| ---------------- | :-------------------------------------------------------------------- |
| `site`           | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `spatial`        | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `issues`         | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `discussions`    | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `users`          | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `me`             | ![7%](https://img.shields.io/badge/-7%25-red?style=flat-square) |
| `datasets`       | ![7%](https://img.shields.io/badge/-2%25-red?style=flat-square) |
| `reuses`         | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `organizations`  | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `workers`        | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `tags`           | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `topics`         | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `posts`          | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |
| `transfer`       | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `notifications`  | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `avatars`        | ![100%](https://img.shields.io/badge/-100%25-success?style=flat-square) |
| `harvest`        | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square) |

# Liens
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
