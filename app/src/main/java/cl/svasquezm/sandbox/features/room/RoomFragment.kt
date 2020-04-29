package cl.svasquezm.sandbox.features.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import cl.svasquezm.sandbox.R
import cl.svasquezm.sandbox.features.room.base.PostModel
import cl.svasquezm.sandbox.features.room.base.db.PostDatabase
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class RoomFragment : Fragment() {

    private val titles = arrayOf("This is about music", "Post about cats", "How to do bread at home", "All about medicines")
    private val button by lazy { requireView().findViewById<Button>(R.id.button) }
    private val recyclerView by lazy { requireView().findViewById<RecyclerView>(R.id.recyclerView) }
    private val database by lazy {
        Room.databaseBuilder(requireContext(), PostDatabase::class.java, "Posts")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = LayoutInflater.from(requireContext()).inflate(R.layout.layout_item_room, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PostAdapter(database.dao().getPosts().toMutableList())

        recyclerView.adapter = adapter
        button.setOnClickListener {
            val post = generateRandomPost()
            database.dao().insertPost(post)
            adapter.posts.add(post)
            adapter.notifyItemInserted(adapter.itemCount)
        }
    }

    private fun generateRandomPost() = PostModel(
        id = UUID.randomUUID().toString(),
        title = titles[Random().nextInt(3)],
        content = "The dummy content here"
    )

    inner class PostAdapter(val posts: MutableList<PostModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = object: RecyclerView.ViewHolder(TextView(parent.context)){}
        override fun getItemCount() = posts.size
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder.itemView as TextView).text = "${posts[position].title}: ${posts[position].content}"
        }
    }
}
