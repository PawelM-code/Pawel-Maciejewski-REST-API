package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrelloFacade {
    private final TrelloService trelloService;
    private final TrelloMapper trelloMapper;
    private final TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoard() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToTrelloBoardList(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMapper.mapToTrelloBoardDtoList(filteredBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createdTrelloCard(trelloMapper.mapToTrelloCardDto(trelloCard));
    }
}
