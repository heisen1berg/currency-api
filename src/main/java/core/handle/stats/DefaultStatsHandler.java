package core.handle.stats;

import core.data.entity.ExchangeEntity;
import core.handle.exchange.Exchanger;
import core.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

@Component
public class DefaultStatsHandler implements StatsHandler {

    private final ExchangeRepository exchangeRepository;
    private final Exchanger exchanger;

    @Autowired
    public DefaultStatsHandler(ExchangeRepository exchangeRepository, Exchanger exchanger) {
        this.exchangeRepository = exchangeRepository;
        this.exchanger = exchanger;
    }

    @Override
    public LinkedHashMap<String, Long> getMostPopular() {
        final Iterable<ExchangeEntity> exchanges = exchangeRepository.findAll();
        final LinkedHashMap<String, Long> sumMap = new LinkedHashMap<>();
        exchanges.forEach(exchangeEntity -> {
            final String key = exchangeEntity.getCurrencyFrom() + "->" + exchangeEntity.getCurrencyTo();
            Long newSum = sumMap.get(key);
            newSum = newSum == null ? 1 : newSum + 1;
            sumMap.put(key, newSum);
        });
        final LinkedHashMap<String, Long> sorted = new LinkedHashMap<>();
        sumMap.entrySet().stream().sorted(Map.Entry.comparingByValue(
                (v1, v2) -> {
                    return -v1.compareTo(v2);
                }
                )
        )
                .forEach(entry -> sorted.put(entry.getKey(), entry.getValue()));
        return sorted;
    }

    @Override
    public LinkedList<String> getUsersTotalValueMoreThan(Double value) {
        final Iterable<ExchangeEntity> exchanges = exchangeRepository.findAll();
        final LinkedHashMap<String, Double> sumMap = new LinkedHashMap<>();
        exchanges.forEach(exchangeEntity -> {
            final String key = exchangeEntity.getUserId().toString();
            final Double convertedValue = exchanger.exchange(exchangeEntity.getValue(), exchangeEntity.getCurrencyFrom(), "USD");

            Double newSum = sumMap.get(key);
            newSum = newSum == null ? convertedValue : newSum + convertedValue;

            sumMap.put(key, newSum);
        });
        final LinkedList<String> ret = new LinkedList<>();
        sumMap.forEach((k, v) -> {
            if (v >= value) ret.add(k);
        });
        return ret;
    }

    @Override
    public LinkedList<String> getUsersSingleValueMoreThan(Double value) {
        final Iterable<ExchangeEntity> exchanges = exchangeRepository.findAll();
        final LinkedList<String> ret = new LinkedList<>();
        exchanges.forEach(exchangeEntity -> {
            final Double convertedValue = exchanger.exchange(exchangeEntity.getValue(), exchangeEntity.getCurrencyFrom(), "USD");
            if (convertedValue >= value) ret.add(exchangeEntity.getUserId().toString());
        });
        return ret;
    }

}
