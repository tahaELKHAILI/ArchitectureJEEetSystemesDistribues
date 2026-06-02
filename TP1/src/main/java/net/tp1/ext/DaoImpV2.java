package net.tp1.ext;

import net.tp1.dao.IDao;

public class DaoImpV2 implements IDao {

    @Override
    public double getData() {
        System.out.println("Version web services");
        double t = 12;
        return t;
    }
}
