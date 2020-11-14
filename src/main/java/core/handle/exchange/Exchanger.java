package core.handle.exchange;

public interface Exchanger {
    Double exchange(Float value, String currencyFrom, String currencyTo);
}
