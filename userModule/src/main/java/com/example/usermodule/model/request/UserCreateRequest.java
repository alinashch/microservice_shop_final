package com.example.usermodule.model.request;

import com.example.usermodule.annotation.Duration;
import com.example.usermodule.model.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UserCreateRequest {

    @NotBlank(message = "Имя не может быть пустым")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 100, message = "Имя не может быть больше 100 символов")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 100, message = "Фамилия не может быть больше 100 символов")
    private String lastName;

    @NotBlank(message = "Телефон не может быть пустой")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 11, message = "Телефон не может быть больше 11 символов")
    private String phone;

    @Email(message = "Почта указана неверно")
    @NotBlank(message = "Почта не может быть пустой")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "Адрес не может быть пустой")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 100, message = "Адрес не может быть больше 100 символов")
    private Address address;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Duration
    private LocalDate dateOfBirth;

}
