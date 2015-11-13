package edu.gatech.edutech.smarterap.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.gatech.edutech.smarterap.validators.ResetPasswordValidator;

/**
 * @author Elder Crisostomo
 * @author Joey Slater - Updated for SmarterAP
 */
@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ResetPasswordController
{
	@Autowired
	private ResetPasswordValidator resetPasswordValidator;

}
