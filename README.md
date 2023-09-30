# Depend on it
```
repositories {
    maven {
        // Maven of dragons.plus
        url "https://maven.dragons.plus/releases"
    }
}

dependencies {
    modImplementation("plus.dragons.createdragonlib:create_dragon_lib-${minecraft_version}:${create_dragon_lib_version}")
}
```