/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.PatientDAO;
import edu.weber.finalproject.schedschema.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author Masoud
 */
public class PatientDaoJdbc implements PatientDAO{
    private SimpleJdbcTemplate jdbc;
    
    private static final String ALL_FIELDS= "p.id, p.address_id, p.username, p.password, p.first_name, p.last_name, p.middle_name, p.birth_date, p.email, p.phone, a.address1, a.city, a.state, a.zip";

    public PatientDaoJdbc (SimpleJdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    
    //********************************************************************************************************************
    //   SQL String Methods
    //********************************************************************************************************************
    private String checkUserNames() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM Patient ");
        sql.append("WHERE username = ?");
        return sql.toString();
    }
    
    private String getAllPatientsQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(ALL_FIELDS);
        sql.append(" FROM Patient p ");
        sql.append("LEFT JOIN Addresses a ");               //address table name??
        sql.append("ON p.address_id = a.address_id");        //don't know actual column names  
        return sql.toString();
    }
    
    private String getOnePatientById() {
        StringBuilder sql = new StringBuilder();
        sql.append(getAllPatientsQuery());
        sql.append(" WHERE p.id = ?");
        return sql.toString();
    }
    
    private String getPatientByAuthQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append(getAllPatientsQuery());
        sql.append(" WHERE p.username = ? ");
        sql.append("AND p.password = ?");
        return sql.toString();
    }
    
    private String addAddress() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Addresses (address1, city, state, zip) ");
        sql.append("VALUES (:address, :city, :state, :zip)");
        return sql.toString();
    }
    
    private String addPatient(int addressId) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Patient (address_id, username, password, first_name, middle_name, last_name, phone, email, ");
        sql.append("birth_date) ");
        sql.append("VALUES (");
        sql.append(addressId);
        sql.append(", :username, :password, :first, :middle, :last, :phone, :email, :birthDate)");
        System.out.print(sql);
        return sql.toString();        
    }
    
    private String updateAddressString() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Addresses SET ");
        sql.append("address1=:address,city=:city,state=:state,zip=:zip");
        sql.append("WHERE address_id = :addressId");
        return sql.toString();
    }
    /**
     * must be used in conjunction with Update Address 
     * @return the Update statement
     */
    private String updatePatientString() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Patient SET ");
        sql.append("address_id = :addressId, username=:userName, password=:password, first_name:firstName, last_name:lastName, ");
        sql.append("middle_name:middleName, birth_date=:birthDate, email=:email, phone=:phone "); 
        sql.append("WHERE id = :id");
        return sql.toString();
    }
    
    //*********************************************************************************************************************
    //      PatientDAO implementation methods
    //*********************************************************************************************************************
    
    /**
     * If the username and password belong to patient in the DB, the patientId is returned.  Returns 0 if 
     * the username and password do not belong to a patient.
     * @param user
     * @param pass
     * @return 
     */
    @Override
    public int authenticatePatient(String user, String pass) {
        List<Patient> patientList = jdbc.query(getPatientByAuthQuery(), mapper, user, pass);
        if(patientList.isEmpty()) {
            return 0;
        }
        else {
            return patientList.get(0).getId();
        }
    }

    @Override
    public Patient getPatientById(int id) {
        return (Patient) jdbc.queryForObject(getOnePatientById(), mapper, id);
    }
    
    /**
     * For use by Admin Manager to be able to pull up all the patients
     * @return all the patients in persistence
     */
    @Override
    public List<Patient> getAllPatients() {
        return jdbc.query(getAllPatientsQuery(), mapper);
    }

    @Override
    public int addPatient(Patient patient) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(patient);
        //get the generated addressId from the address insert 
        KeyHolder holder = new GeneratedKeyHolder();
        if(patient.getAddressId() == 0) {
            jdbc.getNamedParameterJdbcOperations().update(addAddress(), param, holder);         
        }
        int addressId = holder.getKey().intValue();
        if (patient.getId() == 0 && addressId != 0){
            return jdbc.update(addPatient(addressId), param);
        } 
        else {
            return 0;
        }
    }

    @Override
    public int updatePatient(Patient patient) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(patient);
        if(patient.getId()!=0){
            return jdbc.update(updatePatientString(), param);
        } else return 1;
    }

    @Override
    public int deletePatient(int id) {
        String deleteStatement = "DELETE FROM patient WHERE id = ?";
        return jdbc.update(deleteStatement, id);
    }
    
    /**
     * Prevents duplicate usernames.  Used to register new users
     * @param userName
     * @return username already exists in DB? true or false
     */
    @Override
    public boolean checkExistingUserNames(String userName) {
        List<Patient> patientList = jdbc.query(checkUserNames(), mapper, userName);
        if(patientList.isEmpty())
            return true;
        else
            return false;
    }
    
    private RowMapper<Patient> mapper = new ParameterizedRowMapper<Patient>(){

        @Override
        public Patient mapRow(ResultSet rs, int i) throws SQLException {
            Patient patient = new Patient();
            patient.setId(rs.getInt(1));
            patient.setAddressId(rs.getInt(2));
            patient.setUsername(rs.getString(3));
            patient.setPassword(rs.getString(4));
            patient.setFirst(rs.getString(5));
            patient.setLast(rs.getString(6));
            patient.setMiddle(rs.getString(7));
            patient.setBirthDate(rs.getDate(8));
            patient.setEmail(rs.getString(9));
            patient.setPhone(rs.getString(10));
            patient.setAddress(rs.getString(11));
            patient.setCity(rs.getString(12));
            patient.setState(rs.getString(13));
            patient.setZip(rs.getString(14));
            return patient;
        }
    
   };
}
