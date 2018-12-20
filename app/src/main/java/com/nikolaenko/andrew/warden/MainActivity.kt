package com.nikolaenko.andrew.warden

import android.bluetooth.BluetoothAdapter
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import com.nikolaenko.andrew.warden.model.TransferModel
import com.pixplicity.easyprefs.library.Prefs
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    var progressView: FrameLayout? = null

    internal var doubleBackToExitPressedOnce = false

    override fun onStart() {
        super.onStart()

        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()

        if ( Prefs.getDouble("my_usd_balance_prefs", 0.00) < 100){
            Prefs.putDouble("my_usd_balance_prefs", 1020.25)
        }

        if ( Prefs.getDouble("my_eur_balance_prefs", 0.00) < 100){
            Prefs.putDouble("my_eur_balance_prefs", 800.05)
        }

        if (Prefs.getDouble("my_rub_balance_prefs", 0.00) < 100){
            Prefs.putDouble("my_rub_balance_prefs", 10200.00)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressView = findViewById(R.id.progressMain)

        if (savedInstanceState == null){
            val fragment = FragmentMain()
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.placeholder, fragment, "main_fragment")
            ft.commit()
        }
    }

    fun hardReplaceFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction()
                .replace(R.id.placeholder, fragment)
                .commit()
    }


    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount != 0) {
            super.onBackPressed()
            return
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finish()
            return
        }

        this.doubleBackToExitPressedOnce = true
        toast("Double press BACK to exit")
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }



    companion object {

        var isEnabled: Boolean = false

    }
}
