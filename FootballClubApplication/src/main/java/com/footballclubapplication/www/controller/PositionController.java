package com.footballclubapplication.www.controller;

import com.footballclub.core.dto.PositionDTO;
import com.footballclub.core.dto.mapper.PositionMapper;
import com.footballclub.core.entity.Player;
import com.footballclub.core.entity.Position;
import com.footballclub.core.exception.PositionNotFoundException;
import com.footballclubapplication.www.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: add @GetMapping("/{id}/players")

@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;
    private final Logger logger = LoggerFactory.getLogger(PositionController.class);

    @GetMapping
    public List<Position> findAll() {
        logger.info("Finding all positions");
        return positionService.findAll();
    }

    @GetMapping("/{id}")
    public Position findById(@PathVariable("id") long id) {
        logger.info("Finding position by id: {}", id);
        return positionService.findById(id)
                .orElseThrow(() -> {
                    logger.error("Position with id {} not found", id);
                    return new PositionNotFoundException("Position not found");
                });
    }

    @GetMapping("/{id}/players")
    public List<Player> getPlayersByPositionId(@PathVariable("id") long id){
        logger.info("Finding players by position id: {}", id);
        return positionService.getPlayersByPositionId(id);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody PositionDTO positionDTO) {
        positionService.save(PositionMapper.INSTANCE.toPosition(positionDTO));
        return ResponseEntity
                .ok("The position was been saved");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody PositionDTO positionDTO) {
        positionService.update(PositionMapper.INSTANCE.toPosition(positionDTO));
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
