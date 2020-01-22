package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {
    public List<TrelloList> mapToTrelloList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(trelloListDto1 -> new TrelloList(
                        trelloListDto1.getId(),
                        trelloListDto1.getName(),
                        trelloListDto1.isClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloListDto> mapToTrelloListDto(final List<TrelloList> trelloLists){
        return trelloLists.stream()
                .map(trelloList -> new TrelloListDto(
                        trelloList.getId(),
                        trelloList.getName(),
                        trelloList.isClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloBoard> mapToTrelloBoardList(final List<TrelloBoardDto> trelloBoardDtoList){
        return trelloBoardDtoList.stream()
                .map(trelloBoardDto -> new TrelloBoard(
                        trelloBoardDto.getId(),
                        trelloBoardDto.getName(),
                        mapToTrelloList(trelloBoardDto.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloBoardDto> mapToTrelloBoardDtoList(final List<TrelloBoard> trelloBoards){
        return trelloBoards.stream()
                .map(trelloBoard -> new TrelloBoardDto(
                        trelloBoard.getName(),
                        trelloBoard.getId(),
                        mapToTrelloListDto(trelloBoard.getLists())))
                .collect(Collectors.toList());
    }

    public TrelloCard mapToTrelloCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(
                trelloCardDto.getName(),
                trelloCardDto.getDescription(),
                trelloCardDto.getPos(),
                trelloCardDto.getListId());
    }

    public TrelloCardDto mapToTrelloCardDto(final TrelloCard trelloCard) {
        return  new TrelloCardDto(
                trelloCard.getName(),
                trelloCard.getDescription(),
                trelloCard.getPos(),
                trelloCard.getListId());
    }
}
