package hr.java.spring.boot.Spring_2_3_2026.controller;

import hr.java.spring.boot.Spring_2_3_2026.dto.HardwareDTO;
import hr.java.spring.boot.Spring_2_3_2026.service.HardwareService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hardware")
@AllArgsConstructor
public class HardwareController {
    private HardwareService hardwareService;

    @GetMapping
    public ResponseEntity<List<HardwareDTO>> getAllHardware(){
        return ResponseEntity.ok(hardwareService.getAllHardware().stream().toList());
    }

    @GetMapping("/{hardwareCode}")
    public ResponseEntity<List<HardwareDTO>> getHardwareByCode(@PathVariable String hardwareCode) {
        return ResponseEntity.ok(hardwareService.getHardwareByCode(hardwareCode).stream().toList());
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveNewHardware(@Valid @RequestBody HardwareDTO hardwareDTO) {
        hardwareService.saveNewHardware(hardwareDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/component/{hardwareId}")
    public ResponseEntity<HardwareDTO> updateHardware(@Valid @RequestBody HardwareDTO hardwareDTO, @PathVariable Integer hardwareId) {
        if (hardwareService.hardwareByIdExists(hardwareId)) {
            hardwareService.updateHardware(hardwareDTO, hardwareId);
            return ResponseEntity.ok(hardwareDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/component/{hardwareId}")
    public ResponseEntity<HardwareDTO> deleteHardware(@PathVariable Integer hardwareId){
        if (hardwareService.hardwareByIdExists(hardwareId)) {
            boolean result = hardwareService.deleteHardwareById(hardwareId);
            if (result) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
