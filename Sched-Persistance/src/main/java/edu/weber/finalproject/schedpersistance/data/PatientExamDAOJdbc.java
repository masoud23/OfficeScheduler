/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.PatientExamDAO;
import edu.weber.finalproject.schedschema.PatientExam;
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
public class PatientExamDAOJdbc implements PatientExamDAO {
    
    private SimpleJdbcTemplate jdbc;
    
    public PatientExamDAOJdbc(SimpleJdbcTemplate jdbc) {    
        this.jdbc = jdbc;
    }
    
    private String getPatientExamsString() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, appointment_id, doctor_id, exam_id, patient_id, service_dateTime )");
        sql.append("FROM PatientExams");
        return sql.toString();
    } 
    
    private String getPatientExamByIDString() {
        StringBuilder sql = new StringBuilder();
        sql.append(getPatientExamsString());
        sql.append(" WHERE id = ?");
        return sql.toString();
    }
    
    private String getPastPatientExamsSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getPatientExamsString());
        sql.append(" WHERE service_dateTime < NOW()");
        sql.append("AND patient_id = ?");
        return sql.toString();
    }
    
    private String getFuturePatientExamsSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getPatientExamsString());
        sql.append(" WHERE service_dateTime > NOW() ");
        sql.append("AND patient_id = ?");
        return sql.toString();
    }
    
    private String insertPatientExamSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PatientExams (");
        sql.append("appointment_id, doctor_id, exam_id, patient_id, service_dateTime) ");
        sql.append("VALUES (");
        sql.append(":appointmentId, :doctorId, :examId, :patientId, :serviceDate)");
        return sql.toString();
    }
    
    private String updatePatientExamSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE PatientExams ");
        sql.append("SET appointment_id = :appointmentId, doctor_id = :doctorId, exam_id = :examId, patient_id = :patientId, ");
        sql.append("service_dateTime = :serviceDate");
        return sql.toString();
    }
    
    private String deletePatientExamSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM PatientExams ");
        sql.append("WHERE id = ?");
        return sql.toString();
    }
    //*******************************************************************************************************************
    //  PatientExamDAO implementation methods
    //*******************************************************************************************************************
    @Override
    public PatientExam getPatientExamById(int id) {
        return jdbc.queryForObject(getPatientExamByIDString(), mapper, id);
    }

    @Override
    public List<PatientExam> getAllPatientExams() {
        return jdbc.query(getPatientExamsString(), mapper);
    }

    @Override
    public List<PatientExam> getPatientsPastExams(int patientId) {
        return jdbc.query(getPastPatientExamsSQL(), mapper, patientId);
    }

    @Override
    public List<PatientExam> getPatientsFutureExams(int patientId) {
        return jdbc.query(getFuturePatientExamsSQL(), mapper, patientId);
    }

    @Override
    public int addPatientExam(PatientExam exam) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(exam);
        return jdbc.update(insertPatientExamSQL(), param);
    }

    @Override
    public int updatePatientExam(PatientExam exam) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(exam);
        return jdbc.update(updatePatientExamSQL(), param);
    }

    @Override
    public int deletePatientExam(int deleteId) {
        return jdbc.update(deletePatientExamSQL(), deleteId);
    }
    
    private RowMapper<PatientExam> mapper = new ParameterizedRowMapper<PatientExam>(){

        @Override
        public PatientExam mapRow(ResultSet rs, int i) throws SQLException {
            PatientExam pe = new PatientExam();
            pe.setId(rs.getInt("id"));
            pe.setAppointmentId(rs.getInt("appointment_id")); //not sure of column name yet
            pe.setDoctorId(rs.getInt("doctor_id"));
            pe.setExamId(rs.getInt("exam_id")); 
            pe.setPatientId(rs.getInt("patient_id"));
            pe.setServiceDate(rs.getDate("service_dateTime"));
            return pe;
        }
   }; 
}
