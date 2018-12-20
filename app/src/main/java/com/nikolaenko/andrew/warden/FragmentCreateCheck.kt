package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.fragment_create_check.*
import org.jetbrains.anko.support.v4.toast


class FragmentCreateCheck : BaseFragment() {

    var myBalance: String = "0.00"
    var balanceToTransfer: String = "0.00"

    override fun onStart() {
        super.onStart()

        changeFontInTextView(text1)
        changeFontInEditTextView(text2)

        changeFontInTextView(text3)
        changeFontInTextView(toolbarTitle)
        changeFontInTextView(textOk)
        changeFontInTextView(textCancel)

        myBalance = Prefs.getDouble("my_usd_balance_prefs", 0.0).toString()

//        myBalance = "524.26"

        textBalance.text = "$myBalance USD"

        text2.requestFocus()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_check, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCancel.setOnClickListener {
            mainActivity.hardReplaceFragment(FragmentMain())
        }

        textOk.setOnClickListener {

            if (!text2.text.isNullOrBlank()){
                if (balanceToTransfer.toDouble() > myBalance.toDouble()){
                    toast("Enter less amount")
                    return@setOnClickListener
                } else {
                    replaceFragment(FragmentCreateCheckSecond.newInstance(balanceToTransfer), true)
                }
            } else {
                toast("Enter amount")
                return@setOnClickListener
            }

        }

        text2.addTextChangedListener(object : TextWatcher{
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
