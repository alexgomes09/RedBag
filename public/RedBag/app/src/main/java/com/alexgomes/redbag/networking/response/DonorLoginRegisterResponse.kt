package com.alexgomes.redbag.networking.response

import com.alexgomes.redbag.networking.DonorModel

/**
 * Created by agomes on 10/18/18.
 */
class DonorLoginRegisterResponse {
    val success: String = ""
    val message: String = ""
    val token: String = ""
    val donor = DonorModel()
}