package com.example.messagingapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.messagingapp.model.CompleteUserDto
import com.example.messagingapp.model.conversation_model.MessageData
import com.example.messagingapp.model.user_model.UserData
import com.example.messagingapp.repositories.ConversationRepository
import com.example.messagingapp.repositories.UsersRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlin.random.Random

class UsersViewModel(
    private val usersRepo: UsersRepository,
    private val messageRepo: ConversationRepository
): ViewModel() {

    private val disposeBag = CompositeDisposable()


    // Observables used by the view model to get the users infos only
    private val usersData: BehaviorSubject<List<UserData>> = BehaviorSubject.createDefault(listOf())

    // Observable used by the viewmodel to get conversations only
    private val usersConversations: BehaviorSubject<MutableList<MutableList<MessageData>>> = BehaviorSubject.createDefault(mutableListOf())

    // Observable exposed to the view to get the final data
    val completeUsersList: MutableLiveData<List<CompleteUserDto>> = MutableLiveData()

    var currentUserId: String = "-1"

    init {
        //
        this.getUsersInfosAndConversation(1, 2)
    }

    private fun getFixedSizeOfRandomUsers(count: Int) {
        this.usersRepo.getRandomListOfUsers(count).subscribe({ users ->
            //usersLiveData.postValue(users)
            this.usersData.onNext(users)
        }, { error ->
            Log.d("Error in function getFixedSizeOfRandomUsers while fetching data from Fake API",
                error.message ?: "Default message error"
            )
        }).addTo(disposeBag)
    }

    private fun getConversations(count: Int, messagesPerConversation: Int, callBack: () -> Unit) {
        this.messageRepo.getRandomConversation(count, messagesPerConversation).subscribe({ messages ->
            this.usersConversations.onNext(messages)
            callBack()
        }, { error ->
            Log.d("Error in function getConversations while fetching data from Fake API",
                error.message ?: "Default message error"
            )
        }).addTo(disposeBag)
    }

    fun getUsersInfosAndConversation(usersToFetch: Int, messagesByConversation: Int) {
        this.getConversations(usersToFetch, messagesByConversation) {
            Log.d("Conversations loaded", "Loaded $usersToFetch random conversations")
        }
        this.getFixedSizeOfRandomUsers(usersToFetch)
        Observables
            .combineLatest(this.usersData, this.usersConversations)
            .observeOn(Schedulers.io())
            .subscribeBy {(users, conversations) ->
                val completeUsersMappedData = users.zip(conversations) { userInfos, conversation ->
                    CompleteUserDto(userInfos, conversation)
                }
                this.completeUsersList.postValue(completeUsersMappedData)
            }.let {
                this.disposeBag.add(it)
            }
    }
}