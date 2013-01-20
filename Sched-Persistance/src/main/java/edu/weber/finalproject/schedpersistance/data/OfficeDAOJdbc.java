/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.OfficeDAO;
import edu.weber.finalproject.schedschema.Office;
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
 * @author Mike
 */
public class OfficeDAOJdbc implements OfficeDAO{
    
    private SimpleJdbcTemplate jdbc;
    
    private static final String ALL_FIELDS = "o.id, o.address_id, o.name, a.address1, a.city, a.state, a.zip";
    
    public OfficeDAOJdbc(SimpleJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    //********************************************************************************************************************
    //   SQL String Methods
    //********************************************************************************************************************
    private String getOfficeByIdQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append(getAllOfficesQuery());
        sql.append(" WHERE o.id = ?");
        return sql.toString();
    }
    
    private String getAllOfficesQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(ALL_FIELDS);
        sql.append("FROM Offices o");
        sql.append("LEFT JOIN Addresses a ");
        sql.append("ON o.address_id = a.address_id");
        return sql.toString();
    }
    
    private String addAddressSql() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Addresses (address1, city, state, zip) ");
        sql.append("VALUES (:address, :city, :state, :zip)");
        return sql.toString();
    }
    
    private String addOfficeSql(int addressId) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Offices (address_id, name ");
        sql.append("VALUES (");
        sql.append(addressId);
        sql.append(", :name)");
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
    
    private String updateOfficeString() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Offices SET ");
        sql.append("address_id = :addressId, name = :name ");
        sql.append("WHERE id = :id");
        return sql.toString();
    }
    
    //*********************************************************************************************************************
    //      OfficeDAO implementation methods
    //*********************************************************************************************************************
    @Override
    public int addOffice(Office office) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(office);
        //get the generated addressId from the address insert 
        KeyHolder holder = new GeneratedKeyHolder();
        if(office.getAddressId() == 0) {
            jdbc.getNamedParameterJdbcOperations().update(addAddressSql(), param, holder);
        }
        int addressId = holder.getKey().intValue();
        if(office.getId() == 0 && addressId != 0) {
            return jdbc.update(addOfficeSql(addressId), param);
        }
        else {
            return 0;
        }
    }
    @Override
    public Office getOfficeById(int officeId) {
        return (Office) jdbc.queryForObject(getOfficeByIdQuery(), mapper, officeId);
    }

    @Override
    public List<Office> getAllOffices() {
        return jdbc.query(getAllOfficesQuery(), mapper);
    }

    @Override
    public int updateOffice(Office office) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(office);
        if(office.getId()!= 0){
            jdbc.update(updateAddressString(), param);
            return jdbc.update(updateOfficeString(), param);
        } else return 1;
    }

    @Override
    public int deleteOffice(int deleteId) {
        String deleteStatement = "DELETE FROM Offices WHERE id = ?";
        return jdbc.update(deleteStatement, deleteId);
    }
    
     private RowMapper<Office> mapper = new ParameterizedRowMapper<Office>(){

        @Override
        public Office mapRow(ResultSet rs, int i) throws SQLException {
            Office office = new Office();
            office.setId(rs.getInt(1));
            office.setAddressId(2);
            office.setName(rs.getString(3));
            office.setAddress(rs.getString(4));
            office.setCity(rs.getString(5));
            office.setState(rs.getString(6));
            office.setZip(rs.getString(7));
           
            return office;
        }
   }; 
}
