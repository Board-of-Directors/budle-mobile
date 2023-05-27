package fit.budle.util

import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File

class FileEncoder {
    fun decodeBase64toSVG(base64: String?): String? {
        return if (base64 != null) {
            String(Base64.decode(base64, Base64.DEFAULT))
        } else return null
    }

    fun encodeBitmapToBase64(imageBitmap: Bitmap?): String? {
        return if (imageBitmap != null) {
            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val byteArray = baos.toByteArray()
            Base64.encodeToString(byteArray, Base64.DEFAULT).replace("\n", "")
        } else return null
    }

    fun encodeFileToBase64(uri: Uri?): String? {
        return if (uri != null) {
            val downloadDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = uri.path?.let { File(downloadDir, "map.svg") }
            if (file != null) {
                val bytes = file.readBytes();
                Base64.encodeToString(bytes, Base64.DEFAULT).replace("\n", "")
            } else null
        } else null
    }
}