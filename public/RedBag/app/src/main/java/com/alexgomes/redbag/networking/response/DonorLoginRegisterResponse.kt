package com.alexgomes.redbag.networking.response

import com.alexgomes.redbag.networking.generic.BaseModel
import com.alexgomes.redbag.networking.generic.DonorModel

/**
 * Created by agomes on 10/18/18.
 */
class DonorLoginRegisterResponse : BaseModel() {
    val token: String = ""
    val donor = DonorModel()
}