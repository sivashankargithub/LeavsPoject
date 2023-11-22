package com.sangamone.projects.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangamone.projects.model.EmailRequestentity;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {
	
	@Autowired
	SendGrid sendGrid;
	public Response sendemail(EmailRequestentity emailRequest) {
		
		Mail mail = new Mail(new Email("suhasacharyag.sangamone@gmail.com"),emailRequest.getSubject(), new Email(emailRequest.getTo()),new Content("text/plain",emailRequest.getBody()));
		mail.setReplyTo(new Email("sivashankarchandrappa@gmail.com"));
		Request request=new Request();
		
		Response response=null;
		
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			response = this.sendGrid.api(request);
		}
		catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		return response;

	}
}
