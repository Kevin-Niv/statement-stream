package com.meraki.t24statementproducer.controller;

import com.meraki.t24statementproducer.entity.StatementModel;
import com.meraki.t24statementproducer.kafka.StatementProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statement/v1")
public class StatementController {

    private StatementProducer statementProducer;

    public StatementController(StatementProducer statementProducer) {
        this.statementProducer = statementProducer;
    }

    @PostMapping("/statement")
    public String sendStatement(@RequestBody StatementModel statementModel){

        statementProducer.sendStatement(statementModel);

        return "Statement Sent Successfully............";
    }
}
