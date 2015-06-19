package com.cortexintelligence.app.crawler.impl;

import com.cortexintelligence.app.crawler.ICrawler;
import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import org.apache.commons.lang.ArrayUtils;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by lennonjesus on 18/06/15.
 */
public class GmailCrawlerImpl implements ICrawler {

    @Override
    public void start(String ... args) {
        if (ArrayUtils.isEmpty(args) || ArrayUtils.getLength(args) < 2) {
            throw new ParametrosInsuficientesException();
        }


        // FIXME validar
        String user = args[0];
        String pass = args[1];

        Properties props = new Properties();
        try {
            //FIXME remover path hardcoded
            props.load(new FileInputStream(new File("/Users/lennonjesus/Dev/projetos/crawler/src/test/resources/gmail.properties")));
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", user, pass);

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();

            for (Message message : messages) {
                System.out.println("Tutulo: " + message.getSubject());
                System.out.println("Conteudo: " + message.getContent());
            }
            inbox.close(true);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
