package de.hdu.zimmerbelegung.service;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.hdu.zimmerbelegung.manager.AdminGastManager;
import de.hdu.zimmerbelegung.manager.AdminTagStatusManager;
import de.hdu.zimmerbelegung.manager.AdminZeitraumStatusManager;
import de.hdu.zimmerbelegung.manager.AdminBuchungManager;
import de.hdu.zimmerbelegung.manager.AdminManager;
import de.hdu.zimmerbelegung.manager.BelegungManager;
import de.hdu.zimmerbelegung.manager.ZimmerManager;

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

    public static AdminManager getAdminManager() {
		return (AdminManager) ctx.getBean("AdminManager",AdminManager.class);
	}
    
    public static ZimmerManager getZimmerManager() {
		return (ZimmerManager) ctx.getBean("ZimmerManager",ZimmerManager.class);
	}
	    
    public static AdminBuchungManager getAdminBuchungManager() {
		return (AdminBuchungManager) ctx.getBean("adminBuchungManager",AdminBuchungManager.class);
	}
	   public static AdminGastManager getAdminGastManager() {
 		return (AdminGastManager) ctx.getBean("adminGastManager",AdminGastManager.class);
 	}
    
    public static AdminTagStatusManager getAdminTagStatusManager() {
 		return (AdminTagStatusManager) ctx.getBean("adminTagStatusManager",AdminTagStatusManager.class);
 	}
    
    public static AdminZeitraumStatusManager getAdminZeitraumStatusManager() {
 		return (AdminZeitraumStatusManager) ctx.getBean("adminZeitraumStatusManager",AdminZeitraumStatusManager.class);
 	}
}