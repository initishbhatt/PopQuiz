package com.initishbhatt.popquiz.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.initishbhatt.popquiz.R
import com.initishbhatt.popquiz.data.repository.UserDataEntity
import kotlinx.android.synthetic.main.item_high_score.view.*

/**
 * @author nitishbhatt
 */
class HighScoreItemAdapter(
        private val highScoreList: List<UserDataEntity>
) : RecyclerView.Adapter<HighScoreItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = (LayoutInflater.from(parent.context).inflate(R.layout.item_high_score, parent, false))
        return ViewHolder(root)
    }

    override fun getItemCount(): Int = highScoreList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(highScoreList[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(userDataEntity: UserDataEntity) = with(itemView) {
            name_label.text = userDataEntity.userName.toUpperCase()
            score_label.text = userDataEntity.userScore.toString()
        }
    }
}