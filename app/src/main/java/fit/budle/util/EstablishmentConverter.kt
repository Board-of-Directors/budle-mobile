package fit.budle.util

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import fit.budle.dto.customer_user.User
import fit.budle.dto.establishment.Establishment
import fit.budle.dto.establishment.EstablishmentDto
import fit.budle.dto.tag.standard.IconTag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class EstablishmentConverter {
    companion object {
        fun convertEstablishment(
            establishment: EstablishmentDto,
            scope: CoroutineScope,
        ): Establishment {

            var decodedImage: BitmapPainter? = null
            val decodedTagsIcons: ArrayList<IconTag> = arrayListOf()
            val decodedPhotos: ArrayList<BitmapPainter?> = arrayListOf()

            scope.launch {
                if (establishment.image != null) {
                    val imageBytes: ByteArray = Base64.decode(establishment.image, Base64.DEFAULT)
                    val factory = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    if (factory != null) {
                        decodedImage = BitmapPainter(
                            factory.asImageBitmap()
                        )
                    }
                }
                if (establishment.photos != null) {
                    for (photo in establishment.photos) {
                        val imageBytes: ByteArray = Base64.decode(photo.image, Base64.DEFAULT)
                        val factory = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                        if (factory != null) {
                            decodedImage = BitmapPainter(
                                factory.asImageBitmap()
                            )
                            decodedPhotos.add(decodedImage)
                        }
                    }
                }

                if (establishment.tags != null) {
                    establishment.tags.forEach {
                        var decodedIcon: BitmapPainter? = null
                        if (it.image != null) {
                            val imageBytes: ByteArray = Base64.decode(it.image, Base64.DEFAULT)
                            decodedIcon = BitmapPainter(
                                BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                                    .asImageBitmap()
                            )
                        }
                        decodedTagsIcons.add(IconTag(name = it.name, image = decodedIcon))
                    }

                }
            }
            return Establishment(
                establishment.id,
                establishment.name,
                establishment.description,
                establishment.address,
                User("Олег"),
                establishment.hasCardPayment,
                establishment.hasMap,
                establishment.map,
                establishment.category,
                decodedImage,
                establishment.rating,
                establishment.price,
                establishment.workingHours,
                decodedTagsIcons,
                decodedPhotos,
                establishment.cuisineCountry,
                establishment.starsCount
            )
        }
    }
}