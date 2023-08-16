package com.example.whatsappcln.model

import android.os.Message
import java.sql.Timestamp

data class MessageModel(
    var message: String? = "",
    var senderId: String? = "",
    var timestamp: Long ? = 0
)
