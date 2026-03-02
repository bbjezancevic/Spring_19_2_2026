package hr.java.spring.boot.Spring_2_3_2026.service;

import hr.java.spring.boot.Spring_2_3_2026.domain.Hardware;
import hr.java.spring.boot.Spring_2_3_2026.domain.Type;
import hr.java.spring.boot.Spring_2_3_2026.dto.HardwareDTO;
import hr.java.spring.boot.Spring_2_3_2026.repository.HardwareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Integer saveNewHardware(HardwareDTO hardware) {
        return hardwareRepository.saveNewHardware(convertHardwareDtoToHardware(hardware));
    }

    @Override
    public Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id) {
        Optional<Hardware> updatedHardwareOptional = hardwareRepository.updateHardware(convertHardwareDtoToHardware(hardwareDTO), id);

        if (updatedHardwareOptional.isPresent()) {
            return Optional.of(convertHardwareToHardwareDTO(updatedHardwareOptional.get()));
        }

        return Optional.empty();
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        return hardwareRepository.hardwareByIdExists(id);
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        return hardwareRepository.deleteHardwareById(id);
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
