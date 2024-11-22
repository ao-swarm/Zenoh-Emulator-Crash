import java.util.Properties

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            val localProperties = Properties()
            val localPropertiesFile = File(settingsDir, "local.properties")
            if (localPropertiesFile.exists()) {
                localProperties.load(localPropertiesFile.inputStream())
            }
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/eclipse-zenoh/zenoh-kotlin")
            credentials {
                username = localProperties.getProperty("github.user")
                password = localProperties.getProperty("github.token")
            }
        }
    }
}

rootProject.name = "Zenoh Emulator Crash"
include(":app")
 