package core.handle.exchange;

import core.data.entity.ExchangeEntity;
import core.data.model.ExchangeRequest;
import core.data.model.ExchangeResponse;
import core.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaulExchangeHandler implements ExchangeHandler {
    private final Exchanger exchanger;
    private final ExchangeRepository exchangeRepository;

    @Autowired
    public DefaulExchangeHandler(Exchanger exchanger, ExchangeRepository exchangeRepository) {
        this.exchanger = exchanger;
        this.exchangeRepository = exchangeRepository;
    }


    public ExchangeResponse buildExchangeResponse(ExchangeRequest exchangeRequest) {
        final ExchangeEntity exchangeEntity = new ExchangeEntity(exchangeRequest.getUserId(),
                exchangeRequest.getValue(),
                exchangeRequest.getCurrencyFrom(),
                exchangeRequest.getCurrencyTo());
        final Double exchangeResult = exchanger.exchange(exchangeRequest.getValue(),
                exchangeRequest.getCurrencyFrom(), exchangeRequest.getCurrencyTo());
        if (exchangeResult == null) return null;

        exchangeRepository.save(exchangeEntity);
        return new ExchangeResponse(exchangeEntity.getId(), exchangeResult);
    }

}
