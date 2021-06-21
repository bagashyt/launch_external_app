package com.bagas.movetoapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagas.movetoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // change package name with package name destination app
    private var appPackageName = "com.google.android.youtube"

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.btnMove.setOnClickListener {
            launchApp(appPackageName)
        }


    }

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

    // function to check if application is installed or not
    private fun isAppInstalled(packageName: String): Boolean {
        return try {
            val packageManager = packageManager
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

}