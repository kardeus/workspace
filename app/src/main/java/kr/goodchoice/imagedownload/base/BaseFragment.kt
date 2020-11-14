package kr.goodchoice.imagedownload.base

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(
    @LayoutRes private val layoutRes: Int = 0
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    // dp to px
    val Int.px : Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    // px to dp
    val Int.dp : Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()
}