package com.nikolaenko.andrew.warden


import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_settings.*
import org.jetbrains.anko.support.v4.toast


class FragmentSettings : BaseFragment() {

    override fun onStart() {
        super.onStart()


        changeFontInTextView(toolbarTitle)

        changeFontInTextView(textWithdraw)
        changeFontInTextView(textI2p)
        changeFontInTextView(textColopr)
        changeFontInTextView(textDate)
        changeFontInTextView(textLanguage)
        changeFontInTextView(textHistory)
        changeFontInTextView(textNick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textWithdraw.setOnClickListener {
            toast("withdraw pressed")
        }

        textI2p.setOnClickListener {
            toast("I2p pressed")
        }

        textColopr.setOnClickListener {
            toast("Colors pressed")
        }

        textDate.setOnClickListener {
            toast("Date pressed")
        }

        textLanguage.setOnClickListener {
            toast("Language pressed")
        }

        textHistory.setOnClickListener {
            toast("History pressed")
        }

        textNick.setOnClickListener {
            toast("Nick pressed")
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
