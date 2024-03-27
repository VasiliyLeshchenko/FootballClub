package com.footballclubapplication.www.controller;

import com.footballclubapplication.www.entity.Player;
import com.footballclubapplication.www.entity.Position;
import com.footballclubapplication.www.exeption.PositionNotFoundException;
import com.footballclubapplication.www.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: add @GetMapping("/{id}/players")

@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> findAll() {
        return positionService.findAll();
    }

    @GetMapping("/{id}")
    public Position findById(@PathVariable("id") long id) {
        return positionService.findById(id)
                .orElseThrow(() -> new PositionNotFoundException("Position not found"));
    }

    @GetMapping("/{id}/players")
    public List<Player> getPlayersByPositionId(@PathVariable("id") long id){
        return positionService.getPlayersByPositionId(id);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Position position) {
        positionService.save(position);
        return ResponseEntity
                .ok("The position was been saved");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Position position) {
        positionService.update(position);
        return ResponseEntity
                .ok("The position has been successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        positionService.delete(id);
        return ResponseEntity
                .ok("The position has been successfully deleted");
    }
}
