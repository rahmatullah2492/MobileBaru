package com.example.kelompokbaru.ui.home

import GitHubUser
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompokbaru.R
import com.example.kelompokbaru.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback

class HomeFragment : Fragment() {

    private lateinit var searchView: androidx.appcompat.widget.SearchView  // BARU

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GitHubUserAdapter
    //private val users = mutableListOf<GitHubUser>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view: View = binding.root

        adapter = GitHubUserAdapter()
        recyclerView = view.findViewById(R.id.RecyclerView_User)

        searchView = view.findViewById(R.id.search) // Inisialisasi SearchView BARU

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        // Tambahkan listener untuk SearchView INI BARU
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val query = newText.orEmpty()
                adapter.filter(query) // Panggil filter pada adapter dengan kata kunci pencarian
                return true
            }
        })

        getUsersFromApi()

        return view

    }

    private fun getUsersFromApi(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)
        val token ="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"
        val bearerToken = "Bearer $token"

        val call = service.getUser(bearerToken)
        call.enqueue(object : Callback<List<GitHubUser>> {
            override fun onResponse(
                call: Call<List<GitHubUser>>,
                response: Response<List<GitHubUser>>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()
                    users?.let { adapter.setUserList(it) }
//
                } else {
                }
            }

            override fun onFailure(call: Call<List<GitHubUser>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}