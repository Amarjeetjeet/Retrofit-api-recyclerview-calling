package com.example.retrofit_recyclerview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL

class Adapeter(private val mList: List<ItemsViewModel> , private val context: Context):RecyclerView.Adapter<Adapeter.ViewHolder>() {

    private lateinit var mListener : onItemClicklistener

    interface onItemClicklistener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener:onItemClicklistener){
        mListener = listener
    }

    class ViewHolder(itemView: View,listener:onItemClicklistener) :RecyclerView.ViewHolder(itemView) {

        val userTextname : TextView = itemView.findViewById(R.id.userTextname)
        val radomNumber : TextView = itemView.findViewById(R.id.radomNumber)
        val thumbnailUrlText : TextView = itemView.findViewById(R.id.thumbnailUrlText)

        val thumbnailUrl : ImageView = itemView.findViewById(R.id.thumbnailUrl)

        init{
            itemView.setOnClickListener{

                 listener.onItemClick(adapterPosition)


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapeter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item,parent,false)
        return ViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: Adapeter.ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.userTextname.text = ItemsViewModel.title
        holder.radomNumber.text = ItemsViewModel.id
        var url = ItemsViewModel.thumbnailUrl
        holder.thumbnailUrlText.text = url

        Glide.with(context).load("https://randomuser.me/api/portraits/med/men/75.jpg").placeholder(R.drawable.koko).into(holder.thumbnailUrl)


    }

    override fun getItemCount(): Int {
        return mList.size
    }
}