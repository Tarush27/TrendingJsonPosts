package com.tarush27.jsonposts.ui

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tarush27.jsonposts.R
import com.tarush27.jsonposts.apiCall.Post
import com.tarush27.jsonposts.apiCall.RetrofitClient
import com.tarush27.jsonposts.postAdapter.PostsAdapter
import com.tarush27.jsonposts.postModel.PostModel
import retrofit2.Call
import retrofit2.Callback


class PostsFragment : Fragment() {


    private lateinit var postRecyclerView: RecyclerView
    private lateinit var postAdapter: PostsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPostRecyclerView(view)
        fetchPosts()
    }

    private fun setupPostRecyclerView(view: View) {
        postRecyclerView = view.findViewById(R.id.postsRv)
        postRecyclerView.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.VERTICAL,
            false
        )
        postAdapter = PostsAdapter()
        postRecyclerView.adapter = postAdapter
    }

    private fun fetchPosts() {
        val service = RetrofitClient.postService
        val responses: Call<List<Post>> = service.getPosts()
        responses.enqueue(object : Callback<List<Post>> {
            override fun onResponse(
                call: Call<List<Post>>,
                response: retrofit2.Response<List<Post>>
            ) {
                Log.d("onResponse", response.body().toString())

                val posts: List<PostModel>? = response.body()?.map {
                    PostModel(
                        postTitle = it.title,
                        postDescription = it.description
                    )
                }
                postAdapter.updatePost(posts as ArrayList)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("onFailure", "${t.message}")
            }

        })
    }

}