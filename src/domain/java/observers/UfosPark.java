package observers;

import payments.CreditCard;

import java.util.*;

public class UfosPark implements GuestDispatcher {
    private final double UFO_FEE = 500;  // EZIs
    private Map<String, String> flota = new HashMap<String, String>();

    public void add(String ufoID) {
        flota.put(ufoID, "");
    }

    @Override
    public void dispatch(CreditCard creditCard) {
        if(!this.containsCard(creditCard.number())) {
                for (Map.Entry<String, String> entry : flota.entrySet()) {
                    if (entry.getValue().equals("") && creditCard.pay(UFO_FEE)) {
                        entry.setValue(creditCard.number());
                        break;
                    }
                }
        }
    }

    public String getUfoOf(String cardNumber) {
        if (this.containsCard(cardNumber)) {
            for (Map.Entry<String,String> entry : flota.entrySet()) {
                if (entry.getValue().equals(cardNumber)) {
                    return entry.getKey();

                }
            }
        }
        return "";
    }

    public boolean containsCard(String cardNumber){
        return flota.containsValue(cardNumber);
    }

    public Collection<String> cardNumbers() {
        return flota.values();
    }

    @Override
    public String toString() {
        return  "[" + String.join(", ", flota.keySet()) + "]";

    }

    // testing
    public boolean containsUfo(String ufoID) {
        return flota.containsKey(ufoID);
    }


    public void seeFlota() {
        System.out.println(flota);
    }
}
