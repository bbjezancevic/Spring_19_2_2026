package hr.java.spring.boot.Example.service;

import hr.java.spring.boot.Example.domain.Hardware;
import hr.java.spring.boot.Example.domain.Type;
import hr.java.spring.boot.Example.dto.HardwareDTO;
import hr.java.spring.boot.Example.repository.HardwareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HardwareServiceImpl implements HardwareService{
    private HardwareRepository hardwareRepository;

    @Override
    public List<HardwareDTO> getAllHardware() {
        return hardwareRepository.getAllHardware().stream().map(this::convertHardwareToHardwareDTO).toList();
    }

    @Override
    public List<HardwareDTO> getHardwareByCode(String hardwareCode) {
        return hardwareRepository.getHardwareByCode(hardwareCode).stream().map(this::convertHardwareToHardwareDTO).toList();
    }

    private HardwareDTO convertHardwareToHardwareDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getName(), hardware.getCode(), hardware.getPrice(),
                hardware.getType().getName(), hardware.getStock());
    }

    private Hardware convertHardwareDtoToHardware (HardwareDTO hardwareDTO) {
        Integer latestID =
                hardwareRepository.getAllHardware().stream()
                        .max((a1, a2) -> a1.getId().compareTo(a2.getId()))
                        .get().getId();

        return new Hardware(latestID + 1, hardwareDTO.getHardwareName(), hardwareDTO.getHardwareCode(),
                hardwareDTO.getHardwarePrice(), Type.valueOf(hardwareDTO.getTypeName()), hardwareDTO.getHardwareStock());
    }
}
