package com.castrec.stephane.noteskotlinsample.commons.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sca on 24/02/2018.
 */
class DateHelper {


    companion object {
        private val format = "HH:mm:ss"
        private var formatter: SimpleDateFormat? = null
        /**
         * create formatted date from timestamp.
         * @param timestamp
         * @return
         */
        @Throws(ParseException::class)
        fun getFormattedDate(timestamp: Long): String {
            if (formatter == null) {
                formatter = SimpleDateFormat(format)
            }
            return formatter!!.format(Date(timestamp)).toString()
        }
    }
}