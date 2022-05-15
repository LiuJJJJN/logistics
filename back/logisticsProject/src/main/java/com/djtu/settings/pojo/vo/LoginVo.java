package com.djtu.settings.pojo.vo;

import com.djtu.validator.IsIdent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    @NotNull(message = "{username.null}")
    private String username;
    @NotNull
    @Length(min = 6, max = 18)
    private String password;
    @NotNull
    @IsIdent(message = "{ident.pattern}")
    private String ident;
    private Boolean rememberMe;
}
