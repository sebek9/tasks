package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.TrelloService;

import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        trelloBoards.stream()
                .filter(dto -> dto.getName() != null)
                .filter(dto -> dto.getId() != null)
                .filter(dto -> dto.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> {
                    System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());

                    System.out.println("Board contains lists: ");

                    trelloBoardDto.getLists()
                            .forEach(trelloList ->
                                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
                });
return trelloClient.getTrelloBoards().get()(TaskNotFoundException::new));
    }



    /*
    @Autowired
    private TrelloService trelloService;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {

        return trelloService.fetchTrelloBoards();
    }
*/
/*
    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createdTrelloCard(trelloCardDto);

    }
    */
}