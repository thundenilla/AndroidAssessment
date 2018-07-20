package com.amalinamakhtar.assessment

import java.io.*
import java.util.*

class CSVHelper(private val inputStream: InputStream) {

    fun read(): List<String> {
        val result = ArrayList<String>()
        val reader = BufferedReader(InputStreamReader(inputStream) as Reader?)
        try {
            var csvLine = reader.readLine()
            do {
                val line = csvLine.split("\\n").toString()
                if (!line.isBlank()) {
                    result.add(line)
                }
                csvLine = reader.readLine() ?: ""
            } while (csvLine != "")

        } catch (exception: IOException) {
            throw IOException("Error in reading CSV file: $exception")
        } finally {
            try {
                inputStream.close()
            } catch (exceptionClose: IOException) {
                throw RuntimeException("Error while closing CSV: $exceptionClose")
            }
        }
        return result
    }

}