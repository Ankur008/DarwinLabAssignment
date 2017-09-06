package com.example.itstym.livecyptorate.Send

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.example.itstym.livecyptorate.HelperClass.Constrants
import com.example.itstym.livecyptorate.PIN.Adapter.KeyBoardAdapter
import com.example.itstym.livecyptorate.PIN.keyboardListener
import com.example.itstym.livecyptorate.R
import kotlinx.android.synthetic.main.activity_send_crypto_currency.*
import kotlinx.android.synthetic.main.keyboard_layout.*
import kotlinx.android.synthetic.main.toolbar.*

class SendCryptoCurrencyActivity : AppCompatActivity() {

    var value:String="0.0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_crypto_currency)

        setSupportActionBar(toolbar)

        if (toolbar!=null){
            supportActionBar?.title = "SEND"
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }

        keywordRecyclerView.setHasFixedSize(true)
        keywordRecyclerView.layoutManager= GridLayoutManager(this@SendCryptoCurrencyActivity,3, GridLayoutManager.VERTICAL,false)
        keywordRecyclerView.adapter= KeyBoardAdapter(this@SendCryptoCurrencyActivity, Constrants.getKeyBoardValues(),object : keyboardListener {
            override fun onKeyPressed(keyValue: Int) {


                when(keyValue){

                    10->{
                        //backspace pressed remove last pin if present
                        if(!ethAmount.text.toString().isNullOrEmpty()) {
                            ethAmount.text=ethAmount.text.toString().substring(0,ethAmount.text.toString().length-1)
                        }
                    }

                    11 ->{
                       //0 is hit
                        addValue("0")
                    }

                    12 ->{
                       // . hit
                        addValue(".")
                    }

                    else ->{
                       //any no pressed
                        addValue(keyValue.toString())
                    }

                }

            }


        })
    }

    private fun  addValue(pressedKey: String) {
        var temp:StringBuilder?=null
        temp?.append(ethAmount.text.toString())
        temp?.append(pressedKey)
        ethAmount.text=temp.toString()
    }

}
