package core.controller;


import core.handle.stats.StatsHandler;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsHandler statsHandler;

    public StatsController(StatsHandler statsHandler) {
        this.statsHandler = statsHandler;
    }

    @GetMapping("/popular")
    @ResponseBody
    public LinkedHashMap<String, Long> getMostPopular() {
        return statsHandler.getMostPopular();
    }

    @GetMapping("/users_total_value_more_than{value}")
    @ResponseBody
    public List<String> getUsersTotalValueMoreThan(@RequestParam("value") Double value) {
        return statsHandler.getUsersTotalValueMoreThan(value);
    }

    @GetMapping("/users_single_value_more_than{value}")
    @ResponseBody
    public List<String> getUsersSingleValueMoreThan(@RequestParam("value") Double value) {
        return statsHandler.getUsersTotalValueMoreThan(value);
    }
}
