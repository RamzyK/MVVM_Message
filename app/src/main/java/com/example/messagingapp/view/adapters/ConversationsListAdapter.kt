package com.example.messagingapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.messagingapp.R
import com.example.messagingapp.model.CompleteUserDto
import com.example.messagingapp.model.conversation_model.MessageData
import com.example.messagingapp.model.user_model.UserData

class ConversationsListAdapter(
    private val users: List<CompleteUserDto>,
    private val onClickHandler: OnConversationClicked
): RecyclerView.Adapter<ConversationsListAdapter.ConversationCellViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationCellViewHolder {
        val conversationView = LayoutInflater.from(parent.context).inflate(R.layout.conversation_cell_layout, parent, false)
        return ConversationCellViewHolder(conversationView)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ConversationCellViewHolder, position: Int) {
        val user = this.users[position]
        val holderContext = holder.itemView.context
        holder.userNameTv.text =  holderContext.getString(R.string.user_name_formatted_dispay, user.infos.lastName , user.infos.name)
        holder.messagesCountTv.text = user.getFormattedConversationCount()
        holder.lastMessageTv.text = this.formatLastMessage(user.conversations.last())

        holder.itemView.setOnClickListener {
            onClickHandler.displayConversation(user.infos)
        }
        Glide
            .with(holder.itemView)
            .load("https://robohash.org/${user.infos.profilePicture}")
            .into(holder.userProfilePictureIv)
    }

    private fun formatLastMessage(data: MessageData): String {
        return if(data.isMyMessage) "Moi: ${data.message}" else data.message
    }

    inner class ConversationCellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var userProfilePictureIv: ImageView
        var userNameTv: TextView
        var messagesCountTv: TextView
        var lastMessageTv: TextView


        init {
            userProfilePictureIv = itemView.findViewById(R.id.user_picture_iv)
            userNameTv = itemView.findViewById(R.id.user_name_tv)
            messagesCountTv = itemView.findViewById(R.id.messages_count_tv)
            lastMessageTv = itemView.findViewById(R.id.last_message_tv)
        }
    }
}

interface OnConversationClicked {
    fun displayConversation(userData: UserData)
}