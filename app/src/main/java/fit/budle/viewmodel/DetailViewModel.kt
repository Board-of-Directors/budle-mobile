package fit.budle.viewmodel

import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {
    fun getDetailText(): String {
        // some imaginary backend call
        return "Detail"
    }
}
