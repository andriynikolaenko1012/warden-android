package com.nikolaenko.andrew.warden


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Handler
import android.view.KeyEvent
import kotlinx.android.synthetic.main.fragment_redeem_check.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.app.Activity
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.ContextCompat.getSystemService
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.pixplicity.easyprefs.library.Prefs
import org.jetbrains.anko.support.v4.toast
import tw.henrychuang.lib.AutoAddTextWatcher
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


class FragmentRedeemCheck : BaseFragment() {

    private var isCheckValid: Boolean = false
    private var isCodeValid: Boolean = false

    override fun onStart() {
        super.onStart()

        changeFontInTextView(text1)
        changeFontInEditTextView(text2)
        changeFontInEditTextView(text4)
        changeFontInTextView(text5)

        changeFontInTextView(text3)
        changeFontInTextView(toolbarTitle)
        changeFontInTextView(text6)
        changeFontInTextView(textCancel)
        changeFontInTextView(textRedeem)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_redeem_check, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCancel.setOnClickListener {
            goBack()
        }

        textRedeem.setOnClickListener {

            if (text2.text.toString() == getString(R.string.text_check_sample)){

                if (text4.text.toString() == getString(R.string.text_code_sample)){

                    val price = Prefs.getString("balance_to_transfer_usd", "")
                    if (price.isNullOrEmpty()){
                        toast("This check is already used")
                        return@setOnClickListener
                    } else {
                        showProgress(true)

                        val mHandler = Handler()
                        mHandler.postDelayed({

                            showProgress(false)
                            replaceFragment(FragmentRedeemCheckSecond.newInstance(Prefs.getString("balance_to_transfer_usd", "")), true)
                        }, 1000L)
                    }

                } else {
                    toast("Activation code is invalid")
                    return@setOnClickListener
                }

            } else {
                toast("This check number is invalid")
                return@setOnClickListener
            }

        }

        text2.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)

                return@OnEditorActionListener true
            }
            false
        })

        text4.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)

                return@OnEditorActionListener true
            }
            false
        })


        text2.addTextChangedListener(AutoAddTextWatcher(text2,
                "-",
                2, 4, 6, 8))

        text4.addTextChangedListener(AutoAddTextWatcher(text4,
                "-",
                2, 4))

        text2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isCodeValid = p0?.contains(getString(R.string.text_check_sample))!!

                if (isCheckValid && isCodeValid) {

                    val price = Prefs.getString("balance_to_transfer_usd", "")
                    if (price.isNullOrEmpty()){
                        toast("This check is already used")
                    } else {
                        showProgress(true)
                        val mHandler = Handler()
                        mHandler.postDelayed({
                            showProgress(false)
                            text6.text = "$price USD"
                        }, 1000L)
                    }
                }
            }
        })

        text4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isCheckValid = p0?.contains(getString(R.string.text_code_sample))!!

                if (isCheckValid && isCodeValid) {

                    val price = Prefs.getString("balance_to_transfer_usd", "")
                    if (price.isNullOrEmpty()){
                        toast("This check is already used")
                    } else {
                        showProgress(true)
                        val mHandler = Handler()
                        mHandler.postDelayed({
                            showProgress(false)
                            text6.text = "$price USD"
                        }, 1000L)
                    }

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
