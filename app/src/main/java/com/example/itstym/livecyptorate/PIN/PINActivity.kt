package com.example.itstym.livecyptorate.PIN

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.EditText
import android.widget.Toast
import com.example.itstym.livecyptorate.PIN.Adapter.KeyBoardAdapter
import com.example.itstym.livecyptorate.R
import kotlinx.android.synthetic.main.activity_pin.*
import kotlinx.android.synthetic.main.keyboard_layout.*
import kotlinx.android.synthetic.main.toolbar.*


class PINActivity : AppCompatActivity() {

    var pin:String?=null
    var lastFilledEdittext:ArrayList<EditText>?=null

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)



        lastFilledEdittext= ArrayList()

        setSupportActionBar(toolbar)

        if (toolbar!=null){
            supportActionBar?.title = "ENTER PIN"
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }

        keywordRecyclerView.setHasFixedSize(true)
        keywordRecyclerView.layoutManager=GridLayoutManager(this@PINActivity,3,GridLayoutManager.VERTICAL,false)
        keywordRecyclerView.adapter=KeyBoardAdapter(this@PINActivity,getKeyBoardValues(),object :keyboardListener {
            override fun onKeyPressed(keyValue: Int) {


                when(keyValue){

                    10->{
                        //backspace pressed remove last pin if present
                        if (!pin.isNullOrEmpty()){
                            pin=pin?.substring(0,pin!!.length-1)
                            removeTheFilledCircle(lastFilledEdittext!!.size-1)
                        }
                    }

                    11 ->{
                        if (!isFilled()){
                            pin += "0"
                            fillTheCircle(lastFilledEdittext?.size)
                        }else{
                            //i.e. when all the pin filled
                            attemptLogin()
                        }

                    }

                    12 ->{
                        if(!isFilled()){
                            pin+="."
                            fillTheCircle(lastFilledEdittext?.size)
                        }else{
                            //i.e. when all the pin filled
                            attemptLogin()
                        }

                    }

                    else ->{
                        if (isFilled()){
                            pin+=keyValue.toString()
                            fillTheCircle(lastFilledEdittext?.size)
                        }else{
                            //i.e. when all the pin filled
                            attemptLogin()
                        }

                    }

                }

            }


        })
    }

    private fun attemptLogin() {
        Toast.makeText(this@PINActivity, "Attempt Login ",Toast.LENGTH_LONG).show()
    }

    private fun isFilled():Boolean {
        return pin?.length != 4
    }

    private fun  removeTheFilledCircle(size: Int?) {

        when(size){

            0->{
                pinOne.background=ContextCompat.getDrawable(this@PINActivity,R.drawable.circle)
                lastFilledEdittext?.remove(pinOne)
            }

            1->{
                pinTwo.background=ContextCompat.getDrawable(this@PINActivity,R.drawable.circle)
                lastFilledEdittext?.remove(pinTwo)
            }
            2->{
                pinThree.background=ContextCompat.getDrawable(this@PINActivity,R.drawable.circle)
                lastFilledEdittext?.remove(pinThree)
            }
            3->{
                pinFour.background=ContextCompat.getDrawable(this@PINActivity,R.drawable.circle)
                lastFilledEdittext?.remove(pinFour)
            }

        }

    }

    private fun  fillTheCircle(size: Int?) {

        when(size){

            0->{
                pinOne.background=ContextCompat.getDrawable(this@PINActivity,R.drawable.circle_filled)
                lastFilledEdittext?.add(pinOne)
            }

            1->{
                pinTwo.background=ContextCompat.getDrawable(this@PINActivity,R.drawable.circle_filled)
                lastFilledEdittext?.add(pinTwo)
            }
            2->{
                pinThree.background=ContextCompat.getDrawable(this@PINActivity,R.drawable.circle_filled)
                lastFilledEdittext?.add(pinThree)
            }
            3->{
                pinFour.background=ContextCompat.getDrawable(this@PINActivity,R.drawable.circle_filled)
                lastFilledEdittext?.add(pinFour)
            }

        }
    }

    private fun  getKeyBoardValues(): ArrayList<Int> {

        var keys:ArrayList<Int> = ArrayList()

        (1..12).mapTo(keys) { it }
        return keys
    }


}
