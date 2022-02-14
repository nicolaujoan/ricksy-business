package observers;

import payments.CreditCard;

import java.util.HashSet;
import java.util.Set;

public class RickMenus implements GuestDispatcher{
    private final double PRICE = 10;
    private int stock = 100;
    Set<String> clientes = new HashSet<String>();

    public int getStock() {
        return stock;
    }

    public String stock() {
        return "stock: " + stock;
    }

    public void reduceStock() {
        stock --;
    }

    private Set<String> getClients() {
        return clientes;
    }

    public String clients() {
        return "[" + String.join(", ", getClients()) + "]";
    }

    private boolean stockAvailable() {
        return getStock() > 0;
    }

    @Override
    public void dispatch(CreditCard creditCard) {
        if (stockAvailable() && creditCard.pay(PRICE)) {
            reduceStock();
            clientes.add(creditCard.owner());
        }
    }

    @Override
    public String toString() {
        return "RickMenus{" +
                "stock=" + stock +
                ", clientes=" + clientes +
                '}';
    }
}
