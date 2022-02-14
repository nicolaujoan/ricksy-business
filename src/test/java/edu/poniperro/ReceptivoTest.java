package edu.poniperro;

import static org.junit.Assert.*;

import observers.GuestDispatcher;
import org.junit.Test;
import org.junit.Before;
import payments.CreditCard;
import observers.CrystalExpender;
import receptivo.Receptivo;
import observers.UfosPark;


public class ReceptivoTest {

    Receptivo receptivo = null;
    CrystalExpender packExpender = null;
    UfosPark ufosPark = null;

    @Before
    public void setup() {
        receptivo = new Receptivo();
        packExpender = new CrystalExpender(4, 50);
        ufosPark = new UfosPark();
        ufosPark.add("blaster");
        ufosPark.add("lotyal");
    }

    @Test
    public void registraDispatcherTest() {
        receptivo.registra(ufosPark);
        receptivo.registra(packExpender);
        assertTrue(receptivo.contains(packExpender));
        assertTrue(receptivo.contains(ufosPark));

    }

    @Test
    public void dispatchUserTest() {
        // se prepara el receptivo
        receptivo.registra(ufosPark);
        receptivo.registra(packExpender);

        // se crea un usuario y se le despacha
        CreditCard parse = new CreditCard("parserito real", "0787790437383");
        receptivo.dispatch(parse);

        // comprobaciones que se le ha despachado

        /* En ufoPark */
        ufosPark.seeFlota();
        assertTrue(ufosPark.containsCard(parse.number()));

        /* En CrystalExpender */
        int lessStock = 3;   // el stock de crystal se ha reducido en 1
        assertEquals(lessStock, packExpender.stock());

    }
}
