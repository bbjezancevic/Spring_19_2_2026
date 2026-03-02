package hr.java.spring.boot.Spring_2_3_2026.service;

import hr.java.spring.boot.Spring_2_3_2026.dto.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {
    List<HardwareDTO> getAllHardware();
    List<HardwareDTO> getHardwareByCode(String hardwareCode);
    Integer saveNewHardware(HardwareDTO hardware);
    Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id);
    boolean hardwareByIdExists(Integer id);
    boolean deleteHardwareById(Integer id);
}
