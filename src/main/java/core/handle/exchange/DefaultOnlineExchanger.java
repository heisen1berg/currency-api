package core.handle.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class DefaultOnlineExchanger implements Exchanger {

    private final RestTemplate restTemplate;

    @Autowired
    public DefaultOnlineExchanger(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Double exchange(Float value, String currencyFrom, String currencyTo) {
        if (currencyFrom.equals(currencyTo)) return (double) value;
        if (value <= 0) return null;
        final String url = "https://api.ratesapi.io/api/latest?base=" + currencyFrom + "&symbols=" + currencyTo;
        try {
            final HashMap<String, Object> serverResponse = restTemplate.getForObject(url, HashMap.class);
            final Float multiplier = Float.parseFloat(((Map) serverResponse.get("rates")).get(currencyTo).toString());
            return (double) (value * multiplier);
        } catch (Exception e) {
            return null;
        }
    }
}
