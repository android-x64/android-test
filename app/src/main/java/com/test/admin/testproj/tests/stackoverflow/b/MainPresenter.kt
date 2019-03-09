package com.test.admin.testproj.tests.stackoverflow.b

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainPresenter(private val getInfoUsecase: GetInfoUsecase,
                    private val uiContext: CoroutineContext = Dispatchers.Main) : CoroutinePresenter(uiContext) {
    lateinit var view: View

    fun inject(view: View) {
        this.view = view
    }

    fun onResume() {
        refreshInfo()
    }

    fun refreshInfo() = launch {
        view.showLoading()
        view.showInfo(getInfoUsecase.getInfo())
        view.hideLoading()
    }

    fun getHeroesFromRepository(page: Int) {
        launch {
            view.showHeroes(getInfoUsecase.getHeroes(page))
            view.hideLoading()
        }
    }


    interface View {
        fun showLoading()
        fun hideLoading()

        fun showInfo(info: String)
        fun showHeroes(info: String?)
    }
}