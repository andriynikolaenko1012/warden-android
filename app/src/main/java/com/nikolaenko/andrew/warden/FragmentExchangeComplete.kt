package com.nikolaenko.andrew.warden


import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_exchange_complete.*


class FragmentExchangeComplete : BaseFragment() {

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textOk)
        changeFontInTextView(toolbarTitle)

        changeFontInTextView(text)
        changeFontInTextView(text1)
        changeFontInTextView(text2)
        changeFontInTextView(text3)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange_complete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textOk.setOnClickListener {
            mainActivity.hardReplaceFragment(FragmentMain())
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
