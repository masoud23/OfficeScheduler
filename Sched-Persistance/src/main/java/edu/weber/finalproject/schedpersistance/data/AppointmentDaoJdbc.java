/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.AppointmentDAO;
import edu.weber.finalproject.schedschema.Appointment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 *
 * @author Mike
 */
public class AppointmentDaoJdbc implements AppointmentDAO{

    private SimpleJdbcTemplate jdbc;
    
    //I need to check the db for actual column name of date_time
    private static final String ALL_FIELDS = "id, slot_id, patient_exam_id, created_dateTime";
    private static final String ALL_MEMBERS = ":id,:resultId,:patientId,:examId,:created";
    private static final String UPDATE_STRING = "id=:id, result_id=:resultId, patient_id=:patientId, exam_id=:examId, created_dateTime=:created";
    
    private static final String GET_PATIENT_APPOINTMENTS = "SELECT " + ALL_FIELDS + " FROM appointment WHERE patient_id = ?"; 
    private static final String DELETE_APPOINTMENT= "DELETE FROM appointment WHERE id =?";
    
    public AppointmentDaoJdbc(SimpleJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    private String getAllAppointmentsSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, slot_id, created_dateTime ");
        sql.append("FROM Appointments");
        return sql.toString();
    }
    
    private String getAppointmentByIdSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getAllAppointmentsSQL());
        sql.append(" WHERE id = ?");
        return sql.toString();
    }
    
    private String getAppointmentBySlotIdSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getAllAppointmentsSQL());
        sql.append(" WHERE slot_id = ?");
        return sql.toString();
    }
    
    private String insertAppointmentSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Appointments (");
        sql.append("slot_id, created_dateTime) ");
        sql.append("VALUES (:slotId, :createdDate)");
        return sql.toString();
    }
    
    private String updateAppointmentSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Appointments ");
        sql.append("SET slotId = :slotId, created_dateTime = :createdDate ");
        sql.append("WHERE id = :id");
        return sql.toString();
    }
    //*********************************************************************************************************************
    //      AppointmentDAO implementation methods
    //*********************************************************************************************************************
    
    @Override
    public List<Appointment> getAllAppointments() {
        return jdbc.query(getAllAppointmentsSQL(), mapper);
    }
    
    /**
     * @deprecated  Will be re-worked in either manager or with joins
     * @param patientId
     * @return 
     */
    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return jdbc.query(GET_PATIENT_APPOINTMENTS, mapper, patientId);
    }
     
    @Override
    public Appointment getAppointmentById(int id) {
        return (Appointment) jdbc.queryForObject(getAppointmentByIdSQL(), mapper, id);
    }

    @Override
    public Appointment getAppointmentBySlotId(int slotId) {
        return (Appointment) jdbc.queryForObject(getAppointmentBySlotIdSQL(), mapper, slotId);
    }

    @Override
    public int addAppointment(Appointment appointment) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(appointment);
        if (appointment.getId()==0){
            return jdbc.update(insertAppointmentSQL(), param);
        } else return 0;
    }

    @Override
    public int updateAppointment(Appointment appointment) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(appointment);
        if (appointment.getId()==0){
            return jdbc.update(updateAppointmentSQL(), param);
        } else return 0;
    }

    @Override
    public int deleteAppointment(int id) {
        return jdbc.update(DELETE_APPOINTMENT, id);
    }
    
    private RowMapper<Appointment> mapper = new ParameterizedRowMapper<Appointment>(){

        @Override
        public Appointment mapRow(ResultSet rs, int i) throws SQLException {
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt("id"));
            appointment.setSlotId(rs.getInt("slot_id"));
            java.sql.Timestamp stamp = rs.getTimestamp("created_dateTime");
            appointment.setCreatedDate(stamp);
            
            return appointment;
        }
    
   };
}
    
