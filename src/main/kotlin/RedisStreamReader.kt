import io.lettuce.core.*

fun readDataFromRedisStream() {
    redisConnection { commands ->
        try {
            commands.xgroupCreate(XReadArgs.StreamOffset.from(STREAM_NAME, "0-0"), CONSUMER_GROUP_NAME)
        } catch (redisBusyException: RedisBusyException) {
            println("Consumer group already exists. Continuing with the existing one...")
        }

        println("Consumer waiting for new messages...");

        while (true) {
            val people: List<StreamMessage<String, String>> = commands.xreadgroup(
                Consumer.from(CONSUMER_GROUP_NAME, "people_consumer_1"),
                XReadArgs.StreamOffset.lastConsumed(STREAM_NAME)
            )

            if (people.isNotEmpty()) {
                for (person in people) {
                    println(person)
                    commands.xack(STREAM_NAME, CONSUMER_GROUP_NAME, person.id)
                }
            }
        }
    }
}