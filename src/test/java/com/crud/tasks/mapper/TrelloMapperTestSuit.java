package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuit {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToTrelloList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "ListOne", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "ListTwo", true);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToTrelloList(trelloListDtos);

        //Then
        assertEquals(2, trelloLists.size());
        assertEquals("ListOne", trelloLists.get(0).getName());
        assertEquals("ListTwo", trelloLists.get(1).getName());
        assertFalse(trelloLists.get(0).isClosed());
        assertTrue(trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToTrelloListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "ListOne", false);
        TrelloList trelloList2 = new TrelloList("2", "ListTwo", true);
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(trelloList1);
        trelloList.add(trelloList2);

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToTrelloListDto(trelloList);

        //Then
        assertEquals(2, trelloListsDto.size());
        assertEquals("ListOne", trelloListsDto.get(0).getName());
        assertEquals("ListTwo", trelloListsDto.get(1).getName());
        assertFalse(trelloListsDto.get(0).isClosed());
        assertTrue(trelloListsDto.get(1).isClosed());
    }


    @Test
    public void testMapToTrelloBoardList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "ListOne", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "ListTwo", true);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("BoardOne", "1", trelloListDtos);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto1);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToTrelloBoardList(trelloBoardsDto);

        //Then
        assertEquals(1, trelloBoards.size());
        assertEquals("BoardOne", trelloBoards.get(0).getName());
        assertEquals("ListOne", trelloBoards.get(0).getLists().get(0).getName());
    }

    @Test
    public void testMapToTrelloBoardDtoList() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "ListOne", false);
        TrelloList trelloList2 = new TrelloList("2", "ListTwo", true);
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(trelloList1);
        trelloList.add(trelloList2);
        TrelloBoard trelloBoard1 = new TrelloBoard("BoardOne", "1", trelloList);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToTrelloBoardDtoList(trelloBoards);

        //Then
        assertEquals(1, trelloBoardsDto.size());
        assertEquals("1", trelloBoardsDto.get(0).getName());
        assertEquals("ListOne", trelloBoardsDto.get(0).getLists().get(0).getName());
    }

    @Test
    public void testMapToTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("1", "2", "3", "4");

        //When
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);

        //Then
        Assertions.assertThat(trelloCard).isEqualToComparingFieldByField(trelloCardDto);
    }

    @Test
    public void testMapToTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("1", "2", "3", "4");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToTrelloCardDto(trelloCard);

        //Then
        Assertions.assertThat(trelloCardDto).isEqualToComparingFieldByField(trelloCard);
    }
}
