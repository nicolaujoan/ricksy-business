package payments;

public class CreditCard {
    private final String owner;
    private final String number;
    private Double credit = 3000d;
    private final String SYMBOL = "EZI";

    public CreditCard(String owner, String number) {
        this.owner = owner;
        this.number = number;
    }

    public Boolean pay(double amount) {
        boolean isPayed = false;
        if (credit >= amount) {
            credit -= amount;
            isPayed = true;
        }
        return isPayed;
    }

    public String number() {
        return this.number;
    }

    public Double credit() {
        return this.credit;
    }

    public String owner() {
        return this.owner;
    }

    public boolean isOwner(String owner) {
        return owner == owner();
    }

    @Override
    public String toString() {
        return "owner:" + owner + "\n" +
                "number:" + number + "\n" +
                "credit:" + credit + SYMBOL + "\n";
    }
}
