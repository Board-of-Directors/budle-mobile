package fit.budle

import fit.budle.request.response.DefaultBooleanResponse
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

class MockServer {
    companion object {
        val server = MockWebServer()
        fun successDispatcher(): Dispatcher {
            return object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    return when (request.path) {
                        else -> MockResponse().setResponseCode(200)
                            .setBody(
                                DefaultBooleanResponse(
                                    result = true,
                                    exception = null
                                ).toString()
                            )
                    }
                }
            }
        }
    }
}
