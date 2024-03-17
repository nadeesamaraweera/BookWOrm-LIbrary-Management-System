package org.bookmanagement.Service.Custom;

import org.bookmanagement.Service.ForgetMailService;
import org.bookmanagement.Controller.Email.EmailController;
import org.bookmanagement.Controller.ForgetPassWord.ConfirmForgetPassWordFormController;
import org.bookmanagement.Controller.ForgetPassWord.ForgetPassWordFormController;
import org.bookmanagement.Controller.LoginPageController;
import org.bookmanagement.Repository.AdminRepository;
import org.bookmanagement.Repository.Custom.RepositoryFactory;
import org.bookmanagement.Repository.UserRepository;
import org.bookmanagement.Dto.AdminDto;
import org.bookmanagement.Entity.Admin;
import org.bookmanagement.Entity.User;
import org.bookmanagement.configure.SessionFactoryConfiguration;
import org.hibernate.Session;

import javax.mail.MessagingException;
import java.util.Random;

public class ForgetMailServiceImpl implements ForgetMailService {

    private final UserRepository userRepository = (UserRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.User);

    private final AdminRepository adminRepository = (AdminRepository) RepositoryFactory.getDaoFactory().getDao(RepositoryFactory.DaoType.admin);
    private Session session;
    @Override
    public boolean sendEmail(String email, String user) {

        session = SessionFactoryConfiguration.getInstance().getSession();

        if (user.equals("User")){
            userRepository.SetSession(session);

            User checkEmail = userRepository.CheckEmail(email);

            if (checkEmail != null){
                //send email
                SendMail(email);
                if (ForgetPassWordFormController.user.equals("User")){
                    LoginPageController.Admin_Username = checkEmail.getUsername();
                    return true;
                }
            }
        }

        if (user.equals("Admin")){
            adminRepository.SetSession(session);
            Admin checkEmail = adminRepository.CheckEmail(email);
            if (checkEmail != null){
                //send email
                SendMail(email);
                if (ForgetPassWordFormController.user.equals("Admin")){
                    LoginPageController.Admin_Username = checkEmail.getUsername();
                    AdminServiceImpl.data = new AdminDto(
                            checkEmail.getId(),
                            checkEmail.getName(),
                            checkEmail.getUsername(),
                            checkEmail.getPassword(),
                            checkEmail.getEmail()
                    );
                    return true;
                }
            }
        }


        return false;
    }

    void SendMail(String email){
        Random random = new Random();
        int randomValue = random.nextInt(9000) + 1000;
        ConfirmForgetPassWordFormController.otp = randomValue;
        try {
            EmailController.sendEmail(email, randomValue);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
