fun readDataFromAFile(): List<Person> {
    val file = object {}.javaClass.getResourceAsStream("mock_data_1K.CSV");
    val reader = file.bufferedReader()

    // skip header line
    reader.readLine()
    return reader.lineSequence()
        .filter { it.isNotBlank() }
        .map {
            val (id, first_name, last_name, email, gender) = it.split(',', ignoreCase = false, limit = 5)
            Person(id, first_name, last_name, email, gender)
        }.toList()
}