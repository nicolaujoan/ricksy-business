package observers;

import payments.CreditCard;

public class CrystalExpender {
    private int stock;
    private final double CRYSTAL_PRICE;

    public CrystalExpender(int stock, double itemCost) {
        this.stock = stock;
        this.CRYSTAL_PRICE = itemCost;
    }

    public void dispatch(CreditCard creditCard) {
        if (stockAvailable()) {
            if (creditCard.pay(CRYSTAL_PRICE)) {
                stock--;
            }
        }
    }

    public int stock() {
        return this.stock;
    }

    private boolean stockAvailable() {
        return stock() > 0;
    }

    @Override
    public String toString() {
        return "stock: " + stock + "\ncost: " + CRYSTAL_PRICE + "\n";
    }
}
