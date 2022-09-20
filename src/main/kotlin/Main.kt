import core.Startup
import kotlinx.cli.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val parser = ArgParser("BDVMonitoringBot")
    val interval by parser.option(
        ArgType.Int, shortName = "i", description = "Message sending interval"
    ).required()
    val unit by parser.option(
        ArgType.Choice<TimeUnit>(), shortName = "u",
        description = "Time unit"
    ).default(TimeUnit.MINUTES).multiple()

    parser.parse(args)

    Startup().run(unit.first().toMillis(interval.toLong()))
}