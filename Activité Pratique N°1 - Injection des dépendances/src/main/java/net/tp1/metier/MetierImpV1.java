package net.tp1.metier;

import net.tp1.dao.DaoImpV1;
import net.tp1.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component ("metier")

public class MetierImpV1 implements IMetier {
    @Autowired
    private IDao dao; //Couplage faible

    public MetierImpV1() {
    }

    public MetierImpV1(IDao dao){
        this.dao = dao;
    }

    @Override
    public double calcule() {
        double data = dao.getData();
        return data*43/3;
    }

    public void setDao(DaoImpV1 dao) {
        this.dao = dao;
    }
}
