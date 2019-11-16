package com.youtubedl.data.local.model

import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore.Video.Thumbnails.MINI_KIND
import com.youtubedl.util.FileUtil.Companion.getFileSize
import java.io.File

/**
 * Created by cuongpm on 1/12/19.
 */

data class LocalVideo constructor(
    var file: File
) {

    var size: String = ""
        get() = getFileSize(file.length().toDouble())

    val thumbnail: Bitmap?
        get() = ThumbnailUtils.createVideoThumbnail(file.path, MINI_KIND)

}