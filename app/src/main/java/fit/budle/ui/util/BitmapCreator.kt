package fit.budle.ui.util

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun bitmapCreator(vararg uris: Uri): List<Bitmap> {
    val bitmapList = mutableListOf<Bitmap>()
    for (uri in uris) {
        val source = ImageDecoder.createSource(LocalContext.current.contentResolver, uri)
        bitmapList.add(ImageDecoder.decodeBitmap(source))
    }
    return bitmapList
}