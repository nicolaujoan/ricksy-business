package edu.poniperro;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import payments.CreditCard;
import observers.CrystalExpender;

public class CrystalExpenderTest {

    CrystalExpender crystalExpender = null;

    @Before
    public void setup() {
        crystalExpender = new CrystalExpender(5, 50);
    }

    @Test
    public void displayInfoTest() {
        String desiredOutput = "stock: 5\n" +  "cost: 50.0\n";
        assertEquals(crystalExpender.toString(), desiredOutput);
    }

    @Test
    public void compraPackTest() {
        CreditCard lincler = new CreditCard("joseph lincler", "89977968675755");
        crystalExpender.dispatch(lincler);
        String desiredOutput = "stock: 4\n" +  "cost: 50.0\n";
        assertEquals(desiredOutput, crystalExpender.toString());
        assertEquals(2950.0, lincler.credit(), 0.0);

    }

    @Test
    public void compraSinCreditoTest() {
        CreditCard moroso = new CreditCard("morosium", "03703782870329");
        moroso.pay(3000);
        assertEquals(0.0, moroso.credit(), 0.0);  // moroso sin blanca

        // solicita crystal
        // pero está sin blanca
        crystalExpender.dispatch(moroso);
        String desiredOutput = "stock: 5\n" +  "cost: 50.0\n";  // info inicial
        assertEquals(desiredOutput, crystalExpender.toString());  // se mantiene intacta
        // el moroso no ha podido pagar
    }
}
