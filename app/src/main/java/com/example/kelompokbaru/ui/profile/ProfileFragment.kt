package com.example.kelompokbaru.ui.profile

import GitHubUser
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kelompokbaru.R
import com.example.kelompokbaru.databinding.FragmentProfileBinding
import com.example.kelompokbaru.ui.home.GitHubService
import com.example.kelompokbaru.ui.login.MainActivity_Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private lateinit var sharedPreferences: SharedPreferences

    private val binding get() = _binding!!

    //new variabel
    private lateinit var imgProfile: ImageView
    private lateinit var txtName: TextView
    private lateinit var txtGithub: TextView
    private lateinit var txtFollowers: TextView
    private lateinit var txtFollowing: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val view: View = binding.root
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val email = user?.email
        imgProfile = view.findViewById(R.id.image_profile)
        txtName = view.findViewById(R.id.txt_fullname)
        txtGithub = view.findViewById(R.id.tv_uname_github)
        txtFollowers = view.findViewById(R.id.total_followers)
        txtFollowing = view.findViewById(R.id.total_following)
        setHasOptionsMenu(true)
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users")

        //new source code close
        sharedPreferences = requireActivity().getSharedPreferences("user_data", AppCompatActivity.MODE_PRIVATE)

        usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists() && snapshot.value != null) {
                    val retrievedUser = snapshot.children.first().getValue(User::class.java)
                    retrievedUser?.let { displayUserData(it) }
                } else {
                    Toast.makeText(requireContext(), "User data not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
        val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)

        if (isLoggedIn) {
            val username = sharedPreferences.getString("username", "")
            val email = sharedPreferences.getString("email", "")
            val firstChar = username?.take(1)
        }

        binding.btnLogout.setOnClickListener {
            val edit = sharedPreferences.edit()
            edit.putBoolean("is_logged_in", false)
            edit.apply()

            // Redirect to your login activity
            val intent = Intent(requireContext(), MainActivity_Login::class.java)
            startActivity(intent)

            // Finish the current activity
            requireActivity().finish()
        }

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bottom_nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    private fun displayUserData(user: User){
        val userGithub = user.github
        userGithub?.let {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(GitHubService::class.java)

            val call = service.getUserDetail(userGithub)
            call.enqueue(object : Callback<GitHubUser> {
                override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        Picasso.get()
                            .load(user?.avatar_url)
                            .into(imgProfile)
                        txtName.text = user?.name
                        txtGithub.text = user?.login
                        txtFollowers.text = user?.followers.toString()
                        txtFollowing.text = user?.following.toString()
                    } else {
                        Toast.makeText(requireContext(), "Failed to retrieve GitHub user data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
                    Toast.makeText(requireContext(), "API call failed: ${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }

}