package hr.java.spring.boot.Example_23_2_2026.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HardwareDTO {
    @NotBlank(message = "Hardware name cannot be blank.")
    private String hardwareName;
    @NotBlank(message = "Hardware code cannot be blank.")
    private String hardwareCode;
    @NotNull(message = "Hardware price cannot be null.")
    @PositiveOrZero(message = "Hardware price must be positive or zero.")
    private BigDecimal hardwarePrice;
    @NotBlank(message = "Hardware type name cannot be blank.")
    private String typeName;
    @NotNull(message = "Hardware stock cannot be null.")
    @PositiveOrZero(message = "Hardware stock has to be positive or zero.")
    private Integer hardwareStock;
}
