GFocus - Minimalist Android Launcher for Deep Work
GFocus is a minimalist, privacy-first Android launcher designed to help you focus. When Focus Mode is enabled, you can only access the apps you've selected. All other apps and their notifications are blocked to eliminate distractions.

🚀 Features
📱 Focus Mode Launcher: Acts as your home screen while Focus Mode is active.

✅ App Whitelisting: Choose specific apps to allow during Focus Mode.

🔕 Notification Blocking: Prevent distractions by hiding notifications from non-whitelisted apps.

🔒 Secure Exit: Exit focus mode manually or set up a secure PIN (coming soon).

🔄 Auto Restore: Automatically switch back to your default launcher when focus ends.

🧭 Simple UI: Clean and intuitive interface for selecting focus apps and toggling modes.

🛡️ 100% Private: No ads, no data collection. Your device, your rules.

🧱 Architecture
Language: Kotlin

Minimum SDK: Android 12 (API 31)

Main Components:

MainActivity.kt: App entry point and UI logic.

FocusAppSelectorActivity.kt: Lets users select focus-mode apps.

FocusPreferenceManager.kt: Handles storing and retrieving focus app preferences.

FocusModeManager.kt: Starts and ends focus mode with launcher switching.

AppListAdapter.kt: Recyclable UI list of installed apps.

🛠️ Setup Instructions
Clone the repository:

bash
Copy
Edit
git clone https://github.com/Gorakhnath-Patil/GFocus.git
Open in Android Studio

Build and Run on a device running Android 12 or higher

🧪 Development Status
🔨 Currently under active development.
Next milestones:

 Add secure PIN to exit focus mode

 Block background app activity

 Schedule recurring focus sessions

🤝 Contribution
Contributions are welcome! Open issues or submit pull requests.
To discuss ideas, feel free to open a GitHub Discussion.

📃 License
This project is released under the MIT License.

📸 Screenshots
(Add screenshots or a screen recording here once UI is ready)

🙏 Acknowledgments
Built with ❤️ by Gorakhnath Patil
Inspired by minimalist productivity principles and deep work methodology.
