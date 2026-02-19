package hr.java.spring.boot.Example.service;

import hr.java.spring.boot.Example.domain.Hardware;
import hr.java.spring.boot.Example.repository.HardwareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HardwareServiceImpl implements HardwareService{
    private HardwareRepository hardwareRepository;

    @Override
    public List<Hardware> getAllHardware() {
        return hardwareRepository.getAllHardware().stream().toList();
    }

    @Override
    public List<Hardware> getHardwareByCode(String hardwareCode) {
        return hardwareRepository.getHardwareByCode(hardwareCode).stream().toList();
    }
}
