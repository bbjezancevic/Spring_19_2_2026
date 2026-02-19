package hr.java.spring.boot.Example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    CPU,
    GPU,
    MBO,
    RAM,
    STORAGE,
    OTHER;
}
