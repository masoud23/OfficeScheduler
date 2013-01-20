/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.DoctorDAO;
import edu.weber.finalproject.schedschema.Doctor;
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
public class DoctorDaoJdbc implements DoctorDAO{

    private SimpleJdbcTemplate jdbc;
    private static final String ALL_FIELDS = "id, first_name, last_name, email, phone, npi ";
    
    public DoctorDaoJdbc(SimpleJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    //***************************************************************************************************************
    //      SQL String methods
    //***************************************************************************************************************
    
    private String getAllDoctorsString() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(ALL_FIELDS);
        sql.append("FROM Doctors");
        return sql.toString();
    }
    
    private String getDoctorByIdString() {
        StringBuilder sql = new StringBuilder();
        sql.append(getAllDoctorsString());
        sql.append(" WHERE id = ?");
        return sql.toString();
    }
    
    private String getDoctorByNPIString() {
       StringBuilder sql = new StringBuilder();
       sql.append(getAllDoctorsString());
       sql.append(" WHERE npi = ?");
       return sql.toString();
    }
    
    private String insertDoctor() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT into Doctors (");
        sql.append("first_name, last_name, email, phone, npi) ");
        sql.append("VALUES (:first, :last, :email, :phone, :npi)");
        return sql.toString();
    }
    
    private String updateDoctor() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Doctors ");
        sql.append("SET first_name=:first, last_name=:last, email=:email, phone=:phone, npi=:npi ");
        sql.append("WHERE id = :id");
        return sql.toString();
    }
    
    private String deleteDoctor() {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM Doctors ");
        sql.append("WHERE id = ?");
        return sql.toString();
    }
    //****************************************************************************************************************
    //      DoctorDAO Implementation methods
    //****************************************************************************************************************
    
    @Override
    public Doctor getDoctorById(int id) {
        return (Doctor) jdbc.queryForObject(getDoctorByIdString(), mapper, id);
    }

    @Override
    public Doctor getDoctorByNpi(long npi) {
        return (Doctor) jdbc.queryForObject(getDoctorByNPIString(), mapper, npi);
    }

    @Override
    public List<Doctor> getDoctors() {
        return jdbc.query(getAllDoctorsString(), mapper);
    }

    @Override
    public int addDoctor(Doctor doctor) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(doctor);
        if (doctor.getId()==0){
            return jdbc.update(insertDoctor(), param);
        } else return 0;
    }

    @Override
    public int updateDoctor(Doctor doctor) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(doctor);
        if(doctor.getId()!=0){
            return jdbc.update(updateDoctor(), param);
        } else return 1;
    }

    @Override
    public int deleteDoctor(int id) {
        return jdbc.update(deleteDoctor(), id);
    }
    
    private RowMapper<Doctor> mapper = new ParameterizedRowMapper<Doctor>(){

        @Override
        public Doctor mapRow(ResultSet rs, int i) throws SQLException {
            Doctor doctor = new Doctor();
            doctor.setId(rs.getInt("id"));
            doctor.setFirst(rs.getString("first_name")); //not sure of column name yet
            doctor.setLast(rs.getString("last_name"));
            doctor.setEmail(rs.getString("email")); 
            doctor.setPhone(rs.getString("phone"));
            doctor.setNpi(rs.getLong("npi"));
            return doctor;
        }
   }; 
}
