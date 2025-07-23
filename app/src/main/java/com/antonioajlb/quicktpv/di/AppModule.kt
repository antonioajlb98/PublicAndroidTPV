package com.antonioajlb.quicktpv.di

import com.antonioajlb.di.dataModule
import com.antonioajlb.di.useCaseModule
import com.antonioajlb.di.viewmodelModule

val appModule = listOf(
    dataModule,
    useCaseModule,
    viewmodelModule

)