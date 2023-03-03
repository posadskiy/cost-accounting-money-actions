package com.posadskiy.costaccounting.moneyactions.web.endpoint;

import com.posadskiy.costaccounting.moneyactions.api.request.MoneyActionRequest;
import com.posadskiy.costaccounting.moneyactions.core.controller.EventController;
import com.posadskiy.costaccounting.moneyactions.core.db.model.MoneyAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v0/events")
public class EventEndpoint {
    private final EventController eventController;

    @Autowired
    public EventEndpoint(EventController eventController) {
        this.eventController = eventController;
    }

    @PostMapping("monthly")
    public Map<Integer, List<MoneyAction>> lastMonthEvents(@RequestBody final MoneyActionRequest moneyActionRequest) {
        return eventController.monthEvents(moneyActionRequest.getUserId(), moneyActionRequest.getYear(), moneyActionRequest.getMonth());
    }
}
