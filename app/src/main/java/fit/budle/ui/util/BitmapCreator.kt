package fit.budle.ui.util

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
fun bitmapCreator(vararg uris: Uri, resolver: ContentResolver): List<Bitmap> {
    val bitmapList = mutableListOf<Bitmap>()
    for (uri in uris) {
        val source = ImageDecoder.createSource(resolver, uri)
        bitmapList.add(ImageDecoder.decodeBitmap(source))
    }
    return bitmapList
}