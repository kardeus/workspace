package kr.goodchoice.imagedownload.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_result.*
import kr.goodchoice.imagedownload.R
import kr.goodchoice.imagedownload.base.BaseFragment
import kr.goodchoice.imagedownload.base.BaseRecyclerView
import kr.goodchoice.imagedownload.databinding.ListItemImagesBinding
import kr.goodchoice.imagedownload.model.Documents
import kr.goodchoice.imagedownload.model.SearchResponse
import kr.goodchoice.imagedownload.view.component.recyclerview.VerticalItemDecoration

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SearchResultFragment : BaseFragment(
    layoutRes = R.layout.fragment_search_result
) {

    private var response: SearchResponse? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        response = arguments?.get("response") as? SearchResponse

        rv_image?.apply {
            adapter = object : BaseRecyclerView.Adapter<Documents, ListItemImagesBinding>(
                layoutResId = R.layout.list_item_images
            ){

            }.apply {
                addItemDecoration(
                    VerticalItemDecoration(8.dp)
                )
                replaceAll(response?.documents)
            }
        }

    }
}