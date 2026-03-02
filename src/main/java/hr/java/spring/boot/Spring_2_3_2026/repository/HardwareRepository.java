package hr.java.spring.boot.Spring_2_3_2026.repository;

import hr.java.spring.boot.Spring_2_3_2026.domain.Hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {
    List<Hardware> getAllHardware();
    List<Hardware> getHardwareByCode(String hardwareCode);
    Integer saveNewHardware(Hardware hardware);
    Optional<Hardware> updateHardware(Hardware hardware, Integer id);
    boolean hardwareByIdExists(Integer id);
    boolean deleteHardwareById(Integer id);
}
