package core.handle.exchange;

import core.io.CurrencyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OfflineExchanger implements Exchanger {

    private final CurrencyReader currencyReader;

    @Autowired
    public OfflineExchanger(CurrencyReader currencyReader) {
        this.currencyReader = currencyReader;
    }

    public Double exchange(Float value, String currencyFrom, String currencyTo) {
        if (currencyFrom.equals(currencyTo)) return (double) value;
        if (value <= 0) return null;
        final Float multiplier = currencyReader.getMultiplier(currencyFrom, currencyTo);
        if (multiplier == null) return null;
        return (double) (value * multiplier);
    }
}
