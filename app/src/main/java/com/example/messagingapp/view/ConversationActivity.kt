package com.example.messagingapp.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.messagingapp.R
class ConversationActivity : AppCompatActivity(), OnConversationClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disscussions)
    }
}