package com.picpay.desafio.android

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.RecyclerViewMatchers.atPosition
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.ui.contacts.ContactsActivity
import com.picpay.desafio.android.ui.contacts.ContactsState
import com.picpay.desafio.android.ui.contacts.ContactsViewModel
import io.mockk.every
import io.mockk.mockk
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module


class ContactsActivityTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val mockLiveData = MutableLiveData<ContactsState>()

    private val mockViewModel = mockk<ContactsViewModel>(relaxed = true)

    @After
    fun teardown() {
        stopKoin()
    }

    @Before
    fun setup() {
        startKoin {
            modules(module {
                viewModel { mockViewModel }
            })
        }

        every { mockViewModel.contactsState }.returns(mockLiveData)
    }

    @Test
    fun shouldDisplayTitle() {
        launchActivity<ContactsActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        mockLiveData.postValue(
            ContactsState.Success(
                mockSuccessResponse
            )
        )
        launchActivity<ContactsActivity>().apply {
            onView(withId(R.id.recyclerView)).check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(withText("@eduardo.santos"))
                    )
                )
            )
        }

    }

    companion object {
        val mockSuccessResponse = listOf(
            User(
                "https://randomuser.me/api/portraits/men/9.jpg",
                "Eduardo Santos",
                0,
                "@eduardo.santos"
            )
        )
    }
}