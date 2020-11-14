package kr.goodchoice.imagedownload.domain

sealed class Results {
    class Success<T>(val data: T) : Results()
    class Error(val thrawable: Throwable) : Results()
}