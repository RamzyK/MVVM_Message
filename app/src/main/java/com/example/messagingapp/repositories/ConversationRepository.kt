package com.example.messagingapp.repositories

import com.example.messagingapp.model.conversation_model.MessageDataDto
import com.example.messagingapp.network.services.MessagesApiService
import io.reactivex.rxjava3.core.Flowable

class ConversationRepository(
    private val conversationService: MessagesApiService
) {

    fun getRandomConversation(size: Int, messagesPerConversation: Int): Flowable<MutableList<MutableList<MessageDataDto>>> {
        return conversationService.getRandomConversation(
            "fr_FR",
            size * messagesPerConversation,
            "boolean",
            "text"
        ).map {
            val rawListMessages = it.data
            var position = 0
            val formattedList: MutableList<MutableList<MessageDataDto>> = mutableListOf()
            val tempList: MutableList<MessageDataDto> = mutableListOf()

            while (position <= rawListMessages.count()) {
                if(position % messagesPerConversation == 0 && position > 0) {
                    val finishedList = mutableListOf<MessageDataDto>()
                    finishedList.addAll(tempList)
                    formattedList.add(finishedList)
                    tempList.clear()
                }

                if(position < rawListMessages.count()) {
                    tempList.add(rawListMessages[position])
                }
                position += 1
            }
            formattedList
        }
    }
}