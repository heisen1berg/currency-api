package core.handle.exchange;

import core.data.model.ExchangeRequest;
import core.data.model.ExchangeResponse;

public interface ExchangeHandler {
    ExchangeResponse buildExchangeResponse(ExchangeRequest exchangeRequest);
}
