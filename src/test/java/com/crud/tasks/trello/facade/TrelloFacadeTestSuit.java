package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuit {
    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList(){
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test_list",false));

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("test","1", trelloListDtos));

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1","test_list",false));

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("1","test",trelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);
        when(trelloMapper.mapToTrelloBoardList(trelloBoardDtoList)).thenReturn(trelloBoardList);
        lenient().when(trelloMapper.mapToTrelloBoardDtoList(trelloBoardList)).thenReturn(trelloBoardDtoList);
        when(trelloValidator.validateTrelloBoards(trelloBoardList)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoard();

        //Tehn
        assertNotNull(trelloBoardDtos);
        assertEquals(0,trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards(){
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","my_list",false));

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("my_task","1", trelloListDtos));

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1","my_list",false));

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("1","my_task",trelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);
        when(trelloMapper.mapToTrelloBoardList(trelloBoardDtoList)).thenReturn(trelloBoardList);
        lenient().when(trelloMapper.mapToTrelloBoardDtoList(anyList())).thenReturn(trelloBoardDtoList);
        when(trelloValidator.validateTrelloBoards(trelloBoardList)).thenReturn(trelloBoardList);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoard();

        //Tehn
        assertNotNull(trelloBoardDtos);
        assertEquals(1,trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("my_task", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("my_list", trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
                    });
        });
    }
}
