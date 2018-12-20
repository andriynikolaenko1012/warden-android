package com.nikolaenko.andrew.warden


import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_card.*
import org.jetbrains.anko.support.v4.toast


class FragmentCard : BaseFragment() {

    override fun onStart() {
        super.onStart()


        changeFontInTextView(toolbarTitle)

        changeFontInTextView(text)
        changeFontInTextView(textYes)
        changeFontInTextView(textNo)

        changeFontInTextView(textWeb)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textWeb.setOnClickListener {
            replaceFragment(FragmentBalanceWeb(), true)
        }

        textYes.setOnClickListener {
            toast("HERE MUST BE URL ADDRESS")
        }

        textNo.setOnClickListener {
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
