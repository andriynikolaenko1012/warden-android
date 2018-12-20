package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_offer.*


class FragmentOffer : BaseFragment() {

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textBluetooth)
        changeFontInTextView(textCreate)
        changeFontInTextView(toolbarTitle)
        changeFontInTextView(text)
        changeFontInTextView(text2)
        changeFontInTextView(text3)
        changeFontInTextView(text4)
        changeFontInTextView(text5)
        changeFontInTextView(text6)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textBluetooth.setOnClickListener {
            replaceFragment(FragmentExchangeInProgress(), true)
        }

        textCreate.setOnClickListener {

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
