package fit.budle.util

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

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

    fun encodeStringToBase64(svgPath: String?): String? {
        return if (svgPath != null) {
            val bytes = svgPath.toByteArray()
            Base64.encodeToString(bytes, Base64.DEFAULT).replace("\n", "")
        } else null
    }
}