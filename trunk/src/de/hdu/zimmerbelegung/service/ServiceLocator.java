package de.hdu.zimmerbelegung.service;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.hdu.zimmerbelegung.manager.BelegungManager;

public class ServiceLocator {
  private static ApplicationContext ctx;

    static {
        ctx = new ClassPathXmlApplicationContext("spring-hibernate.xml");
    }

    private ServiceLocator() {
    }

    public static SessionFactory getSessionFactory() {
        return (SessionFactory) ctx.getBean("factory",SessionFactory.class);
    }

    public static BelegungManager getBuchungManager() {
        return (BelegungManager) ctx.getBean("buchungManager",BelegungManager.class);
    }
}