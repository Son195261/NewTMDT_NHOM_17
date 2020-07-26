package nhom17.thuongmaidientu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public SendEmail(JavaMailSender javaMailSender) {
		// TODO Auto-generated constructor stub
		this.javaMailSender = javaMailSender;
	}

	public void sendNotification(String email, int maxacnhan) throws MailException
	{
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(email);
		mail.setFrom("maithaison1998@gmail.com");
		mail.setSubject("Mã xác nhận");
		mail.setText(Integer.toString(maxacnhan));
		javaMailSender.send(mail);
	}
}
