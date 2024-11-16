import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.appdistribution) apply false
    alias(libs.plugins.firebase.performance) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
}

dependencies {
    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.compose)
}

val detektConfigFile = files("$rootDir/config/detekt/detekt.yml")
val detektBaselineFile = file("$rootDir/config/detekt/detekt-baseline.xml")

tasks {
    register(name = "detektAll", type = Detekt::class) {
        val noBaseline = project.hasProperty("noBaseline")

        description = "Runs a detekt build."
        buildUponDefaultConfig = true
        parallel = true
        ignoreFailures = false

        setSource(file(projectDir))
        if (!noBaseline) {
            baseline.set(detektBaselineFile)
        }
        config.setFrom(detektConfigFile)

        include("**/*.kt")
        exclude(
            "**/resources/**",
            "**/build/**",
            "**/test/**",
            "**/androidTest/**"
        )
        reports {
            html.required.set(true)
            xml.required.set(true)
            txt.required.set(true)
            md.required.set(true)
            sarif.required.set(false)
        }
    }

    register(name = "detektGenerateBaseLine", type = DetektCreateBaselineTask::class) {
        description = "Overrides current Detekt baseline."
        buildUponDefaultConfig.set(true)
        parallel.set(true)
        ignoreFailures.set(false)

        setSource(files(rootDir))
        config.setFrom(detektConfigFile)
        baseline.set(file(detektBaselineFile))

        include(
            "**/*.kt",
            "**/*.kts"
        )

        exclude(
            "**/resources/**",
            "**/build/**"
        )
    }
}