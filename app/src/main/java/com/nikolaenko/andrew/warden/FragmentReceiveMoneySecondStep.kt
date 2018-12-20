package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_receive_money_second.*
import org.jetbrains.anko.support.v4.toast


class FragmentReceiveMoneySecondStep : BaseFragment() {

    var myBalance: String = "0.00"
    var balanceToTransfer: String = "0.00"

    override fun onStart() {
        super.onStart()

        changeFontInTextView(textBluetooth)
        changeFontInTextView(textInternet)

        changeFontInTextView(toolbarTitle)
        changeFontInTextView(text1)
        changeFontInTextView(text2)
        changeFontInTextView(text3)
        changeFontInTextView(textBalance)

        changeFontInTextView(textHz)

        myBalance = Prefs.getDouble("my_eur_balance_prefs", 0.0).toString()
        textBalance.text = "$myBalance EUR"

        text2.requestFocus()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_receive_money_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textBluetooth.setOnClickListener {

            if (!text2.text.isNullOrBlank()){
                if (balanceToTransfer.toDouble() > myBalance.toDouble()){
                    toast("Enter less amount")
                    return@setOnClickListener
                } else {
                    replaceFragment(FragmentReceiveMoneyThird.newInstance(balanceToTransfer), true)
                }
            } else {
                toast("Enter amount")
                return@setOnClickListener
            }
        }

        textInternet.setOnClickListener {
            if (!text2.text.isNullOrBlank()){
                if (balanceToTransfer.toDouble() > myBalance.toDouble()){
                    toast("Enter less amount")
                    return@setOnClickListener
                } else {
                    replaceFragment(FragmentReceiveMoneyThird.newInstance(balanceToTransfer), true)
                }
            } else {
                toast("Enter amount")
                return@setOnClickListener
            }

        }

        text2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                if (!p0.isNullOrBlank()){
                    balanceToTransfer = p0.toString()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrBlank()){
                    textHz.visibility = View.VISIBLE
                } else {
                    textHz.visibility = View.GONE
                }

            }
        })
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
