package hr.java.spring.boot.Example_23_2_2026.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hardware {
    private Integer id;
    private String name;
    private String code;
    private BigDecimal price;
    private Type type;
    private Integer stock;
}
