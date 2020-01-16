package com.worldremit.sousers.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.worldremit.sousers.App
import com.worldremit.sousers.R
import com.worldremit.sousers.api.model.User
import com.worldremit.sousers.common.BaseMvpActivity
import com.worldremit.sousers.user.UserDetailsPresenter.UserDetailsView
import kotlinx.android.synthetic.main.activity_user_details.*


class UserDetailsActivity : BaseMvpActivity(), UserDetailsView {
    private lateinit var userDetailsPresenter: UserDetailsPresenter

    companion object {
        private const val USER_ID = "USER_ID"

        fun newInstance(context: Context, imageId: Int): Intent {
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra(USER_ID, imageId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        val app = application as App
        userDetailsPresenter = UserDetailsPresenter(app.usersRepository, intent.getIntExtra(USER_ID, 0))
        userDetailsPresenter.onCreate(this)
        userDetailsPresenter.onStart()
    }

    override fun showUser(user: User) {
        title = user.displayName
        Glide.with(user_image.context)
            .load(user.profileImage)
            .apply(RequestOptions().override(120, 120))
            .apply(RequestOptions.circleCropTransform())
            .into(user_image)
    }
}