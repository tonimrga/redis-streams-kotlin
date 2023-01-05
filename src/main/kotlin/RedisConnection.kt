import io.lettuce.core.RedisClient
import io.lettuce.core.api.sync.RedisCommands

fun redisConnection(fn: (commands: RedisCommands<String, String>) -> Unit ) {
    val redisClient = RedisClient.create("redis://localhost:6379")
    val connection = redisClient.connect()
    val commands = connection.sync()

    fn(commands)

    connection.close();
    redisClient.shutdown();
}