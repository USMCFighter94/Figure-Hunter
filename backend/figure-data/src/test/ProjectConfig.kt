import io.kotlintest.AbstractProjectConfig

class ProjectConfig : AbstractProjectConfig() {
    override fun parallelism(): Int = 4
}
