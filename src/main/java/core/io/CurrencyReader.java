package core.io;

public interface CurrencyReader {
    Float getMultiplier(String currencyFrom, String currencyTo);
}
