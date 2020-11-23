package com.algaworks.algafood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object>{

	private String zeroField;
	private String descriptionField;
	private String descriptionmandatory;
			
	@Override
	public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
		this.zeroField = constraintAnnotation.zeroField();
		this.descriptionField = constraintAnnotation.descriptionField();
		this.descriptionmandatory = constraintAnnotation.descriptionmandatory();
	}
	
	@Override
	public boolean isValid(Object objectToValidate, ConstraintValidatorContext context) {
		boolean isValid = true;
		
		try {
			BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(objectToValidate.getClass(), this.zeroField)
					.getReadMethod().invoke(objectToValidate);
			
			String description = (String) BeanUtils.getPropertyDescriptor(objectToValidate.getClass(), this.descriptionField)
					.getReadMethod().invoke(objectToValidate);
			
			if (value != null && BigDecimal.ZERO.compareTo(value) == 0 && description != null) {
				isValid = description.toLowerCase().contains(this.descriptionmandatory.toLowerCase());
			}
			
			return isValid;
			
		} catch (Exception e) {
			throw new ValidationException(e);
		}		
		
	}
	
	

}
