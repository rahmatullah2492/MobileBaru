import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompokbaru.R
import com.example.kelompokbaru.ui.home.Activity_User_Detail
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class GitHubUserAdapter : RecyclerView.Adapter<GitHubUserAdapter.GitHubUserViewHolder>() {

    private var userList: List<GitHubUser> = ArrayList()
    private var filteredList: List<GitHubUser> = ArrayList() // filter search

    fun setUserList(users: List<GitHubUser>) {
        userList = users
        filteredList = users // filter search
        notifyDataSetChanged()
    }

    //filter search
    fun filter(query: String) {
        filteredList = userList.filter { user ->
            user.login.contains(query, ignoreCase = true)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_grid, parent, false)
        return GitHubUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitHubUserViewHolder, position: Int) {
        val user = filteredList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    inner class GitHubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: GitHubUser) {
            val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar_Detail)
            val imageView = itemView.findViewById<ImageView>(R.id.Photo_Grid)
            val profileImageView = itemView.findViewById<ImageView>(R.id.FotoProfil)
            val nameTextView = itemView.findViewById<TextView>(R.id.TeksName_Grid_Item)

            Picasso.get()
                .load(user.avatar_url) // mengambil gambar avatar url_untuk di tampilkan di FotoHome
                .into(profileImageView)

            // Sembunyikan gambar dan tampilkan progres bar
            imageView.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE

            nameTextView.text = user.login

            // Gunakan Picasso untuk mengunduh dan menampilkan gambar dengan listener
            Picasso.get()
                .load(user.avatar_url)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        // Gambar berhasil dimuat, sembunyikan progres bar
                        progressBar.visibility = View.INVISIBLE
                        imageView.visibility = View.VISIBLE
                    }

                    override fun onError(e: Exception?) {
                        // Tangani kesalahan jika gambar gagal dimuat
                        progressBar.visibility = View.INVISIBLE
                        imageView.visibility = View.VISIBLE
                    }
                })


            //pindah menggunakan nama pengguna
            nameTextView.setOnClickListener {
                val intent = Intent(itemView.context, Activity_User_Detail::class.java)
                intent.putExtra("login", user.login)
                intent.putExtra("avatar_url", user.avatar_url)
                itemView.context.startActivity(intent)
            }


// pindah menggunakan semua item saat di klik
//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, Activity_User_Detail::class.java)
//                intent.putExtra("login", user.login)
//                itemView.context.startActivity(intent)
//            }
        }
    }
}

// //menggunakan itemView untuk pindah ke activity detail
//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, Activity_User_Detail::class.java)
//                intent.putExtra("login", user.login)
//                itemView.context.startActivity(intent)