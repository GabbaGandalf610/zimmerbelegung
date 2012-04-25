package de.hdu.zimmerbelegung.service;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    public static IZimmerKategorieManager getZimmerKategorieManager() {
        return (IZimmerKategorieManager) ctx.getBean("zimmerKategorieManager",ZimmerKategorieManager.class);
    }
}