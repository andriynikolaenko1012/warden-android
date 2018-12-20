package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_exchange_in_progress.*


class FragmentExchangeInProgress : BaseFragment() {

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textCancel)
        changeFontInTextView(toolbarTitle)

        changeFontInTextView(text)
        changeFontInTextViewBold(text1)
        changeFontInTextView(text2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange_in_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCancel.setOnClickListener {
            mainActivity.hardReplaceFragment(FragmentMain())
        }


        val mHandler = Handler()
        mHandler.postDelayed({

            showProgress(false)
            replaceFragment(FragmentExchangeComplete(), true)
        }, 10000L)

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
