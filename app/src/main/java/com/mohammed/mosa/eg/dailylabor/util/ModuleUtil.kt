package com.mohammed.mosa.eg.dailylabor.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.print.PrintAttributes
import android.print.PrintJob
import android.print.PrintManager
import android.webkit.WebView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.mohammed.mosa.eg.dailylabor.R
import com.mohammed.mosa.eg.dailylabor.util.DASH_LINE_20

import java.util.Date
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi



fun dateStatus(date: Date, context: Context): String {
    val daysUntilExpiry = ((date.time - Date().time) / (1000 * 60 * 60 * 24)).toInt()
    return when{
        daysUntilExpiry  <= 0 -> " (${context.getString(R.string.expired)}) "
        daysUntilExpiry < 30 -> " (${context.getString(R.string.days, daysUntilExpiry.toString())}) "
        else -> " (${context.getString(R.string.valid)}) "

    }
}




@OptIn(ExperimentalEncodingApi::class)
fun String.encodeToBase64(): String{
    return  Base64.Default.encode(this.encodeToByteArray())
}




fun Intent.shareData(data: String, context: Context): Intent{
    this.also {
        it.setAction(Intent.ACTION_SEND)
        it.setType("text/plain")
        it.putExtra(Intent.EXTRA_TEXT,data)
        if (it.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(it, context.getString(R.string.share_to)))
        }
    }
    return this
}

fun Intent.dailPhone(phone: String, context: Context): Intent{
    this.also {
        it.setAction(Intent.ACTION_DIAL)
        it.data = Uri.parse("tel:$phone")
        //it.putExtra(Intent.EXTRA_TEXT, )
        if (it.resolveActivity(context.packageManager) != null) {
            context.startActivity(it)
        }
    }
    return this
}

fun Intent.sendEmail(email: String, context: Context): Intent{
    this.also {
        it.setAction(Intent.ACTION_SENDTO)
        it.data = Uri.parse("mailto:$email")

        if (it.resolveActivity(context.packageManager) != null) {
            context.startActivity(it)
        }
    }
    return this

}


fun Intent.openMap(latitude: Double, longitude: Double, context: Context): Intent{
    this.also {
        it.setAction(Intent.ACTION_VIEW)
        it.data = Uri.parse("geo:$latitude,$longitude")
        if (it.resolveActivity(context.packageManager) != null) {
            context.startActivity(it)
        }
    }
    return this
}



fun String.formatIban(): String{
    return this.chunked(4).joinToString(" ")
}

fun String.formatPhoneNumber(): String{
    return this.chunked(3).joinToString("-")
}




fun createWebViewPrint(context: Context, webView: WebView): PrintJob? {
    try {
        // Get print manager instance
        val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager

        // Create print adapter
        val printAdapter =
            webView.createPrintDocumentAdapter("Document")

        // Create job name
        val jobName = "Document_${System.currentTimeMillis()}"

        // Create print attributes
        val attributes = PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
            .setResolution(PrintAttributes.Resolution("pdf", "pdf", 600, 600))
            .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
            .build()

        // Start print job
        return printManager.print(jobName, printAdapter, attributes).also {
            Toast.makeText(context, "Preparing document for printing...", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Failed to print: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        e.printStackTrace()
        return null
    }
}

// Extension function version
fun WebView.createPrint(context: Context): PrintJob? {
    return createWebViewPrint(context, this)
}

// Usage example with print job callback
fun printWebViewWithCallback(context: Context, webView: WebView, onComplete: (Boolean) -> Unit) {
    val printJob = createWebViewPrint(context, webView)

    printJob?.let { job ->
        // Monitor print job
        Thread {
            while (!job.isCompleted && !job.isFailed && !job.isCancelled) {
                Thread.sleep(100)
            }

            // Notify completion on main thread
            android.os.Handler(context.mainLooper).post {
                onComplete(job.isCompleted && !job.isFailed)
            }
        }.start()
    } ?: onComplete(false) // Null print job means immediate failure
}

// Example usage with coroutines
suspend fun printWebViewSuspend(context: Context, webView: WebView): Boolean {
    return kotlinx.coroutines.suspendCancellableCoroutine { continuation ->
        printWebViewWithCallback(context, webView) { success ->
            continuation.resume(success, null)
        }
    }
}

fun navigateToTab(
    navController: NavController,
    route: String,
    saveState: Boolean = true,
    popUpToRoute: String? = null,
    inclusive: Boolean = false
) {
    navController.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        popUpToRoute?.let { popUpTo(it) { this.inclusive = inclusive } }
            ?: popUpTo(navController.graph.findStartDestination().id) {
                this.saveState = saveState
            }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = saveState
    }
}