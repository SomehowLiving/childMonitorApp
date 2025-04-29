import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class ScreenMonitorService : AccessibilityService() {

    private val toxicWords = listOf("hate", "sad", "kill", "abuse", "depressed")

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED ||
            event?.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {

            val text = event.text.toString().lowercase()

            for (word in toxicWords) {
                if (text.contains(word)) {
                    Toast.makeText(this, "⚠️ Harmful Content Detected!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onInterrupt() {}
}
