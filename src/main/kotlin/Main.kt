const val STREAM_NAME = "people_stream"
const val CONSUMER_GROUP_NAME = "people_consumer_group"

fun main() {
    val data = readDataFromAFile()
    publishDataToRedisStream(data)
    readDataFromRedisStream()
}