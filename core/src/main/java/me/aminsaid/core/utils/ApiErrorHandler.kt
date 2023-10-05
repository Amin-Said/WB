package me.aminsaid.core.utils

import me.aminsaid.core.R


sealed class ApiErrorHandler(val errorMsgRes: Int?) {
    object InValidRequestError : ApiErrorHandler(
        R.string.invalid_request
    )

    object UnAuthorizedError : ApiErrorHandler(
        R.string.unauthorized
    )

    object PermissionDeniedError : ApiErrorHandler(
        R.string.permission_denied
    )

    object NotFoundError : ApiErrorHandler(
        R.string.not_found
    )

    object ServerDownError : ApiErrorHandler(
        R.string.server_down
    )

    object EmptyResponseError : ApiErrorHandler(
        R.string.empty_response
    )

    object ReachLimitExceeded : ApiErrorHandler(
        R.string.limit_exceeded
    )


    object UnKnownError : ApiErrorHandler(
        null
    )


    companion object {
        private const val INVALID_REQUEST_CODE = 400
        private const val UNAUTHORIZED_CODE = 401
        private const val PERMISSION_DENIED_CODE = 403
        private const val NOT_FOUND_CODE = 404
        private const val SERVER_DOWN_CODE = 500
        private const val LIMIT_EXCEEDED_CODE = 503
        const val EMPTY_RESPONSE_CODE = 600

        fun getMsgFromErrorCode(code: Int?): ApiErrorHandler {
            return when (code) {
                INVALID_REQUEST_CODE -> InValidRequestError
                UNAUTHORIZED_CODE -> UnAuthorizedError
                PERMISSION_DENIED_CODE -> PermissionDeniedError
                NOT_FOUND_CODE -> NotFoundError
                SERVER_DOWN_CODE -> ServerDownError
                LIMIT_EXCEEDED_CODE -> ReachLimitExceeded
                EMPTY_RESPONSE_CODE -> EmptyResponseError
                else -> UnKnownError

            }
        }
    }
}
