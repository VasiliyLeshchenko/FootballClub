package com.footballclubapplication.www.controller;

import com.footballclub.core.dto.PositionDTO;
import com.footballclub.core.dto.mapper.PositionMapper;
import com.footballclub.core.entity.Player;
import com.footballclub.core.entity.Position;
import com.footballclub.core.exception.PositionNotFoundException;
import com.footballclubapplication.www.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    @GetMapping
    public List<Position> findAll() {
        log.info("Finding all positions");
        return positionService.findAll();
    }

    @GetMapping("/{id}")
    public Position findById(@PathVariable("id") long id) {
        log.info("Finding position by id: {}", id);
        return positionService.findById(id)
                .orElseThrow(() -> {
                    log.error("Position with id {} not found", id);
                    return new PositionNotFoundException("Position not found");
                });
    }

    @GetMapping("/{id}/players")
    public List<Player> getPlayersByPositionId(@PathVariable("id") long id){
        log.info("Finding players by position id: {}", id);
        return positionService.getPlayersByPositionId(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping
    public ResponseEntity<String> save(@RequestBody PositionDTO positionDTO) {
        log.info("Saving position : {}", positionDTO);
        positionService.save(PositionMapper.INSTANCE.toPosition(positionDTO));
        return ResponseEntity
                .ok("The position was been saved");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody PositionDTO positionDTO) {
        log.info("Updating position : {}", positionDTO);
        positionService.update(PositionMapper.INSTANCE.toPosition(positionDTO));
        return ResponseEntity
                .ok("The position has been successfully updated");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        log.info("Deleting position by id: {}", id);
        positionService.delete(id);
        return ResponseEntity
                .ok("The position has been successfully deleted");
    }
}
