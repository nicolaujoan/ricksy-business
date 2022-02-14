package edu.poniperro;

import observers.UfosPark;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import payments.CreditCard;

public class UfoParkTest {
    UfosPark ufos = null;
    String[] ovnis = { "unx", "dox", "trex" };
    CreditCard abradolph = new CreditCard("Abradolph Lincler", "4916119711304546");
    CreditCard peptoni = new CreditCard("pep toni dimoni", "4916119711334547");
    CreditCard josepet = new CreditCard("josep de la mel", "4916119711304000");

    @Before
    public void setup() {
        ufos = new UfosPark();
        ufos.add("dox");
        ufos.add("trex");
    }

    /**
     * Testea el metodo añadir ovni
     * a la flota de ufos
     */
    @Test
    public void addUfoTest() {
        ufos.add(ovnis[0]);
        assertTrue(ufos.containsUfo("unx"));
    }

    /**
     * Testea el metodo reservar ovni
     * y cargar el pago en la tarjeta
     * de crédito.
     * El crédito de la tarjeta varía.
     */
    @Test
    public void dispatchTest() {
        double creditBefore = abradolph.credit();
        ufos.dispatch(abradolph);
        double creditAfter = abradolph.credit();
        assertTrue(creditBefore > creditAfter);

    }

    /**
     * Testea que no se pueda reservar un ovni
     * si no hay crédito suficiente en la tarjeta.
     * El crédito de la tarjeta no varía.
     */
    @Test
    public void dispatchNoCreditTest() {
        peptoni.pay(3000);  // me buiden sa cartera per badar
        double creditBefore = peptoni.credit();
        ufos.dispatch(peptoni);
        double creditAfter = peptoni.credit();
        assertFalse(ufos.containsCard(peptoni.number()));
        assertEquals(creditBefore, creditAfter, 0.0);
    }

    /**
     * Testea que no se pueda reservar un ovni
     * si ya existe un ovni reservado para esa tarjeta.
     * El crédito de la tarjeta no varía.
     */
    @Test
    public void dispatchUfoAlreadyReservedTest() {
        ufos.dispatch(josepet);  // josepet gets an ufo
        assertTrue(ufos.containsCard(josepet.number()));
        double creditBefore = josepet.credit();  // same
        ufos.dispatch(josepet);  // dispatched again but credit don't vary, already has an ufo
        double creditAfter = josepet.credit();  // same
        assertEquals(creditBefore, creditAfter, 0.0);
    }

    /**
     * Testea que no se pueda reservar un ovni
     * si no existe ninguno disponible (sin reservar).
     * El crédito de la tarjeta no varía.
     */
    @Test
    public void dispatchNoUfoAvaliableTest() {
        // despachamos a tres clientes y por tanto nos quedamos sin ufos
        CreditCard jaume = new CreditCard("Jaume Gomila", "91635753768858523");
        CreditCard dimoni = new CreditCard("dimoni cucarell", "81625753768858533");
        CreditCard jordiet = new CreditCard("jordiet", "41635253769958533");

        ufos.dispatch(jaume);
        ufos.dispatch(dimoni);

        // nos quedamos sin ufos disponibles
        ufos.seeFlota();

        // llega un nuevo cliente
        CreditCard andreu = new CreditCard("Andreu Sorell Pou", "41635753768858533");
        double creditBefore = andreu.credit();
        ufos.dispatch(andreu);  // no hay ufos libres, su credito no varía
        double creditAfter = andreu.credit();
        assertEquals(creditBefore, creditAfter, 0.0);
    }

    /**
     * Devuelve el UFO reservado para la tarjeta.
     */
    @Test
    public void getUfoOfTest() {
        CreditCard andreu = new CreditCard("Andreu Sorell Pou", "41635753768858533");
        ufos.dispatch(andreu);
        ufos.seeFlota();
        System.out.println(ufos.getUfoOf(andreu.number()));
        System.out.println(ufos.cardNumbers());
        assertTrue(ufos.containsCard(andreu.number()));
    }
}

