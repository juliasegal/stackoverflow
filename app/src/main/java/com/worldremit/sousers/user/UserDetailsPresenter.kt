package com.worldremit.sousers.user

import com.worldremit.sousers.api.model.User
import com.worldremit.sousers.common.BaseMvpPresenter
import com.worldremit.sousers.common.BaseMvpView
import com.worldremit.sousers.repository.UsersRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class UserDetailsPresenter(private val usersRepository: UsersRepository, private val userId: Int) : BaseMvpPresenter() {
    private lateinit var view: UserDetailsView

    internal interface UserDetailsView : BaseMvpView {
        fun showUser(user: User)
    }

    override fun onCreate(view: BaseMvpView) {
        this.view = view as UserDetailsView
    }

    override fun onStart() {
        observe(
            usersRepository
                .fetchUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { it -> it?.let {view.showUser(it)} },
                    { /*TODO HANDLE ERROR*/ } )
        )
    }

}