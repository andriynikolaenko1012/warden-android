package com.nikolaenko.andrew.warden


import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikolaenko.andrew.warden.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_usd_history.*


class FragmentUSDHistory : BaseFragment() {

    private var toolTitle: String? = null
//    private lateinit var adapter: HistoryAdapter

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textErase)
        changeFontInTextView(toolbarTitle)

        changeFontInTextView(text)

        toolTitle = arguments?.getString(ARG_FOR_HISTIRY)
        toolbarTitle.text = toolTitle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_usd_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textErase.setOnClickListener {
            showProgress(true)
            val mHandler = Handler()
            mHandler.postDelayed({

                showProgress(false)
                FragmentMain.listUSDTransfers.clear()
                adapter?.setList(FragmentMain.listUSDTransfers)
                replaceFragment(FragmentErasingWeb(), true)
            }, 3000L)
        }

        rvHistory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvHistory.itemAnimator = DefaultItemAnimator()

        adapter = HistoryAdapter { title, amount, comment, currency, date, time, position ->
            replaceFragment(FragmentRecordDeteils.newInstance(amount,date, time, comment, position ), true)
        }
        rvHistory.adapter = adapter
        adapter?.setList(FragmentMain.listUSDTransfers)

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

        private val ARG_FOR_HISTIRY = "arg_for_history"

        var adapter: HistoryAdapter? = null

        fun newInstance(title: String): FragmentUSDHistory {

            val args = Bundle()
            args.putString(ARG_FOR_HISTIRY, title)

            val fragment = FragmentUSDHistory()
            fragment.arguments = args
            return fragment
        }
    }
}
