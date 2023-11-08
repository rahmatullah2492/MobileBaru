package com.example.kelompokbaru.ui.favorite



import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompokbaru.R
import com.example.kelompokbaru.databinding.FragmentFavoriteBinding
import com.example.kelompokbaru.ui.databse.AppDatabase
import com.example.kelompokbaru.ui.databse.UserDao


class FollowingFragment : Fragment() {

    private lateinit var followedUserAdapter: FollowedUserAdapter
    private lateinit var userDao: UserDao
    private lateinit var sharedPreferences: SharedPreferences


    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view: View = binding.root

        userDao = AppDatabase.getInstance(requireContext()).UserDao()
        followedUserAdapter = FollowedUserAdapter()
        sharedPreferences = requireContext().getSharedPreferences("user_data", Context.MODE_PRIVATE)



        val recyclerView: RecyclerView = view.findViewById(R.id.RecyclerView_Favorite)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = followedUserAdapter // Mengatur adapter untuk RecyclerView

        userDao.getUserByEmail(getEmailFromSharedPreferences())
            .observe(viewLifecycleOwner) { followedUsers ->
                if (followedUsers != null){
                    followedUserAdapter.setFollowedUsers(followedUsers)
            }
        }
        return view
    }

private fun getEmailFromSharedPreferences(): String{
    return sharedPreferences.getString("user_email", "")?: ""
    }
}
