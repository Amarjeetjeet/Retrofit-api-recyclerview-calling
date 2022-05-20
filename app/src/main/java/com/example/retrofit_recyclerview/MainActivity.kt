package com.example.retrofit_recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listRecyclerView.setLayoutManager(LinearLayoutManager(this))
        load()
    }

    private fun load() {
        val apiInterface = ApiInterface.create().getMovies()

        apiInterface.enqueue(object : Callback<List<ItemsViewModel>> {
            override fun onResponse(
                call: Call<List<ItemsViewModel>>,
                response: Response<List<ItemsViewModel>>
            ) {

                // ArrayList of class ItemsViewModel
                val userList  = ArrayList(response.body())


                Log.d("userList","the list is $userList" )

                // This will pass the ArrayList to our Adapter
                val adapter = Adapeter(userList,this@MainActivity)

                // Setting the Adapter with the recyclerview
                listRecyclerView.adapter = adapter

                adapter.setOnItemClickListener(object : Adapeter.onItemClicklistener{
                    override fun onItemClick(position: Int) {
                        Toast.makeText(this@MainActivity, "You have clicked $position", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            override fun onFailure(call: Call<List<ItemsViewModel>>, t: Throwable) {
                Log.d("failed",t.message.toString())
            }

        })    }

}