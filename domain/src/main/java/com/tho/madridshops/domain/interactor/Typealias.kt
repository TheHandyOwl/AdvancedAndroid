package com.tho.madridshops.domain.interactor

import com.tho.madridshops.domain.model.Shops


typealias SuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias Variant = Any
