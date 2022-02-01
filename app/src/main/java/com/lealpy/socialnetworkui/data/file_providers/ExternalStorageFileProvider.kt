package com.lealpy.socialnetworkui.data.file_providers

import android.content.Context
import android.os.Environment
import java.io.File
import javax.inject.Inject

class ExternalStorageFileProvider @Inject constructor(
    private val appContext : Context
) {

    fun getFile() : File? {
        return if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File(appContext.getExternalFilesDir(null), EXTERNAL_STORAGE_FILE_NAME)
        }
        else null
    }

    companion object {
        private const val EXTERNAL_STORAGE_FILE_NAME = "message.txt"
    }

}
