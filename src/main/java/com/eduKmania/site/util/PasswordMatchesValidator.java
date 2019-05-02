package com.eduKmania.site.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.eduKmania.site.model.appuser.Users;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	} 
    
//   @Override
//   public void initialize(PasswordMatches constraintAnnotation) {       
//   }
//   @Override
//   public boolean isValid(Object obj, ConstraintValidatorContext context){   
//       UserDTO userDTO = (UserDTO) obj;
//       return userDTO.getPassword().equals(userDTO.getMatchingPassword());    
//   }     
}