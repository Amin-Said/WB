package me.aminsaid.core

import me.aminsaid.core.utils.ApiErrorHandler
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiErrorHandlerTest {

    @Test
    fun testGetMsgFromErrorCode() {
        // Test cases for each error code
        val testCases = listOf(
            400 to ApiErrorHandler.getMsgFromErrorCode(400),
            401 to ApiErrorHandler.getMsgFromErrorCode(401),
            403 to ApiErrorHandler.getMsgFromErrorCode(403),
            404 to ApiErrorHandler.getMsgFromErrorCode(404),
            500 to ApiErrorHandler.getMsgFromErrorCode(500),
            503 to ApiErrorHandler.getMsgFromErrorCode(503),
            600 to ApiErrorHandler.getMsgFromErrorCode(600),
            null to ApiErrorHandler.getMsgFromErrorCode(null)
        )

        for ((errorCode, expectedErrorMsgRes) in testCases) {
            val apiErrorHandler = ApiErrorHandler.getMsgFromErrorCode(errorCode)
            assertEquals(expectedErrorMsgRes.errorMsgRes, apiErrorHandler.errorMsgRes)
        }
    }
}