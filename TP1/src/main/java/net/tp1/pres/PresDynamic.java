package net.tp1.pres;

import net.tp1.dao.IDao;
import net.tp1.metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class PresDynamic {
    public static void main(String[] args) {
        try {
            Scanner reader = new Scanner(new File("config.txt"));
            String daoClassName = reader.nextLine();
            String metierClassName = reader.nextLine();

            //Confirmer les noms
            System.out.println("Dao class path: "+daoClassName);
            System.out.println("Metier class path: "+metierClassName);
            System.out.println("=====================================");

            Class classDao = Class.forName(daoClassName);
            IDao dao = (IDao) classDao.newInstance();

            Class classMetier = Class.forName(metierClassName);
            IMetier metier = (IMetier) classMetier.getConstructor(IDao.class).newInstance(dao);

            System.out.println("Result: "+metier.calcule());

        } catch (FileNotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
