/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.SlotDAO;
import edu.weber.finalproject.schedschema.TimeSlot;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author Masoud
 */
@Component
public class SlotDaoJdbc implements SlotDAO {
    
    @Autowired
    private SimpleJdbcTemplate jdbc;
    private static final String DELETE_SLOT = "DELETE FROM slot WHERE id = ?";

    public SlotDaoJdbc() {}
    public SlotDaoJdbc(SimpleJdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public void setJdbc(SimpleJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    private String getSlotsSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s.id, s.calendar_id, s.start_dateTime, s.span_count, a.slot_id ");
        sql.append("FROM Slots s ");
        sql.append("LEFT JOIN Appointments a ");
        sql.append("ON s.id = a. slot_id");
        return sql.toString();
    }
    
    private String getSlotByIdSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getSlotsSQL());
        sql.append(" WHERE s.id = ?");
        return sql.toString();
    }
    
    private String getAvailableSlotsSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getSlotsSQL());
        sql.append(" WHERE a.slot_id IS NULL ");
        sql.append("OR a.slot_id = 0");
        return sql.toString();
    }
    
    private String getSlotsByCalendarIdSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getSlotsSQL());
        sql.append(" WHERE calendar_id = ?");
        return sql.toString();
    }
    
    private String insertSlotSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Slots (");
        sql.append("calendar_id, span_count, start_dateTime, available) ");
        sql.append("VALUES (");
        sql.append(":calendarId, :spanCount, :dateTime, :available)");
        return sql.toString();
    }
    
    private String updateSlotSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Slots ");
        sql.append("SET calendar_id = :calendarId, span_count = :spanCount, start_dateTime = :dateTime, available = :available ");
        sql.append("WHERE id = :id");
        return sql.toString();
    }
    @Override
    public TimeSlot getSlotById(int id) {
        return (TimeSlot) jdbc.queryForObject(getSlotByIdSQL(), mapper, id);
    }

    @Override
    public List<TimeSlot> getAllSlots() {
        return jdbc.query(getSlotsSQL(), mapper);
    }

    @Override
    public List<TimeSlot> getAllAvailableSlots() {
        return jdbc.query(getAvailableSlotsSQL(), mapper, true);
    }

    @Override
    public int addSlot(TimeSlot slot) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(slot);
        return jdbc.update(insertSlotSQL(), param);
    }

    @Override
    public int updateSlot(TimeSlot slot) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(slot);
        return jdbc.update(updateSlotSQL(), param);
    }

     @Override
    public List<TimeSlot> getSlotsByCalendarId(int calId) {
        return jdbc.query(getSlotsByCalendarIdSQL(), mapper, calId);
    }
     
    @Override
    public int deleteSlot(int id) {       
        return jdbc.update(DELETE_SLOT, id);
    }

        private RowMapper<TimeSlot> mapper = new ParameterizedRowMapper<TimeSlot>(){

        @Override
        public TimeSlot mapRow(ResultSet rs, int i) throws SQLException {
            TimeSlot slot = new TimeSlot();
            slot.setId(rs.getInt(1));
            slot.setCalendarId(rs.getInt(2));
            slot.setSpanCount(rs.getInt(4));
            slot.setDateTime(rs.getDate(3));
            Integer temp = rs.getInt(5);       //Must use Integer in case it is null
            slot.setAvailable(temp == null);
            return slot;
        }
   };    
}
