package observers;

import payments.CreditCard;

public interface GuestDispatcher {
    void dispatch(CreditCard creditCard);
}
