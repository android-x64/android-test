import com.test.admin.testproj.tests.stackoverflow.b.GetInfoUsecase
import com.test.admin.testproj.tests.stackoverflow.b.MainPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterTest {
    private lateinit var presenter: MainPresenter

    @Mock
    private lateinit var view: MainPresenter.View

    @Mock
    private lateinit var mockInfoUseCase: GetInfoUsecase

    val expectedInfo = "getInfo RESULT"

    @Before
    fun setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        presenter = MainPresenter(mockInfoUseCase, Dispatchers.Unconfined)
        presenter.inject(view)
        presenter.onCreate()


    }

    @Test
    fun onResume_RefreshView() = runBlocking {
        Mockito.`when`(mockInfoUseCase.getInfo()).thenReturn(expectedInfo)

        presenter.onResume()

        verify(view).showLoading()
        verify(view).showInfo(expectedInfo)
        verify(view).hideLoading()
    }

    @Test
    fun getHeroesFromRepository() = runBlocking {
        val expectedResult = "expectedHero"
        Mockito.`when`(mockInfoUseCase.getHeroes(0)).thenReturn(expectedResult)

        System.out.println("start " + System.currentTimeMillis()/1000)

        presenter.getHeroesFromRepository(0)

        verify(view).showHeroes(expectedResult)
        verify(view).hideLoading()

        System.out.println("end " + System.currentTimeMillis()/1000)

    }
}