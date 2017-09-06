package com.example.itstym.livecyptorate.HelperClass

import android.content.res.Resources

/**
 * Created by itstym on 5/9/17.
 */


class Constrants{


    companion object {

        public fun  getKeyBoardValues(): ArrayList<Int> {

            var keys:ArrayList<Int> = ArrayList()

            (1..12).mapTo(keys) { it }
            return keys
        }

        fun pxToDP(inPixel:Double):Int{

            return (inPixel/Resources.getSystem().displayMetrics.density).toInt()
        }
    }
}