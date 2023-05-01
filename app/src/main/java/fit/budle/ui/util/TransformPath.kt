package fit.budle.ui.util

import android.graphics.Path
import android.graphics.RectF

fun Path.transformPath() : Pair<Path, RectF> {

    val bounds = RectF()
    val matrix = android.graphics.Matrix()

    this.computeBounds(bounds, true)
    this.offset(-bounds.left, -bounds.top)

    matrix.setScale(3f, 3f)
    this.transform(matrix)

    return Pair(this, bounds)
}