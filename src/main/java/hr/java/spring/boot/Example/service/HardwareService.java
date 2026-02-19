package hr.java.spring.boot.Example.service;

import hr.java.spring.boot.Example.domain.Hardware;

import java.util.List;

public interface HardwareService {
    List<Hardware> getAllHardware();
    List<Hardware> getHardwareByCode(String hardwareCode);
}
