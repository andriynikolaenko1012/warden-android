package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Handler
import kotlinx.android.synthetic.main.fragment_send_money_internet.*
import org.jetbrains.anko.support.v4.toast


class FragmentSendMoneyInternet : BaseFragment() {

    override fun onStart() {
        super.onStart()

        changeFontInTextView(text1)
        changeFontInTextView(text2)

        changeFontInTextView(toolbarTitle)
        changeFontInTextView(textOk)
        changeFontInTextView(textCancel)

        text2.requestFocus()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_send_money_internet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCancel.setOnClickListener {
            mainActivity.hardReplaceFragment(FragmentMain())
        }

        textOk.setOnClickListener {

            if (text2.text.isNullOrBlank()){
                toast("Enter passphrase")
                return@setOnClickListener
            } else {
                replaceFragment(FragmentSendMoneyBluetoothSecond(),true)
            }

        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar!!.title = null

        leftButton.setOnClickListener {
            goBack()
        }
    }
}
