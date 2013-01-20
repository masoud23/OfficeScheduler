/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedmanager;

import java.util.Map;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author Masoud
 */
public class EmailManager {
    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
    
    public EmailManager(){
        
    }
    
    public void setMailSender(JavaMailSender mailSender){
        this.mailSender = mailSender;
        ((JavaMailSenderImpl)this.mailSender).getJavaMailProperties().setProperty("zimbra.mtnmedical.com","true");
    }
    
    public void setVelocityEngine(VelocityEngine velocityEngine){
        this.velocityEngine=velocityEngine;
    }
    
    /**
     * This method is here for backward compatability.  Earlier methods did not make use of the send as html flag.  Use of this method will
     * send the email without sending it as html.
     * @param model
     * @param template
     * @param to
     * @param from
     * @param subject
     * @throws Exception
     */
    public void sendEmail(final Map model,final String template,final String to, final String from,final String subject) throws Exception{
        sendEmail(model,template,to,from,subject,false);
    }
    
    /**
     *  An abstract way to send email to anyone with a velocity template.
     *   Just make sure the velocity template is in the classpath.
     *  @param the model used by the velocity templating system.
     *  @param The path to the velocity template
     *  @param the email address of the recipient in string form.
     *  @param  The email address of the sender in string form.
     *  @param  The subject used for this email.
     */
    public void sendEmail(final Map model,final String template,final String to,final String from,final String subject,final boolean sendAsHTML) throws Exception{
        MimeMessagePreparator prep = new MimeMessagePreparator(){
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(to);
                message.setFrom(from);
                message.setSubject(subject);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, model);
                message.setText(text, sendAsHTML);
            }
        };
        this.mailSender.send(prep);
      }
    
    /**
     * This method is provided for backward compatability.  The addition of the send as html property made this neccesary.  Any use of this method will send the mail with true in the
     * send as html.
     * @param model
     * @param template
     * @param to
     * @param from
     * @param reply
     * @param subject
     * @throws Exception
     */
    public void sendEmail (final Map model,final String templ, final InternetAddress[] to,final InternetAddress from,
            final InternetAddress reply, final String subject) throws Exception{
        sendEmail(model, templ, to, from, reply, subject, true);
    }
    
    /**
     * This is the method that will actually do the work of sending an email to the addresses included.
     * @param model
     * @param template
     * @param to
     * @param from
     * @param reply
     * @param subject
     * @param sendAsHtml
     * @throws Exception
     */
    public void sendEmail(final Map model,final String temple, final InternetAddress[] to, final InternetAddress from,
            final InternetAddress reply, final String subject, final boolean sendAsHTML) throws Exception{
        MimeMessagePreparator prep = new MimeMessagePreparator(){

            @Override
            public void prepare(MimeMessage mm) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mm);
                message.setTo(to);
                message.setFrom(from);
                message.setReplyTo(reply);
                message.setSubject(subject);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,temple,model);
                message.setText(text,sendAsHTML);
            }            
        };
        this.mailSender.send(prep);
    }
}
