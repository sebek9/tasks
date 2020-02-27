package com.crud.tasks;


import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestMapper {


    @InjectMocks
    private TrelloMapper trelloMapper;


    @Test
    public void testMapToBoards(){
        //Given
        List<TrelloBoardDto>lists=new ArrayList<>();
        lists.add(new TrelloBoardDto("1", "testMapToBoards", new ArrayList<>()));
        //When
        List<TrelloBoard> theList=trelloMapper.mapToBoards(lists);
        ///Then
        assertEquals(1,theList.size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard>list=new ArrayList<>();
        list.add(new TrelloBoard("1","testMapToBoardsDto",new ArrayList<>()));
        //When
        List<TrelloBoardDto>theList=trelloMapper.mapToBoardsDto(list);
        //Then
        assertEquals(1,theList.size());
    }
    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto>list=new ArrayList<>();
        list.add(new TrelloListDto("1","testMapToList",true));
        //When
        List<TrelloList>theList=trelloMapper.mapToList(list);
        //Then
        assertEquals(1,theList.size());

    }
    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList>trelloList=new ArrayList<>();
        trelloList.add(new TrelloList("1","test MapToListDto",true));
        //When
        List<TrelloListDto>theList=trelloMapper.mapToListDto(trelloList);
        //Then
        assertEquals(1,theList.size());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard=new TrelloCard("Test","Test","Test","1");
        //When
        TrelloCardDto trelloCardDto=trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCardDto.getName(),"Test");
        assertEquals(trelloCardDto.getDescription(),"Test");
        assertEquals(trelloCardDto.getListId(),"1");
        assertEquals(trelloCardDto.getPos(),"Test");
    }
    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto=new TrelloCardDto("testMapToCard","testMapToCard","testMapToCard","1");
        //When
        TrelloCard trelloCard=trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardDto.getName(),"testMapToCard");
        assertEquals(trelloCardDto.getDescription(),"testMapToCard");
        assertEquals(trelloCardDto.getListId(),"1");
        assertEquals(trelloCardDto.getPos(),"testMapToCard");
    }


}
