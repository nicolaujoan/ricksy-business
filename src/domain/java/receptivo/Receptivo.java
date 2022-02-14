package receptivo;

import observers.GuestDispatcher;
import payments.CreditCard;

import java.util.HashSet;
import java.util.Set;

public class Receptivo {
    private final Set<GuestDispatcher> observers = new HashSet<GuestDispatcher>();

    public void registra(GuestDispatcher observer) {
        observers.add(observer);
    }

    public void dispatch(CreditCard creditCard) {
        for (GuestDispatcher observer: observers) {
            observer.dispatch(creditCard);
        }
    }

    // testing purpose
    public boolean contains(GuestDispatcher observer) {
        return observers.contains(observer);
    }
}
