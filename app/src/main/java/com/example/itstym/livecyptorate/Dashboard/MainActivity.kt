package com.example.itstym.livecyptorate.Dashboard

import android.content.Intent
import android.graphics.Point
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.ViewGroup
import com.example.itstym.livecyptorate.Dashboard.Adapter.CyptoCurrencyNameAdapter
import com.example.itstym.livecyptorate.Dashboard.Model.CryptoCurrency
import com.example.itstym.livecyptorate.HelperClass.Constrants
import com.example.itstym.livecyptorate.PIN.PINActivity
import com.example.itstym.livecyptorate.R
import com.example.itstym.livecyptorate.Receive.ReceiveCryptoCurrencyActivity
import com.example.itstym.livecyptorate.Send.SendCryptoCurrencyActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*






class MainActivity : AppCompatActivity() {


    override fun onResume() {
        super.onResume()

        sendCryptoCurrency.setOnClickListener {
            //launch activity to send the currency
            startActivity(Intent(this@MainActivity,SendCryptoCurrencyActivity::class.java))

        }


        receiveCryptoCurrency.setOnClickListener {
            //launch activity to receive the currency
            startActivity(Intent(this@MainActivity,ReceiveCryptoCurrencyActivity::class.java))
        }

        buySellCryptoCUrrency.setOnClickListener {
            //launch the pin activity
            startActivity(Intent(this@MainActivity,PINActivity::class.java))
        }
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        if (toolbar!=null){
            //supportActionBar?.title ="ANkur";
           setActionBarTitle()
        }

        currencyName.setHasFixedSize(true)
        currencyName.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        currencyName.adapter= CyptoCurrencyNameAdapter(this,getCryptoData(),1)


        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y

        Log.e("Width ",width.toString())
        Log.e("Height ",height.toString())

        val imageWidth=(width/2)-(0.040*width)

        sendCryptoCurrency.layoutParams.width=Constrants.pxToDP(imageWidth)
        sendCryptoCurrency.layoutParams.height=Constrants.pxToDP(imageWidth)

        buySellCryptoCUrrency.layoutParams.width=Constrants.pxToDP(imageWidth)
        buySellCryptoCUrrency.layoutParams.height=Constrants.pxToDP(imageWidth)

        receiveCryptoCurrency.layoutParams.width=Constrants.pxToDP(imageWidth)
        receiveCryptoCurrency.layoutParams.height=Constrants.pxToDP(imageWidth)

    }

     fun setActionBarTitle() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            supportActionBar!!.setTitle(title)
        } else {
            val firstPart = SpannableString("Ledger")
            val lastPart = SpannableString("EX")

            firstPart.setSpan(ForegroundColorSpan(ContextCompat.getColor(applicationContext, android.R.color.black)), 0, firstPart.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            lastPart.setSpan(ForegroundColorSpan(ContextCompat.getColor(applicationContext, R.color.colorPrimary)), 0, lastPart.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            supportActionBar!!.title = TextUtils.concat(firstPart, lastPart);

        }


    }

    private fun  getCryptoData(): ArrayList<CryptoCurrency> {

        val CryptoCurrencies:ArrayList<CryptoCurrency> = ArrayList()

        val currencyName:ArrayList<String> = ArrayList()
        currencyName.add("Bitcoin")
        currencyName.add("Ethereum")
        currencyName.add("Stellar Lumens")
        currencyName.add("Litecoin")
        currencyName.add("NEM")
        currencyName.add("NEO")

        val currencyShortName:ArrayList<String> = ArrayList()
        currencyShortName.add("btc")
        currencyShortName.add("Eth")
        currencyShortName.add("stm")
        currencyShortName.add("ltc")
        currencyShortName.add("NEM")
        currencyShortName.add("NEM")


        val currencyValue:ArrayList<Double> = ArrayList()
        currencyValue.add(1400.8)
        currencyValue.add(800.3)
        currencyValue.add(1200.8)
        currencyValue.add(100.8)
        currencyValue.add(4000.8)
        currencyValue.add(1000.8)

        (0..currencyName.size-1).mapTo(CryptoCurrencies) { CryptoCurrency(currencyShortName[it], currencyValue[it],currencyName[it] ) }

        return CryptoCurrencies

    }
}
