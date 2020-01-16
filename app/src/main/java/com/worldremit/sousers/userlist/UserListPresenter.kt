package com.worldremit.sousers.userlist

import com.worldremit.sousers.api.model.User
import com.worldremit.sousers.common.BaseMvpPresenter
import com.worldremit.sousers.common.BaseMvpView
import com.worldremit.sousers.repository.UsersRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

data class FollowUser(val follow: Boolean, val user: User)
data class BlockUser(val block: Boolean, val user: User)

class UserListPresenter(private val usersRepository: UsersRepository) : BaseMvpPresenter() {
    private lateinit var view: UserListView
    private val userClickedObservable = PublishSubject.create<User>()
    private val followObservable = PublishSubject.create<FollowUser>()
    private val blockObservable = PublishSubject.create<BlockUser>()

    internal interface UserListView : BaseMvpView {
        fun showUsers(users: List<User>, openUserObservable: Observer<User>, followObservable: Observer<FollowUser>, blockObservable: Observer<BlockUser>)
        fun startUserDetails(userId: Int)
    }

    override fun onCreate(view: BaseMvpView) {
        this.view = view as UserListView
    }

    override fun onStart() {
        observe(
            usersRepository
                .fetchTopUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { users: List<User> -> view.showUsers(users, userClickedObservable, followObservable, blockObservable) },
                    { /*TODO HANDLE ERROR*/ } )
        )

        observe(userClickedObservable
            .subscribe{
                if (!it.isBlocked) {
                    view.startUserDetails(it.userId)
                }
            })

        observe(followObservable
            .subscribe{
                // TODO CALL API?
            })

        observe(blockObservable
            .subscribe{
                // TODO CALL API?
            })
    }

}