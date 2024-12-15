plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

dependencies {
    testImplementation(kotlin("test"))
}

application {
    mainClass = "com.klox.LoxKt"
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    wrapper {
        gradleVersion = project.properties["gradle.version"].toString()
    }
}
