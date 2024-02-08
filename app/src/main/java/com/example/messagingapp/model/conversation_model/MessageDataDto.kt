package com.example.messagingapp.model.conversation_model

import android.os.Parcel
import android.os.Parcelable

data class MessageDataDto(val isMyMessage: Boolean,
                          val message: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {

    }

    companion object CREATOR : Parcelable.Creator<MessageDataDto> {
        override fun createFromParcel(parcel: Parcel): MessageDataDto {
            return MessageDataDto(parcel)
        }

        override fun newArray(size: Int): Array<MessageDataDto?> {
            return arrayOfNulls(size)
        }
    }
}