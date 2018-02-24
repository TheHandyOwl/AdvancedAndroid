package com.tho.madridshops.domain.interactor

import com.tho.madridshops.domain.model.Activities
import com.tho.madridshops.domain.model.Shops


typealias CodeClosure = () -> Unit
typealias ActivitiesSuccessClosure = (activities: Activities) -> Unit
typealias ShopsSuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias Variant = Any
