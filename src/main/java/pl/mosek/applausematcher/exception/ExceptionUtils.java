package pl.mosek.applausematcher.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.experimental.UtilityClass;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class ExceptionUtils {

    static final String DEVICE_NAME_NOT_FOUND = "Device description not found for: ";
    static final String CANNOT_DESERIALIZE_GIVEN_TYPE = "Cannot deserialize value of a given type.";
    static final String CANNOT_DESERIALIZE_GIVEN_TYPES = "Cannot deserialize value for one or more fields.";
    static final String CANNOT_PARSE_JSON = "Cannot correctly parse JSON.";
    static final String FIELDS_NOT_RECOGNIZED = "One or more fields were not recognized.";
    static final String FIELD_NOT_RECOGNIZED = "Field not recognized.";
    static final String MEDIA_NOT_SUPPORTED = "Media is not supported or empty body request.";
    static final String METHOD_ARGUMENT_NOT_VALID = "One or more fields have arguments not valid.";

    static List<String> getMessagesForHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        if (ex.getRootCause() instanceof InvalidFormatException) {
            return List.of(CANNOT_DESERIALIZE_GIVEN_TYPES);
        } else if (ex.getRootCause() instanceof JsonParseException) {
            return List.of(CANNOT_PARSE_JSON);
        }
        return List.of(FIELDS_NOT_RECOGNIZED);
    }

    static List<ApiFieldError> getApiFieldErrorsFromHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        if (ex.getRootCause() instanceof InvalidFormatException rootCause) {
            return List.of(new ApiFieldError(rootCause.getPath().get(0).getFieldName(), CANNOT_DESERIALIZE_GIVEN_TYPE));
        } else if (ex.getRootCause() instanceof JsonParseException) {
            return Collections.emptyList();
        }

        return List.of(new ApiFieldError(((UnrecognizedPropertyException) Objects.requireNonNull(ex.getRootCause())).getPropertyName(), FIELD_NOT_RECOGNIZED));
    }

    static List<String> getMessagesFromMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> messages = new ArrayList<>();
        ex.getBindingResult().getGlobalErrors()
                .forEach(error -> messages.add(error.getDefaultMessage()));

        return messages;
    }

    static List<ApiFieldError> getApiFieldErrorsFromMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<ApiFieldError> apiFieldErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> apiFieldErrors.add(new ApiFieldError(error.getField(), error.getDefaultMessage())));

        return apiFieldErrors;
    }
}
