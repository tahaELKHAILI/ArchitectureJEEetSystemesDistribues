package net.tp1.pres;

import net.tp1.dao.DaoImpV1;
import net.tp1.metier.MetierImpV1;

public class PresStatic {
    public static void main(String[] args) {
        DaoImpV1 d = new DaoImpV1();
        MetierImpV1 m = new MetierImpV1(d);
        System.out.println("Result: "+m.calcule());
    }
}
