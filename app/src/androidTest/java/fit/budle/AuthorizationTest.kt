package fit.budle

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fit.budle.di.config.SharedPrefConfig
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthorizationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loginTest() {
        val loginLabel = composeTestRule.activity.getString(R.string.btn_login)
        composeTestRule.onNodeWithText(loginLabel).performClick()
        composeTestRule.onNodeWithText("Подтвердить").assertIsDisplayed()
        composeTestRule.onNodeWithText("Введите Ваше имя")
            .assertIsDisplayed()
            .performTextInput("user1234")

        composeTestRule.onNodeWithText("Введите пароль").performTextInput("user1234user")
        composeTestRule.onNodeWithText("Подтвердить").performClick()

        composeTestRule.waitUntil(2000) {
            val node = try {
                composeTestRule.onNodeWithText("Поиск").fetchSemanticsNode()
            } catch (e: AssertionError) {
                null
            }
            node != null
        }
    }

    @Test
    fun registrationTest() {
        MockServer.server.dispatcher = MockServer.successDispatcher()
        val loginLabel = composeTestRule.activity.getString(R.string.btn_login)
        composeTestRule.onNodeWithText(loginLabel).performClick()
        composeTestRule.onNodeWithText("Подтвердить").assertIsDisplayed()
        composeTestRule.onNodeWithText("Введите Ваше имя")
            .assertIsDisplayed()
            .performTextInput("user12345")

        composeTestRule.onNodeWithText("Введите пароль").performTextInput("user12345user")
        composeTestRule.onNodeWithText("Подтвердить").performClick()
    }

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val sharedPreferences =
            context.getSharedPreferences(SharedPrefConfig.fileName, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
        composeTestRule.activityRule.scenario.recreate()
    }
}
