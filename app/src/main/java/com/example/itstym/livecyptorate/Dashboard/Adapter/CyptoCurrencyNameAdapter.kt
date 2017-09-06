package com.example.itstym.livecyptorate.Dashboard.Adapter

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.itstym.livecyptorate.Dashboard.Model.CryptoCurrency
import com.example.itstym.livecyptorate.HelperClass.Constrants
import com.example.itstym.livecyptorate.R
import kotlinx.android.synthetic.main.currency_item.view.*

/**
 * Created by itstym on 3/9/17.
 */


class CyptoCurrencyNameAdapter(var mcontext: Context, var CryptoCurrencyDetails:ArrayList<CryptoCurrency>, var selectedPosition:Int): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        (holder as Item).itemView.currencyRate.isSelected = selectedPosition==position
        (holder as Item).itemView.currencyShortName.isSelected = selectedPosition==position

        if (selectedPosition==position){
            (holder as Item).itemView.currencyShortName.setTypeface((holder as Item).itemView.currencyShortName.typeface,Typeface.BOLD)
        }

        holder.bindData(CryptoCurrencyDetails[position])
    }

    override fun getItemCount(): Int = CryptoCurrencyDetails.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(mcontext).inflate(R.layout.currency_item, parent, false)
        return Item(v)

    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(currencyData:CryptoCurrency) {

            itemView.currencyShortName.text = currencyData.cryptoCurrencyShortName

           /* Log.i("Height",itemView.currencyRate.height.toString())
            Log.i("Width",itemView.currencyRate.width.toString())
*/
            itemView.currencyRate.layoutParams.width=Constrants.pxToDP(currencyData.cryptoValue)

        }

    }

}