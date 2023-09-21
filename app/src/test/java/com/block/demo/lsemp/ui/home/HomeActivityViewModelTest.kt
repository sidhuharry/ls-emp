package com.block.demo.lsemp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.block.demo.lsemp.model.EmpType
import com.block.demo.lsemp.model.Employee
import com.block.demo.lsemp.model.EmployeeNetworkResponse
import com.block.demo.lsemp.model.NetworkResponse
import com.block.demo.lsemp.repo.EmployeeDetailsRepo
import kotlin.test.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class HomeActivityViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val dispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: HomeActivityViewModel

    @Mock
    lateinit var empDetailsRepo: EmployeeDetailsRepo

    private var mockEmployeeList = EmployeeNetworkResponse(
        listOf(
            Employee(
                "1",
                "HS",
                "787878",
                "a@gmail.com",
                "some stuff about me",
                "photosmall.png",
                "photoUrlLarge",
                "Alpha",
                EmpType.FULL_TIME
            ),
            Employee(
                "2",
                "HS",
                "787878",
                "a@gmail.com",
                "some stuff about me",
                "photosmall.png",
                "photoUrlLarge",
                "Alpha",
                EmpType.FULL_TIME
            ),
            Employee(
                "3",
                "HS",
                "787878",
                "a@gmail.com",
                "some stuff about me",
                "photosmall.png",
                "photoUrlLarge",
                "Alpha",
                EmpType.FULL_TIME
            )
        )
    )

    @Mock
    lateinit var loaderObserver: Observer<Boolean>

    @Mock
    lateinit var errorObserver: Observer<Boolean>

    @Mock
    lateinit var empListObserver: Observer<EmployeeNetworkResponse>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = spy(HomeActivityViewModel(empDetailsRepo))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test viewModel loading employees`() = runBlocking {
        whenever(empDetailsRepo.getEmployeeList()).thenReturn(
            flowOf(
                NetworkResponse.Success(
                    200,
                    mockEmployeeList
                )
            )
        )
        viewModel.loadEmp()

        viewModel.employees.observeForever(empListObserver)
        viewModel.isError.observeForever(errorObserver)
        viewModel.isLoading.observeForever(loaderObserver)

        assertEquals(3, viewModel.employees.value?.employees?.count())

        verify(loaderObserver).onChanged(false)
        verify(errorObserver).onChanged(false)
        verify(empListObserver).onChanged(mockEmployeeList)
    }
}
