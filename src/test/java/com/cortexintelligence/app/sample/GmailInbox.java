package com.cortexintelligence.app.sample;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GmailInbox {

    public static void main(String[] args) {
        GmailInbox gmail = new GmailInbox();
        gmail.read();
    }

    public void read() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("/Users/lennonjesus/Dev/projetos/crawler/src/test/resources/gmail.properties")));
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", "andre.nalin.rj@gmail.com","143252112");

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            int messageCount = inbox.getMessageCount();

            System.out.println("Total Messages:- " + messageCount);

            Message[] messages = inbox.getMessages();
            System.out.println("------------------------------");
            for (int i = 0; i < 10; i++) {
                System.out.println("Tutulo: " + messages[i].getSubject());
                System.out.println("Conteudo: " + messages[i].getContent());
            }
            inbox.close(true);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}