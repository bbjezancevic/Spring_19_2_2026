package hr.java.spring.boot.Spring_2_3_2026.repository;

import hr.java.spring.boot.Spring_2_3_2026.domain.Hardware;
import hr.java.spring.boot.Spring_2_3_2026.domain.Type;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
@AllArgsConstructor
public class JdbcHardwareRepository implements HardwareRepository{

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Hardware> getAllHardware() {
        return jdbcTemplate.query("SELECT * FROM HARDWARE", new HardwareMapper());
    }

    @Override
    public List<Hardware> getHardwareByCode(String hardwareCode) {
        return jdbcTemplate.query("SELECT * FROM HARDWARE WHERE CODE = ?", new HardwareMapper(), hardwareCode);
    }

    @Override
    public Integer saveNewHardware(Hardware hardware) {
        final String SQL = "SELECT ID FROM FINAL TABLE (INSERT INTO HARDWARE (name, code, price, typeId, stock) VALUES (?, ?, ?, ?, ?)) HARDWARE";
        Integer generatedId = jdbcTemplate.queryForObject(SQL, Integer.class, hardware.getName(), hardware.getCode(),
                hardware.getPrice(), hardware.getType().getId(), hardware.getStock());
        hardware.setId(generatedId);
        return generatedId;
    }

    @Override
    public Optional<Hardware> updateHardware(Hardware hardware, Integer id) {
        if(hardwareByIdExists(id)) {
            final String SQL = "UPDATE HARDWARE SET name = ?, code = ?, price = ?, typeId = ?, stock = ? WHERE ID = ?";
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setString(1, hardware.getName());
                ps.setString(2, hardware.getCode());
                ps.setBigDecimal(3, hardware.getPrice());
                ps.setInt(4, hardware.getType().getId());
                ps.setInt(5, hardware.getStock());
                ps.setInt(6, id);
                return ps;
            });
            hardware.setId(id);
            return Optional.of(hardware);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT (*) FROM HARDWARE WHERE ID = ?", Integer.class, id);
        return count > 0;
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        if (hardwareByIdExists(id)) {
            jdbcTemplate.update("DELETE FROM HARDWARE WHERE ID = ?", id);
            return true;
        }
        else {
            return false;
        }
    }

    private static class HardwareMapper implements RowMapper<Hardware> {
        public Hardware mapRow(ResultSet rs, int i) throws SQLException {
            Hardware newHardware = new Hardware();
            newHardware.setId(rs.getInt("ID"));
            newHardware.setName(rs.getString("NAME"));
            newHardware.setCode(rs.getString("CODE"));
            newHardware.setPrice(rs.getBigDecimal("PRICE"));

            Integer typeId = rs.getInt("TYPEID");

            if(Type.CPU.getId().equals(typeId)) {
                newHardware.setType(Type.CPU);
            }
            else if (Type.GPU.getId().equals(typeId)) {
                newHardware.setType(Type.GPU);
            }
            else if (Type.MBO.getId().equals(typeId)) {
                newHardware.setType(Type.MBO);
            }
            else if (Type.RAM.getId().equals(typeId)) {
                newHardware.setType(Type.RAM);
            }
            else if (Type.STORAGE.getId().equals(typeId)) {
                newHardware.setType(Type.STORAGE);
            }
            else {
                newHardware.setType(Type.OTHER);
            }

            newHardware.setStock(rs.getInt("STOCK"));

            return newHardware;
        }
    }
}
