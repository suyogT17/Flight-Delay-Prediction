package com.delaypredictions.util;

public class SystemConstants {

	
	public static final String		CONFIRMATION_CODE_EMAIL_BODY	= "<html></body>Dear FirstName ,"
			+ "<p>Your Registration request has been processed."
			+"<br>Username is: <b>userid</b>."
			+ "<br>Password is: <b>password</b>."
			+ "<br><br>Should you require any assistance, please do not hesitate to contact us on admin@shoppingmania.com"
			+ "<p>Regards,<br/>Shopping Mania Team</p>"
			+ "<p><em>Note: This is automated email notification from TWSC. Please do not reply to this email.</em></p></body></html>";
	
	public static final String 		CONFIRMATION_CODE_EMAIL_SUBJECT = "User Information";

	public static final String REGISTRATION_COMPLETE_EMAIL_BODY = "<html></body>Dear FirstName ,"
			+ "<p>Your Account has been Created"
			+"<br>Username is: <b> userid </b>."
			+ "<br>Password is: <b> password </b>."
			+ "<br><br>Should you require any assistance, please do not hesitate to contact us on admin@tsc.com"
			+ "<p>Regards,<br/>TSC Team</p>"
			+ "<p><em>Note: This is automated email notification from TWSC. Please do not reply to this email.</em></p></body></html>";

	public static final String REGISTRATION_COMPLETE_EMAIL_SUBJECT ="Confirmation Mail";

	public static final String CHANGE_PASSWORD_EMAIL_BODY ="<html></body>Dear FirstName ,"
			+ "<br>Verification code: <b> vcode </b>."
			+ "<br><br>Should you require any assistance, please do not hesitate to contact us on admin@tsc.com"
			+ "<p>Regards,<br/>TSC Team</p>"
			+ "<p><em>Note: This is automated email notification from TWSC. Please do not reply to this email.</em></p></body></html>";

	public static final String CHANGE_PASSWORD_EMAIL_SUBJECT = "verification code";
	
	

	public static final String FEEDBACK_EMAIL_BODY ="<html></body><p><em>Username:<b>UserName</b>,"
			+ "<br>Feedback: <b> userfeedback </b>."
			+"</em></p></body></html>";

	public static final String FEEDBACK_EMAIL_SUBJECT = "User Feedback";
	
	

	public static final StringBuffer  ORDER_CONFIRMATION_MAIL=new StringBuffer("");
	public static final String 		ORDER_COMPLETE_EMAIL_SUBJECT = "Slot Booked";

	

}

