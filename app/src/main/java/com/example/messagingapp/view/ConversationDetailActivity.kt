package com.example.messagingapp.view

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.messagingapp.R
import com.example.messagingapp.model.conversation_model.MessageDataDto
import com.example.messagingapp.view.adapters.MessagesListAdapter
import com.example.messagingapp.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ConversationDetailActivity : AppCompatActivity() {

    private val usersViewModel: UsersViewModel by viewModel()
    private lateinit var messagesListRv: RecyclerView
    private lateinit var messageEditText: EditText
    private lateinit var userNameTextView: TextView
    private lateinit var backButtonImageButton: ImageButton
    private lateinit var userProfilePictureImageView: ImageView
    private lateinit var conversationAdapter: MessagesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disscussion_detail)

        // XML views linking to the Activity
        this.messageEditText = findViewById(R.id.new_message_et)
        this.userNameTextView = findViewById(R.id.user_complete_name_tv)
        this.backButtonImageButton = findViewById(R.id.back_button)
        this.userProfilePictureImageView = findViewById(R.id.user_picture_iv)

        // Views setup
        this.setUConversationsList()
        this.fillConversationViewWithUserData()

        // Views listeners
        this.setUpViewsListeners()

        this.messagesListRv.layoutManager?.smoothScrollToPosition(this.messagesListRv, null, 10)
    }

    private fun setUpViewsListeners() {
        this.setUpEditTextMessageListener()
        this.setUpBackButtonClickListener()
    }

    private fun setUpBackButtonClickListener() {
        this.backButtonImageButton.setOnClickListener {
            finish()
        }
    }

    private fun setUpEditTextMessageListener(){
        this.messageEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                v.text?.let {
                    this.sendMessage(it.toString())
                }
                true
            } else false
        }
    }

    private fun setUConversationsList() {
        messagesListRv = findViewById(R.id.messages_list)

        this.conversationAdapter = MessagesListAdapter(this.usersViewModel)
        messagesListRv.layoutManager = LinearLayoutManager(this)
        messagesListRv.adapter = conversationAdapter
    }

    private fun sendMessage(text: String) {
        val currentUser = this.usersViewModel.completeUsersList.value?.firstOrNull {
            this.usersViewModel.currentUserId == it.infos.id
        }
        currentUser?.conversations!!.add(MessageDataDto(true, text))
        this.conversationAdapter.updateMessagesToDisplay()
        this.conversationAdapter.notifyItemInserted(currentUser.conversations.count())
        this.messagesListRv.smoothScrollToPosition(currentUser.conversations.count() - 1)
        messageEditText.setText("")
    }

    private fun fillConversationViewWithUserData() {
        val currentUserData = this.usersViewModel.completeUsersList.value!!.first {
            it.infos.id == this.usersViewModel.currentUserId
        }
        this.userNameTextView.text = currentUserData.getFormattedFullUserName()
        Glide
            .with(this)
            .load("https://robohash.org/${currentUserData.infos.profilePicture}")
            .into(this.userProfilePictureImageView)
    }
}