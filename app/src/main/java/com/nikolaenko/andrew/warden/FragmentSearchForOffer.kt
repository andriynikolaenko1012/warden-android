package com.nikolaenko.andrew.warden


import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_search_for_offer.*


class FragmentSearchForOffer : BaseFragment() {

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textCancel)
        changeFontInTextView(toolbarTitle)

        changeFontInTextViewBold(text1)
        changeFontInTextViewBold(text2)
        changeFontInTextViewBold(text3)
        changeFontInTextViewBold(text4)
        changeFontInTextViewBold(text5)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_for_offer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCancel.setOnClickListener {
            goBack()
        }

        text1.paintFlags = text1.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        text2.paintFlags = text1.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        text3.paintFlags = text1.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        text4.paintFlags = text1.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        text5.paintFlags = text1.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        text1.setOnClickListener {
            replaceFragment(FragmentOfferAnExchange(), true)
        }

        text2.setOnClickListener {
            replaceFragment(FragmentOfferAnExchange(), true)
        }

        text3.setOnClickListener {
            replaceFragment(FragmentOfferAnExchange(), true)
        }

        text4.setOnClickListener {
            replaceFragment(FragmentOfferAnExchange(), true)
        }
        text5.setOnClickListener {
            replaceFragment(FragmentOfferAnExchange(), true)
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
