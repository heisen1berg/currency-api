package core.controller;


import core.data.model.ExchangeRequest;
import core.data.model.ExchangeResponse;
import core.handle.exchange.ExchangeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeHandler exchangeHandler;

    @Autowired
    public ExchangeController(ExchangeHandler exchangeHandler) {
        this.exchangeHandler = exchangeHandler;
    }

    @GetMapping
    public @ResponseBody
    ExchangeResponse exchange(@RequestBody ExchangeRequest exchangeRequest) {
        return exchangeHandler.buildExchangeResponse(exchangeRequest);
    }
}
