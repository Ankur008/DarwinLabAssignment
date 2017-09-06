package com.example.itstym.livecyptorate.PIN.Adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.itstym.livecyptorate.PIN.keyboardListener
import com.example.itstym.livecyptorate.R
import kotlinx.android.synthetic.main.keyboard_item.view.*

/**
 * Created by itstym on 4/9/17.
 */


class KeyBoardAdapter(var mcontext: Context, var keyBoardKeys:ArrayList<Int>, var onKeyPressedListener:keyboardListener ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        (holder as Item).bindData(mcontext,keyBoardKeys.get(position),onKeyPressedListener)

    }

    override fun getItemCount(): Int {
        return keyBoardKeys.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(mcontext).inflate(R.layout.keyboard_item, parent, false)
        return Item(v)
    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(context: Context, key: Int, listener: keyboardListener) {

            when(key){

                10->{
                    itemView.keyboardKey.background=ContextCompat.getDrawable(context,R.drawable.backspace)
                    itemView.keyboardKey.text=""
                }

                11 ->{
                    itemView.keyboardKey.text="0"
                }

                12->{
                    itemView.keyboardKey.text="."
                }


                else->{
                    itemView.keyboardKey.text = key.toString()
                }
            }

            itemView.setOnClickListener {
                listener.onKeyPressed(key)
            }
        }

    }

}