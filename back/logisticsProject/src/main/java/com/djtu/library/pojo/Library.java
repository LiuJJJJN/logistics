package com.djtu.library.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    @Null
    private String id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String openTime;
    @NotNull
    @NotBlank
    private String closeTime;
    @NotNull
    @NotBlank
    private String isOpen;
    @NotNull
    @NotBlank
    private String buildingId;

}
