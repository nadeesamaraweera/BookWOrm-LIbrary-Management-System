package org.bookmanagement.Bo;

public interface ForgetMailService extends SuperService{
    boolean sendEmail(String email, String otp);
}
