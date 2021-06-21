# launch_external_app
Launch external app from current application

## Setup
make function intent to launch external App
```kotlin
// function launch intent to package name app
    private fun launchApp(appPackageName: String) {

        if (isAppInstalled(appPackageName)) {
            val intent: Intent = this.packageManager?.
            getLaunchIntentForPackage(appPackageName)!!
            startActivity(intent)
        } else {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            }
            // catch if application doesn't installed then open application page on playstore
            catch (exception: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }
    }
```
create function to check if application is installed or not
```kotlin
    private fun isAppInstalled(packageName: String): Boolean {
        return try {
            val packageManager = packageManager
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
```

## How to use
  change appPackageName with package name destination app
  ```kotlin
    private var appPackageName = "com.google.android.youtube"
  ```

## Reference
- [getlaunchintentforpackage](https://developer.android.com/reference/kotlin/android/content/pm/PackageManager#getlaunchintentforpackage)
- [ApplicationInfo](https://developer.android.com/reference/kotlin/android/content/pm/ApplicationInfo)
- [open an external app](https://www.icampanile.com/open-an-external-app-from-your-app-kotlin/)
- [check if Application is installed](https://handyopinion.com/check-if-application-is-installed-in-android-kotlin/)
