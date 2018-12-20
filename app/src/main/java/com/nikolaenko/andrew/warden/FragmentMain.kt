package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikolaenko.andrew.warden.BaseFragment
import com.nikolaenko.andrew.warden.FragmentSendMoney
import com.nikolaenko.andrew.warden.R
import com.nikolaenko.andrew.warden.model.TransferModel
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : BaseFragment() {

    override fun onStart() {
        super.onStart()

        changeFontInTextView(sendMoney)
        changeFontInTextView(receiveMoney)
        changeFontInTextView(checkManag)
        changeFontInTextView(exchange)
        changeFontInTextView(remainsHistory)

        listUSDTransfers.clear()
        listUSDTransfers.add(0, TransferModel("new_amm", "+14.98", "10/10/2018", "11:17 P.M.", getString(R.string.text_commentary1), "USD"))
        listUSDTransfers.add(1, TransferModel("new_amm1", "-12.99", "10/10/2018", "11:07 P.M.", getString(R.string.text_commentary1), "USD"))
        listUSDTransfers.add(2, TransferModel("new_amm2", "+150.00", "10/10/2018", "10:57 P.M.", getString(R.string.text_commentary1), "USD"))
        listUSDTransfers.add(3, TransferModel("new_amm3", "+129.00", "10/10/2018", "10:33 P.M.", getString(R.string.text_commentary1), "USD"))

        listUSDTransfers.add(4, TransferModel("new_amm_", "+14.98", "10/10/2018", "11:17 P.M.", getString(R.string.text_commentary1), "USD"))
        listUSDTransfers.add(5, TransferModel("new_amm1_", "-12.99", "10/10/2018", "11:07 P.M.", getString(R.string.text_commentary1), "USD"))
        listUSDTransfers.add(6, TransferModel("new_amm2_", "+150.00", "10/10/2018", "10:57 P.M.", getString(R.string.text_commentary1), "USD"))
        listUSDTransfers.add(7, TransferModel("new_amm3_", "+129.00", "10/10/2018", "10:33 P.M.", getString(R.string.text_commentary1), "USD"))

        listUSDTransfers.add(8, TransferModel("new_amm__", "+14.98", "10/10/2018", "11:17 P.M.", getString(R.string.text_commentary1), "USD"))
        listUSDTransfers.add(9, TransferModel("new_amm1__", "-12.99", "10/10/2018", "11:07 P.M.", getString(R.string.text_commentary1), "USD"))
        listUSDTransfers.add(10, TransferModel("new_amm2__", "+150.00", "10/10/2018", "10:57 P.M.", getString(R.string.text_commentary1), "USD"))
        listUSDTransfers.add(11, TransferModel("new_amm3__", "+129.00", "10/10/2018", "10:33 P.M.", getString(R.string.text_commentary1), "USD"))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendMoney.setOnClickListener {
            replaceFragment(FragmentSendMoney(), true)

        }

        receiveMoney.setOnClickListener {
            replaceFragment(FragmentReceiveMoney(), true)
        }

        checkManag.setOnClickListener {
            replaceFragment(FragmentCheckManagement(), true)
        }

        exchange.setOnClickListener {
            replaceFragment(FragmentExchange(), true)
        }

        remainsHistory.setOnClickListener {
            replaceFragment(FragmentHistory.newInstance(false), true)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar!!.title = null

        rightButton.setOnClickListener {
            replaceFragment(FragmentSettings(), true)
        }

    }

    companion object {
        val listUSDTransfers = ArrayList<TransferModel>()
    }
}
