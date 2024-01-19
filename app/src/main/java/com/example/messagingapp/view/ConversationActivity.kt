package com.example.messagingapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.messagingapp.R
import com.example.messagingapp.di.parseConfigurationAndAddItToInjectionModules
import com.example.messagingapp.di.injectModuleDependencies
import com.example.messagingapp.model.CompleteUserDto
import com.example.messagingapp.model.user_model.UserData
import com.example.messagingapp.view.adapters.ConversationsListAdapter
import com.example.messagingapp.view.adapters.OnConversationClicked
import com.example.messagingapp.viewmodel.UsersViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ConversationActivity : AppCompatActivity(), OnConversationClicked {

    private val usersViewModel: UsersViewModel by viewModel()

    private lateinit var usersListRv: RecyclerView
    private lateinit var swipeToRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_disscussions)


        parseConfigurationAndAddItToInjectionModules()
        injectModuleDependencies(this@ConversationActivity)

        this.usersListRv = findViewById(R.id.conversations_tv)
        this.swipeToRefreshLayout = findViewById(R.id.swipe_to_refresh_layout)

        this.swipeToRefreshLayout.setOnRefreshListener {
            CoroutineScope(Dispatchers.Main).launch {
                this@ConversationActivity.usersViewModel.getUsersInfosAndConversation(5, 10)
            }
        }

        this.observeUserLiveData()
    }

    private fun observeUserLiveData() {
        /*usersViewModel.usersLiveData.observe(this@ConversationActivity) {
            Toast.makeText(this, "Got data about ${it.size} random users", Toast.LENGTH_LONG).show()
            setUpusersConversationsList(it)
        }*/

        usersViewModel.completeUsersList.observe(this@ConversationActivity) { usersCompleteData ->
            setUpUsersConversationsList(usersCompleteData)
            this.swipeToRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpUsersConversationsList(conversations: List<CompleteUserDto>) {
        val conversationAdapter = ConversationsListAdapter(conversations, this)
        usersListRv.layoutManager = LinearLayoutManager( this)
        usersListRv.adapter = conversationAdapter
    }

    override fun displayConversation(userData: UserData) {
        Intent(
            this,
            ConversationDetailActivity::class.java
        ).also {
            this.usersViewModel.currentUserId = userData.id
            startActivity(it)
        }
    }
}