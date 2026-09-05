import android.webkit.WebView
import android.webkit.WebViewClient

// WebView Client ke andar yeh logic lagayein
webView.webViewClient = object : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url != null) {
            val lowerUrl = url.lowercase()
            
            // Agar link WhatsApp, Intent ya kisi bhi restricted platform ka hai
            if (lowerUrl.contains("whatsapp") || lowerUrl.contains("wa.me") || lowerUrl.contains("intent://") || lowerUrl.contains("t.me") || lowerUrl.contains("tg:")) {
                
                // App ke andar hi Error page ya Unsupported message dikha dein
                val errorHtml = """
                    <html>
                    <head><meta name='viewport' content='width=device-width, initial-scale=1.0'></head>
                    <body style='background-color:#080c14; color:#ffffff; font-family:sans-serif; text-align:center; padding-top:60px;'>
                        <h2 style='color:#ef4444;'>❌ Unsupported Action</h2>
                        <p style='color:#94a3b8; font-size:14px; margin-top:10px;'>WhatsApp redirection is blocked in this application.</p>
                        <br>
                        <button onclick='history.back()' style='background:#38bdf8; color:#000; border:none; padding:10px 20px; border-radius:8px; font-weight:bold; cursor:pointer;'>Go Back</button>
                    </body>
                    </html>
                """
                view?.loadUrl("data:text/html;charset=utf-8," + android.util.Base64.encodeToString(errorHtml.toByteArray(), android.util.Base64.NO_WRAP))
                
                return true // Asli URL load nahi hoga, error show ho jayega
            }
        }
        return false
    }
}

