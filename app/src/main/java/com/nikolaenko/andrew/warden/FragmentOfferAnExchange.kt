package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_offer_an_exchange.*


class FragmentOfferAnExchange : BaseFragment() {

    override fun onStart() {
        super.onStart()

        changeFontInTextView(text2)
        changeFontInTextView(toolbarTitle)

        changeFontInTextView(text3)
        changeFontInTextView(text)
        changeFontInTextView(text1)
        changeFontInTextView(text4)
        changeFontInTextView(text5)
        changeFontInTextView(textOk)
        changeFontInTextView(textCancel)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offer_an_exchange, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textOk.setOnClickListener {
            showProgress(true)
            val mHandler = Handler()
            mHandler.postDelayed({

                showProgress(false)
                replaceFragment(FragmentExchangeComplete(), true)
            }, 3000L)

        }

        textCancel.setOnClickListener {
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
