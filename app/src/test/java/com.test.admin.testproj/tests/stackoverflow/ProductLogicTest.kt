package com.test.admin.testproj.tests.stackoverflow

import com.test.admin.testproj.tests.stackoverflow.a.Product
import com.test.admin.testproj.tests.stackoverflow.a.ProductLogic
import com.test.admin.testproj.tests.stackoverflow.a.Version
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.Arrays.asList

@RunWith(ParameterizedRobolectricTestRunner::class)
@Config(sdk = [19], packageName = "com.test.admin.testproj")
class ProductLogicTest(private val product: Product?,
                       private val shouldShow: Boolean) {

    @Before
    fun setUp() {
        if (product != null) {
            doReturn(VERSION).`when`(product).version // (2) Works fine
        }
    }

    @Test
    fun shouldShow() {
        assertThat(ProductLogic(product).shouldShow(), `is`(shouldShow))
    }


    companion object {
        private val VERSION = Version(1, 5)

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{index}: {0} => {1}")
        fun data(): Collection<Array<Any?>> {
            val productMock = mock(Product::class.java)
            doReturn(VERSION).`when`(productMock).version // (1) Works fine
            return asList(
                    arrayOf(productMock, true),
                    arrayOf(productMock, false)
            )
        }
    }
}