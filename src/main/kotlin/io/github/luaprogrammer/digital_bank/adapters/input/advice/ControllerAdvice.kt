package io.github.luaprogrammer.digital_bank.adapters.input.advice

import io.github.luaprogrammer.digital_bank.adapters.input.advice.response.ErrorResponse
import io.github.luaprogrammer.digital_bank.adapters.output.exceptions.AccountNotFoundException
import io.github.luaprogrammer.digital_bank.ports.exceptions.InsufficientBalanceException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(AccountNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleAccountNotFoundException(
        exception: AccountNotFoundException,
        request: HttpServletRequest
    ): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(InsufficientBalanceException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleInsufficientBalanceException(
        exception: InsufficientBalanceException,
        request: HttpServletRequest
    ): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            error = HttpStatus.UNPROCESSABLE_ENTITY.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleDataIntegrityViolationException(
        exception: DataIntegrityViolationException,
        request: HttpServletRequest
    ): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            error = HttpStatus.UNPROCESSABLE_ENTITY.name,
            message = "Dados duplicados",
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        val errors = ex.bindingResult.allErrors.associate {
            (it as FieldError).field to it.defaultMessage
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}
