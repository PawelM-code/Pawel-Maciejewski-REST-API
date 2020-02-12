package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TrelloConfig {
    @Value(("${trello.api.endpoint.prod}"))
//    @Value("https://api.trello.com/1")
    private String trelloApiEndpoint;

//    @Value("${trello.app.key}")
    @Value(("${trello.app.key}"))
    private String trelloAppKey;

//    @Value("${trello.app.token}")
    @Value(("${trello.app.token}"))
    private String trelloToken;

//    @Value("${trello.app.username}")
    @Value(("${trello.app.username}"))
    private String trelloUsername;
}
