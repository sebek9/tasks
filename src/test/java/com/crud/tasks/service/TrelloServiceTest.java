package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void shouldfetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test List", false));

        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "Test_name", trelloLists));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDto);

        //When
        List<TrelloBoardDto> theList = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, theList.size());

    }

    @Test
    public void shouldcreatedTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_name", "test_description",
                "test_pos", "test_listId");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1", "test_name", "http://test.com", null);

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto newCard = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        assertEquals("test_name", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }
}
