# Payroll Android Wrapper

This folder contains a standalone Android app project that wraps your payroll web application in a secure WebView.

## Features

- First launch asks for server URL (HTTPS only)
- Stores URL locally on device
- Opens saved URL automatically on next launches
- Menu option to change server URL later
- Pull-to-refresh support

## Project Path

- payroll-android

## Build APK (Android Studio)

1. Open Android Studio.
2. Select Open and choose payroll-android folder.
3. Allow Gradle sync to complete.
4. Build APK:
   - Build > Build Bundle(s) / APK(s) > Build APK(s)
5. Generated debug APK location:
   - app/build/outputs/apk/debug/app-debug.apk

## Usage Flow

1. Install APK on device.
2. On first launch, enter your HTTPS payroll URL.
   - Example: https://your-domain.com
3. App loads login page using saved URL.
4. To change URL later, open app menu and select Change Server URL.

## Notes

- This app appends /login.php to the configured base URL.
- URL validation allows only HTTPS.
- If the saved URL is invalid, setup screen opens again.

## Build APK Using GitHub Actions (No Android Studio)

1. Push this repository to GitHub.
2. Open your repository in browser.
3. Go to Actions tab.
4. Select workflow: Build Payroll Android APK.
5. Click Run workflow.
6. After build completes, open the workflow run and download artifact:
   - payroll-android-debug-apk
7. APK file inside artifact:
   - app-debug.apk

## Optional: Signed Release APK in GitHub Actions

If you configure signing secrets, the same workflow also builds and uploads:

- payroll-android-release-apk

Add these repository secrets in GitHub:

1. ANDROID_KEYSTORE_BASE64
2. ANDROID_KEYSTORE_PASSWORD
3. ANDROID_KEY_ALIAS
4. ANDROID_KEY_PASSWORD

Create base64 for your .jks file (PowerShell):

```powershell
[Convert]::ToBase64String([IO.File]::ReadAllBytes("C:\path\to\your-release-key.jks")) | Set-Clipboard
```

Paste clipboard value into secret ANDROID_KEYSTORE_BASE64.
