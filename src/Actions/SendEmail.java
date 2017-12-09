/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Danielle S. França
 */
public class SendEmail {
    
    public void mandarEmail(String email,String usuario,String senhaUsuario){
        String mailUsuario = "ggmk67142@gmail.com";
        String mailSenha = "gmkW48d3o14";
        String mailPortaSMTP = "587";
        String mailHost = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", mailHost);
        props.put("mail.smtp.port", mailPortaSMTP);
        props.put("mail.smtp.socketFactory.port", mailPortaSMTP);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.ssl.trust", mailHost);
        props.put("mail.smtp.auth", true);
        props.put("mail.debug", true);


        SimpleAuth auth = new SimpleAuth(mailUsuario, mailSenha);
        Session session = Session.getDefaultInstance(props, auth);


        MimeMessage message = new MimeMessage(session);

        try {

            Address from = new InternetAddress("ggmk67142@gmail.com");
            Address to = new InternetAddress(email);

            message.setFrom(from);
            message.addRecipient(Message.RecipientType.TO, to);
            message.setSentDate(new Date());
            message.setText("Email para recuperação de senha: \nUsuário:"+usuario+"\nSenha:"+senhaUsuario);
            message.setSubject("Recuperação de Senha do Gerenciador Revendedoras Mary Kay!");
            Transport.send(message);

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }
}
