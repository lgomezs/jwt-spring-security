package pe.com.config.security.appSecurity.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ResponseModel<T> {

    private Integer code;
    private String status;
    private T data;
    private Map<String, List<String>> errors;

    public void addErrors(String key, String error) {
        initializeEmptyErrors();
        initializeListIfNull(key);

        errors.get(key).add(error);
    }
    
    private void initializeEmptyErrors() {
        if(errors == null) {
            errors = new HashMap<>();
        }
    }

    private void initializeListIfNull(String key) {
        errors.computeIfAbsent(key, k -> new ArrayList<>());
    }

    public static <T> ResponseModel<T> ok(T data) {
        Assert.notNull(data, "Data Must Not Null");
        return ResponseModel.status(HttpStatus.OK, data);
    }

    public static <T> ResponseModel<T> status(HttpStatus httpStatus, @Nullable T data) {
        return ResponseModel.<T>builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .data(data)
                .build();
    }
}
