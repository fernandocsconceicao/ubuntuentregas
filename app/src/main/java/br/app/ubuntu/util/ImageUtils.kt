package br.com.ubuntu.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.io.InputStream

object ImageUtils {
    fun compressImage(iStream: InputStream, maxWidth: Int, maxHeight: Int, quality: Int): ByteArray? {
        val bitmap = BitmapFactory.decodeStream(iStream)
        val scaledBitmap = createScaledBitmap(bitmap, maxWidth, maxHeight)
        return compressBitmap(scaledBitmap, quality)
    }

    private fun createScaledBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        val scale = Math.min(maxWidth.toFloat() / bitmap.width, maxHeight.toFloat() / bitmap.height)
        val finalWidth = (bitmap.width * scale).toInt()
        val finalHeight = (bitmap.height * scale).toInt()
        return Bitmap.createScaledBitmap(bitmap, finalWidth, finalHeight, true)
    }

    private fun compressBitmap(bitmap: Bitmap, quality: Int): ByteArray? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
        return baos.toByteArray()
    }
}