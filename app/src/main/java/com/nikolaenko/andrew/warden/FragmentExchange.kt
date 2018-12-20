package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_exchange.*


class FragmentExchange : BaseFragment() {

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textOffer)
        changeFontInTextView(textAccept)
        changeFontInTextView(toolbarTitle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textOffer.setOnClickListener {
            replaceFragment(FragmentOffer(), true)
        }

        textAccept.setOnClickListener {
            replaceFragment(FragmentSearchForOffer(), true)
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
