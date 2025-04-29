import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.widget.Toast

class NotificationMonitorService : NotificationListenerService() {

    private val toxicWords = listOf("hate", "sad", "kill", "abuse", "depressed")

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val extras = sbn.notification.extras
        val title = extras.getString("android.title") ?: ""
        val text = extras.getString("android.text") ?: ""

        val content = "$title $text".lowercase()

        for (word in toxicWords) {
            if (content.contains(word)) {
                Toast.makeText(this, "⚠️ Harmful Notification Detected!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
