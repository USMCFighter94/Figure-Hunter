object Deps {
    const val exposed = "org.jetbrains.exposed:exposed:${Versions.exposed}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    object Ktor {
        const val core = "io.ktor:ktor:${Versions.ktor}"
        const val netty = "io.ktor:ktor-server-netty:${Versions.ktor}"
        const val gson = "io.ktor:ktor-gson:${Versions.ktor}"
    }

    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"

    const val postgresql = "org.postgresql:postgresql:${Versions.postgresql}"
}
