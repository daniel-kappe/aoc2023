import java.io.File

fun <T>readFile(fileName: String, lineConverter: (String) -> T): List<T> {
    val file = File(String.Companion::class.java.classLoader.getResource(fileName)?.path.orEmpty())
    if (file.isFile) {
        return file
            .readLines()
            .map(lineConverter)
    }
    return listOf()
}

fun <T>createMultiValueLineConverter(separator: String = ",", converter: (String) -> T): (String) -> List<T> {
    return { it.split(separator).map(converter) }
}