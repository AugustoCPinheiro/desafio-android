package com.picpay.desafio.android.contacts

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.contacts.RecyclerViewMatchers.atPosition
import com.picpay.desafio.android.contacts.ui.ContactsActivity
import com.picpay.desafio.android.contacts.ui.ContactsState
import com.picpay.desafio.android.contacts.ui.ContactsViewModel
import com.picpay.desafio.android.core_entity.User
import io.mockk.every
import io.mockk.mockk
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

    @Before
    fun setup() {
        startKoin {
            modules(module {
                viewModel { mockViewModel }
            })
        }

        every { mockViewModel.contactsState }.returns(mockLiveData)
    }

    @After
    fun teardown() {
        stopKoin()
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