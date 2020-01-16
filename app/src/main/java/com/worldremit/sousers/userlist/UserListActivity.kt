package com.worldremit.sousers.userlist

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.worldremit.sousers.App
import com.worldremit.sousers.R
import com.worldremit.sousers.api.model.User
import com.worldremit.sousers.common.BaseMvpActivity
import com.worldremit.sousers.user.UserDetailsActivity
import io.reactivex.Observer


class UserListActivity : BaseMvpActivity(), UserListPresenter.UserListView {
    private lateinit var userListPresenter: UserListPresenter
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        val app = application as App
        userListPresenter = UserListPresenter(app.usersRepository)
        userListPresenter.onCreate(this)
        userListPresenter.onStart()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = UsersAdapter()
        val list = findViewById<RecyclerView>(R.id.users_list)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this)
    }

    override fun showUsers(
        users: List<User>,
        openUserObservable: Observer<User>,
        followObservable: Observer<FollowUser>,
        blockObservable: Observer<BlockUser>
    ) {
        adapter.setUsers(users, openUserObservable, followObservable, blockObservable)
    }

    override fun startUserDetails(userId: Int) {
        startActivity(UserDetailsActivity.newInstance(this, userId))
    }
}