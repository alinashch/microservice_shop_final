package com.example.productservice.model.request;

import com.example.productservice.annotation.Duration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class ProductUpdateRequest {

    @NotBlank(message = "Логин не может быть пустым")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 8, max = 20, message = "Логин не может быть меньше 8 и больше 20 символов")
    private String productName;

    @NotBlank(message = "Логин не может быть пустым")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 8, max = 20, message = "Логин не может быть меньше 8 и больше 20 символов")
    private Long productPrice;

    @NotBlank(message = "Логин не может быть пустым")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 8, max = 20, message = "Логин не может быть меньше 8 и больше 20 символов")
    private String productDescription;

    @NotBlank(message = "Логин не может быть пустым")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 8, max = 20, message = "Логин не может быть меньше 8 и больше 20 символов")
    private String manufacturer;

    @NotBlank(message = "Логин не может быть пустым")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 8, max = 20, message = "Логин не может быть меньше 8 и больше 20 символов")
    private Integer productNumInStock;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Duration
    private LocalDate dateOfCreate;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Duration
    private LocalDate dateOfExpiration;


}
