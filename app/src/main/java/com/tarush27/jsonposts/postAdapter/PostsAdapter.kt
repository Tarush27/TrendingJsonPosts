package com.tarush27.jsonposts.postAdapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.tarush27.jsonposts.R
import com.tarush27.jsonposts.postModel.PostModel
import com.tarush27.jsonposts.ui.DetailFragment


class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val posts: ArrayList<PostModel> = arrayListOf()

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val postConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.postCl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.single_post, parent, false)
        return PostViewHolder(postHolder)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        bindPosts(position, holder)
        holder.postConstraintLayout.setOnClickListener {
            sendPostDetail(it)
        }
    }

    private fun bindPosts(
        position: Int,
        holder: PostViewHolder
    ) {
        val post = posts[position]
        holder.title.text = post.postTitle
        holder.description.text = post.postDescription
    }

    private fun sendPostDetail(it: View) {
        val description: TextView = it.findViewById(R.id.description)
        val getDescription = description.text.toString()
        val bundle = Bundle()
        bundle.putString("description", getDescription)
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle
        val manager: FragmentManager = (it.context as AppCompatActivity).supportFragmentManager
        manager.beginTransaction().replace(R.id.postFragment, detailFragment)
            .addToBackStack(null).commit()
    }

    override fun getItemCount(): Int = posts.size

    fun updatePost(newPosts: ArrayList<PostModel>) {
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }
}