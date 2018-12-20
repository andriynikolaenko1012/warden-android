package com.nikolaenko.andrew.warden


import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikolaenko.andrew.warden.BaseFragment
import com.nikolaenko.andrew.warden.FragmentSendMoneyBluetooth
import com.nikolaenko.andrew.warden.R
import kotlinx.android.synthetic.main.fragment_send_money_second.*

class FragmentSendMoneySecondStep : BaseFragment() {

    private lateinit var mBluetoothAdapter: BluetoothAdapter

    override fun onStart() {
        super.onStart()

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        changeFontInTextView(textBluetooth)
        changeFontInTextView(textInternet)
        changeFontInTextView(toolbarTitle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_send_money_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textBluetooth.setOnClickListener {

            if (mBluetoothAdapter.isEnabled){
                replaceFragment(FragmentSendMoneyBluetooth(), true)
            } else {
                enableBlu()
            }

        }

        textInternet.setOnClickListener {
            replaceFragment(FragmentSendMoneyInternet(), true)
        }
    }

    fun enableBlu(){

        val discoveryIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
                DISCOVER_DURATION)
        startActivityForResult(discoveryIntent, REQUEST_BLU)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar!!.title = null

        leftButton.setOnClickListener {
            goBack()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == DISCOVER_DURATION && requestCode == REQUEST_BLU) {
            Log.e("sss", "blu_enabled")
            replaceFragment(FragmentSendMoneyBluetooth(), true)
        } else{
            Log.e("sss", "blu_cancelled")
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        if (mBluetoothAdapter.isEnabled) {
            mBluetoothAdapter.disable()
        }

    }

    companion object {
        val DISCOVER_DURATION = 300
        val REQUEST_BLU = 1
    }
}
