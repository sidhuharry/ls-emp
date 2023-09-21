package com.block.demo.lsemp

import com.block.demo.lsemp.di.appModule
import com.block.demo.lsemp.di.networkModule
import com.block.demo.lsemp.di.repoModule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules

class KoinDiTest : KoinTest {

    @Test
    fun verifyKoinApp() {
        checkKoinModules(networkModule, appModule, repoModule)
    }
}
