package com.example.messagingapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.messagingapp.R
import com.example.messagingapp.model.conversation_model.MessageDataDto
import com.example.messagingapp.viewmodel.UsersViewModel

const val MY_MESSAGE_BUBBLE_TYPE = 1
const val CORRESPONDANT_MESSAGE_BUBBLE_TYPE = 2

class MessagesListAdapter(
    private val usersViewModel: UsersViewModel
): RecyclerView.Adapter<MessagesListAdapter.MessageCellViewHolder>() {

    private var messages: MutableList<MessageDataDto> = mutableListOf()

    init {
        updateMessagesToDisplay()
    }

    fun updateMessagesToDisplay() {
        val userId = this.usersViewModel.currentUserId
        this.messages = usersViewModel.completeUsersList.value?.first {
            it.infos.id == userId
        }?.conversations ?: mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageCellViewHolder {
        return if(viewType == MY_MESSAGE_BUBBLE_TYPE) {
            val conversationView = LayoutInflater.from(parent.context).inflate(R.layout.my_message_bubble_layout, parent, false)
            MessageCellViewHolder(conversationView)
        } else {
            val conversationView = LayoutInflater.from(parent.context).inflate(R.layout.correspondant_message_bubble_layout, parent, false)
            MessageCellViewHolder(conversationView)
        }
    }

    override fun getItemCount(): Int {
        return this.messages.count()
    }

    override fun getItemViewType(position: Int): Int {
        val messageData = this.messages[position]
        return if (messageData.isMyMessage) {
            MY_MESSAGE_BUBBLE_TYPE
        } else {
            CORRESPONDANT_MESSAGE_BUBBLE_TYPE
        }
    }

    override fun onBindViewHolder(holder: MessageCellViewHolder, position: Int) {
        val messageDatas = this.messages[position]

        holder.bubbleMessageText.text = messageDatas.message
    }

    inner class MessageCellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var bubbleMessageText: TextView

        init {
            bubbleMessageText = itemView.findViewById(R.id.bubble_message_text)
        }
    }
}