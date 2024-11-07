pluginManagement {
    plugins {
        kotlin("jvm") version "2.0.21"
    }
}
rootProject.name = "ZAFUnity"
include("AI")
include("common")
include("user")
include("post")
include("minio")
include("service")
include("model")
include("gateway")
