package net.tp1.metier;

import net.tp1.dao.IDao;

public class MetierImpV1 implements IMetier {
    private IDao dao; //Couplage faible

    public MetierImpV1(IDao dao){
        this.dao = dao;
    }

    @Override
    public double calcule() {
        double data = dao.getData();
        return data*43/3;
    }

}
