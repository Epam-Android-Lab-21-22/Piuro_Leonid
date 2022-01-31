package com.lealpy.socialnetworkui.data.file_providers

import android.content.Context
import java.io.File
import javax.inject.Inject

class InternalStorageFileProvider @Inject constructor(
    private val appContext : Context
) {

    fun getFile() : File {
        return File(appContext.filesDir, INTERNAL_STORAGE_FILE_NAME)
    }

    companion object {
        private const val INTERNAL_STORAGE_FILE_NAME = "message.txt"
    }

}
