package com.nikolaenko.andrew.warden


import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_record_detail.*


class FragmentRecordDeteils : BaseFragment() {

    private var position: Int? = null
    private var amount: String? = null
    private var date: String? = null
    private var time: String? = null
    private var coment: String? = null

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textDelete)
        changeFontInTextView(toolbarTitle)

        changeFontInTextView(text)
        changeFontInTextView(text1)
        changeFontInTextView(text2)
        changeFontInTextView(text3)
        changeFontInTextView(text4)
        changeFontInTextView(text5)

        position = arguments?.getInt(ARG_FOR_POSITION)
        amount = arguments?.getString(ARG_FOR_CURENCY)
        date = arguments?.getString(ARG_FOR_DATE)
        time = arguments?.getString(ARG_FOR_TIME)
        coment = arguments?.getString(ARG_FOR_COMENT)

        text1.text = amount
        text2.text = date
        text3.text = time

        text5.text = coment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_record_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textDelete.setOnClickListener {
            showProgress(true)
            val mHandler = Handler()
            mHandler.postDelayed({

                showProgress(false)

                FragmentMain.listUSDTransfers.removeAt(position!!)
                FragmentUSDHistory.adapter?.notifyItemRemoved(position!!)
                replaceFragment(FragmentDeletings(), true)

            }, 3000L)

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

    companion object {

        private val ARG_FOR_CURENCY = "arg_for_currency"
        private val ARG_FOR_DATE = "arg_for_date"
        private val ARG_FOR_TIME = "arg_for_time"
        private val ARG_FOR_COMENT = "arg_for_comment"
        private val ARG_FOR_POSITION = "arg_for_position"

        fun newInstance(amount: String, date: String, time: String, coment: String, position: Int): FragmentRecordDeteils {

            val args = Bundle()
            args.putString(ARG_FOR_CURENCY, amount)
            args.putString(ARG_FOR_DATE, date)
            args.putString(ARG_FOR_TIME, time)
            args.putString(ARG_FOR_COMENT, coment)
            args.putInt(ARG_FOR_POSITION, position)

            val fragment = FragmentRecordDeteils()
            fragment.arguments = args
            return fragment
        }
    }
}
