package com.personx.decloak

import android.content.ClipData
import android.content.Context
import android.os.Handler
import android.os.Looper

class ClipboardManagerHelper(val context: Context) {

    private val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    private val handler = Handler(Looper.getMainLooper())
    private var lastCopiedText: CharSequence? = null

    fun copyText(text: String, label: String = "Copied Text", timeout: Long = 60000L) {

        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        lastCopiedText = text

        handler.postDelayed({
            if (clipboard.primaryClip?.getItemAt(0)?.text == lastCopiedText) {
                clipboard.setPrimaryClip(ClipData.newPlainText("", ""))
            }
        }, timeout)
    }

    fun clearClipboard() {
        clipboard.setPrimaryClip(ClipData.newPlainText("", ""))
        lastCopiedText = null
    }

    fun getCurrentClipboardText(): CharSequence? {
        return clipboard.primaryClip?.getItemAt(0)?.text
    }

}