import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("fabric-loom") version "0.12-SNAPSHOT"
}

val minecraftVersion: String by project
val loaderVersion: String by project

dependencies {
    minecraft("com.mojang", "minecraft", minecraftVersion)
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc", "fabric-loader", loaderVersion)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    withType<ProcessResources> {
        filteringCharset = Charsets.UTF_8.name()
        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }

    withType<RemapJarTask> {
        archiveBaseName.set("ResourcePackTimer")
    }
}