package net.tp1.pres;

import net.tp1.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresSpringAnotation {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("net.tp1");

        IMetier metier = (IMetier) applicationContext.getBean("metier");
        System.out.println("Version annotation Result: "+metier.calcule());
    }
}
