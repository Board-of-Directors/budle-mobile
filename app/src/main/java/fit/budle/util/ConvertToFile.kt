package fit.budle.util

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun Uri.convertToFile(context: Context): File? {
    return try {

        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(this)
        val tempFile = File(context.cacheDir, "temp_file")

        inputStream?.use { input ->
            FileOutputStream(tempFile).use { output ->
                input.copyTo(output)
            }
        }

        tempFile

    } catch (e: IOException) {
        throw RuntimeException(e)
    }
}