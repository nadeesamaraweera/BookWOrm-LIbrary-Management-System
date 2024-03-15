package org.bookmanagement.Service;

public interface ForgetMailService extends SuperService{
    boolean sendEmail(String email, String otp);
}
