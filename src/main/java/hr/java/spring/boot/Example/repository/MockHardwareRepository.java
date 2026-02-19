package hr.java.spring.boot.Example.repository;

import hr.java.spring.boot.Example.domain.Hardware;
import hr.java.spring.boot.Example.domain.Type;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MockHardwareRepository implements HardwareRepository{
    private static List<Hardware> hardwareList;

    static {
        hardwareList = new ArrayList<>();

        Hardware hardware1 = new Hardware(1, "RX6800", "232", new BigDecimal(500), Type.GPU, 3);
        Hardware hardware2 = new Hardware(2, "Ryzen 7 9800X3D", "523", new BigDecimal(550), Type.CPU, 2);
        Hardware hardware3 = new Hardware(3, "Razer Seiren v3 mini", "12", new BigDecimal(110), Type.OTHER, 12);
        Hardware hardware4 = new Hardware(4, "MSI X870 Tomahawk", "621", new BigDecimal(300), Type.MBO, 2);

        hardwareList.add(hardware1);
        hardwareList.add(hardware2);
        hardwareList.add(hardware3);
        hardwareList.add(hardware4);
    }

    @Override
    public List<Hardware> getAllHardware() {
        return hardwareList;
    }

    @Override
    public List<Hardware> getHardwareByCode(String hardwareCode) {
        return hardwareList.stream()
                .filter(h -> h.getCode().equals(hardwareCode)).collect(Collectors.toList());
    }
}
