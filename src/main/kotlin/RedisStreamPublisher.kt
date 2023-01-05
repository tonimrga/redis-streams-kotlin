fun publishDataToRedisStream(people: List<Person>) {
    redisConnection{commands ->
        for (person in people) {
            val personMessage = mapOf(
                "id" to person.id,
                "firstName" to person.firstName,
                "lastName" to person.lastName,
                "email" to person.email,
                "gender" to person.gender,
            )
            commands.xadd(STREAM_NAME, personMessage)
        }
    }
}