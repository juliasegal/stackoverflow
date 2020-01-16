package com.worldremit.sousers.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.worldremit.sousers.R
import com.worldremit.sousers.api.model.User
import io.reactivex.Observer

class UsersAdapter :
    RecyclerView.Adapter<UsersAdapter.StackOverflowUserViewHolder>() {
    private var userClick: Observer<User>? = null
    private var followClick: Observer<FollowUser>? = null
    private var blockClick: Observer<BlockUser>? = null

    private var users: List<User> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackOverflowUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return StackOverflowUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: StackOverflowUserViewHolder, position: Int) {
        holder.bind(users[position], followClick, blockClick)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setUsers(
        users: List<User>,
        userClick: Observer<User>?,
        followClick: Observer<FollowUser>?,
        blockClick: Observer<BlockUser>?
    ) {
        this.users = users
        this.userClick = userClick
        this.followClick = followClick
        this.blockClick = blockClick
        notifyDataSetChanged()
    }

    inner class StackOverflowUserViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bind(user: User, followClick: Observer<FollowUser>?, blockClick: Observer<BlockUser>?) {
            val userView =
                itemView.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.user_item)
            val txtName = itemView.findViewById<TextView>(R.id.name)
            val id = itemView.findViewById<TextView>(R.id.id)
            val userImage = itemView.findViewById<ImageView>(R.id.user_image)
            val followButton = itemView.findViewById<Button>(R.id.follow)
            val blockButton = itemView.findViewById<Button>(R.id.block)

            fun displayBlocked(isBlocked: Boolean) {
                userView.setBackgroundColor(ContextCompat.getColor(userView.context, if (isBlocked) R.color.grey else R.color.white))
                blockButton.text = followButton.context.getString( if (isBlocked) R.string.user_unblock else R.string.user_block)
            }

            fun displayFollowed(isFollowed: Boolean) {
                 followButton.text = followButton.context.getString( if (isFollowed) R.string.user_unfollow else R.string.user_follow)
            }

            txtName.text = user.displayName
            id.text = user.userId.toString()

            Glide.with(userImage.context)
                .load(user.profileImage)
                .apply(RequestOptions().override(120, 120))
                .apply(RequestOptions.circleCropTransform())
                .into(userImage)

            displayBlocked(user.isBlocked)
            displayFollowed(user.isFollowed)

            userView.setOnClickListener { userClick?.onNext(user) }

            followButton.setOnClickListener {
                followClick?.onNext(FollowUser(!user.isFollowed, user))
                user.isFollowed = !user.isFollowed
                displayFollowed(user.isFollowed)
            }

            blockButton.setOnClickListener {
                blockClick?.onNext(BlockUser(!user.isBlocked, user))
                user.isBlocked = !user.isBlocked
                displayBlocked(user.isBlocked)
            }
        }
    }
}