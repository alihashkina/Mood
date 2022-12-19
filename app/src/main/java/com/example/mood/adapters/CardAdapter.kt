package com.example.mood.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.mood.MainActivity
import com.example.mood.MainActivity.Companion.anxiety
import com.example.mood.MainActivity.Companion.calm
import com.example.mood.MainActivity.Companion.feelingAwesome
import com.example.mood.MainActivity.Companion.feelingAwful
import com.example.mood.MainActivity.Companion.feelingBad
import com.example.mood.MainActivity.Companion.feelingGood
import com.example.mood.MainActivity.Companion.feelingNeutral
import com.example.mood.MainActivity.Companion.feelingNotWell
import com.example.mood.MainActivity.Companion.feelingWell
import com.example.mood.MainActivity.Companion.glad
import com.example.mood.MainActivity.Companion.happy2
import com.example.mood.MainActivity.Companion.harmony
import com.example.mood.MainActivity.Companion.health
import com.example.mood.MainActivity.Companion.lowSpirit
import com.example.mood.MainActivity.Companion.mood
import com.example.mood.MainActivity.Companion.neutral
import com.example.mood.MainActivity.Companion.neutral2
import com.example.mood.MainActivity.Companion.sad
import com.example.mood.MainActivity.Companion.stress2
import com.example.mood.MainActivity.Companion.strongAnxiety
import com.example.mood.MainActivity.Companion.unconcerned
import com.example.mood.MainActivity.Companion.veryHappy
import com.example.mood.MainActivity.Companion.verySad
import com.example.mood.MainActivity.Companion.worry
import com.example.mood.R
import com.example.mood.adapters.dataClass.Card
import com.example.mood.databinding.CardStatisticsBinding
import com.example.mood.fragment.Statistics
import com.example.mood.viewModel.GeneralPageViewModel
import java.util.*

class CardAdapter: RecyclerView.Adapter<CardAdapter.CardHolder>() {

    companion object{
        var deleteCard = false
        var id = 0
        var idDB = 0
    }

    val cardList = ArrayList<Card>()

    class CardHolder(item: View): RecyclerView.ViewHolder(item){

        val binding = CardStatisticsBinding.bind(item)

        @SuppressLint("NotifyDataSetChanged")
        fun bind(card: Card, index: Int) = with(binding){
            cardDate.text = card.cardDate
            cardHealthy.text = card.cardHealthy?.replace("[", "")?.replace("]", "")
            cardUnhealthy.text = card.cardUnhealthy?.replace("[", "")?.replace("]", "")
            cardSymptoms.text = card.cardSymptoms?.replace("[", "")?.replace("]", "")
            cardCare.text = card.cardCare?.replace("[", "")?.replace("]", "")
            cardOther.text = card.cardOther?.replace("[", "")?.replace("]", "")
            id = card.id

            when(card.cardMood){
                -5.0F -> cardMood.text = "$mood: $verySad"
                -4.0F -> cardMood.text = "$mood: $verySad"
                -3.0F -> cardMood.text = "$mood: $sad"
                -2.0F -> cardMood.text = "$mood: $sad"
                -1.0F -> cardMood.text = "$mood: $lowSpirit"
                0.0F -> cardMood.text = "$mood: $neutral"
                1.0F -> cardMood.text = "$mood: $glad"
                2.0F -> cardMood.text = "$mood: $happy2"
                3.0F -> cardMood.text = "$mood: $happy2"
                4.0F -> cardMood.text = "$mood: $veryHappy"
                5.0F -> cardMood.text = "$mood: $veryHappy"
            }

            when(card.cardHealth){
                -5.0F -> cardHealth.text = "$health: $feelingAwful"
                -4.0F -> cardHealth.text = "$health: $feelingAwful"
                -3.0F -> cardHealth.text = "$health: $feelingBad"
                -2.0F -> cardHealth.text = "$health: $feelingBad"
                -1.0F -> cardHealth.text = "$health: $feelingNotWell"
                0.0F -> cardHealth.text = "$health: $feelingNeutral"
                1.0F -> cardHealth.text = "$health: $feelingWell"
                2.0F -> cardHealth.text = "$health: $feelingGood"
                3.0F -> cardHealth.text = "$health: $feelingGood"
                4.0F -> cardHealth.text = "$health: $feelingAwesome"
                5.0F -> cardHealth.text = "$health: $feelingAwesome"
            }

            when(card.cardStress){
                -5.0F -> cardStress.text = "$stress2: $strongAnxiety"
                -4.0F -> cardStress.text = "$stress2: $strongAnxiety"
                -3.0F -> cardStress.text = "$stress2: $anxiety"
                -2.0F -> cardStress.text = "$stress2: $anxiety"
                -1.0F -> cardStress.text = "$stress2: $worry"
                0.0F -> cardStress.text = "$stress2: $neutral2"
                1.0F -> cardStress.text = "$stress2: $unconcerned"
                2.0F -> cardStress.text = "$stress2: $calm"
                3.0F -> cardStress.text = "$stress2: $calm"
                4.0F -> cardStress.text = "$stress2: $harmony"
                5.0F -> cardStress.text = "$stress2: $harmony"
            }

            //кнопка поделиться/удалить
            cardMore.setOnClickListener {
                val popupMenu = PopupMenu(it.context, cardMore)
                var date = it.context.getString(R.string.date)
                var healthy = it.context.getString(R.string.healthy)
                var txtUnhealthy = it.context.getString(R.string.txtUnhealthy)
                var txtSymptoms = it.context.getString(R.string.txtSymptoms)
                var txtCare = it.context.getString(R.string.txtCare)
                var txtOtherTags = it.context.getString(R.string.txtOtherTags)

                popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->

                    when(item.itemId) {
                        R.id.share ->
                        {
                            val intent = Intent()
                            intent.action = Intent.ACTION_SEND
                            intent.putExtra(Intent.EXTRA_TEXT, "${cardMood.text} \n${cardHealth.text} \n${cardStress.text} \n$date: ${cardDate.text} \n$healthy: ${cardHealthy.text} \n$txtUnhealthy: ${cardUnhealthy.text} \n$txtSymptoms: ${cardSymptoms.text} \n$txtCare: ${cardCare.text} \n$txtOtherTags: ${cardOther.text}")
                            intent.type = "text/plain"
                            it.context.startActivity(Intent.createChooser(intent, "Share To:"))
                        }
                        R.id.delete ->
                        {
                            idDB = card.id
                            Statistics.adapter.deleteCard(it.context)
                            Statistics.adapter.updateDelete(index)
                        }
                    }
                    true
                })
                popupMenu.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_statistics, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(cardList[position], position)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    //добавить карточку
    fun addCard(card: Card){
        deleteCard = false
        cardList.add(card)
        notifyDataSetChanged()
    }

    //удалить карочку
    fun deleteCard(context: Context){
        deleteCard = true
        if(cardList.size == 1){
            GeneralPageViewModel().deleteAllDB(context)
        }
        else{
            GeneralPageViewModel().deleteCardDB(context)
        }
    }

    //очистка адаптера
    fun update(){
        cardList.clear()
        notifyDataSetChanged()
    }

    //обновление адаптера после удаления
    fun updateDelete(index: Int){
        cardList.removeAt(index)
        notifyDataSetChanged()
    }
}