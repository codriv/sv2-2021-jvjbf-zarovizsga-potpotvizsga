package settlers;

public class Settler {

    private long id;
    private String nameOfSettler;
    private int amountOfTobacco;
    private int expectedIncome;
    private final int PRICE_PER_TON = 500;

    public Settler(String nameOfSettler, int amountOfTobacco) {
        this.nameOfSettler = nameOfSettler;
        this.amountOfTobacco = amountOfTobacco;
    }

    public long getId() {
        return id;
    }

    public String getNameOfSettler() {
        return nameOfSettler;
    }

    public int getAmountOfTobacco() {
        return amountOfTobacco;
    }

    public int getExpectedIncome() {
        return amountOfTobacco * PRICE_PER_TON;
    }
}
