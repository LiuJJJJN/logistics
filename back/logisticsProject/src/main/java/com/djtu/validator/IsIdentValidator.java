package com.djtu.validator;

import com.djtu.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsIdentValidator implements ConstraintValidator<IsIdent, String> {

    private boolean required = false;

    @Override
    public void initialize(IsIdent isIdent) {
        boolean required = isIdent.required();

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isIdent(s);
        }else {
            if (StringUtils.isEmpty(s)){
                return true;
            }else {
                return ValidatorUtil.isIdent(s);
            }
        }
    }

}
