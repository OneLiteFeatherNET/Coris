plugins {
    java
    jacoco
    `java-library`
    `maven-publish`
    alias(libs.plugins.publishdata)
}

group = "net.theevilreaper.kali"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(platform(libs.microtus.bom))

    compileOnly(libs.phoca)
    compileOnly(libs.minestom)

    testImplementation(platform(libs.microtus.bom))
    testImplementation(libs.minestom)
    testImplementation(libs.minestom.test)
    testImplementation(libs.phoca)
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
}

tasks {
    java {
        compileJava {
            options.release.set(21)
            options.encoding = "UTF-8"
        }
    }

    jacocoTestReport {
        dependsOn(test)
        reports {
            xml.required.set(true)
        }
    }
    test {
        finalizedBy(jacocoTestReport)
        useJUnitPlatform()
        jvmArgs("-Dminestom.inside-test=true")
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

publishData {
    addBuildData()
    useGitlabReposForProject("ID", "https://gitlab.onelitefeather.dev/")
    publishTask("jar")
}

publishing {
    publications.create<MavenPublication>("maven") {
        // configure the publication as defined previously.
        publishData.configurePublication(this)
        version = publishData.getVersion(false)
    }

    repositories {
        maven {
            credentials(HttpHeaderCredentials::class) {
                name = "Job-Token"
                value = System.getenv("CI_JOB_TOKEN")
            }
            authentication {
                create("header", HttpHeaderAuthentication::class)
            }


            name = "Gitlab"
            // Get the detected repository from the publishing data
            url = uri(publishData.getRepository())
        }
    }
}