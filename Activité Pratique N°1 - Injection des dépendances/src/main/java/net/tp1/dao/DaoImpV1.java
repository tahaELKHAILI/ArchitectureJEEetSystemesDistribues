package net.tp1.dao;

import org.springframework.stereotype.Component;

@Component ("d")

public class DaoImpV1 implements IDao {
    @Override
    public double getData() {
        System.out.println("Version base de donnee");
        double temp = 50;
        return temp;
    }
}
