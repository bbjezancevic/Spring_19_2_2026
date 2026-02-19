package hr.java.spring.boot.Example.dto;

import hr.java.spring.boot.Example.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HardwareDTO {
    private String hardwareName;
    private String hardwareCode;
    private BigDecimal hardwarePrice;
    private String typeName;
    private Integer hardwareStock;
}
