package com.ale.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author alewu
 * @date 2020/7/9
 */
@Slf4j
public class FileValidator implements ConstraintValidator<File, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            ConstraintValidatorContext.ConstraintViolationBuilder builder =
                    context.buildConstraintViolationWithTemplate("file size can't be 0");
            builder.addConstraintViolation();
            return false;
        }
        return true;
    }
}
