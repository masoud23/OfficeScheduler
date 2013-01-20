/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.AdminDAO;
import edu.weber.finalproject.schedschema.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 *
 * @author Masoud
 */
public class AdminDaoJdbc implements AdminDAO{
    
    private SimpleJdbcTemplate jdbc;
    
    private static final String ALL_FIELDS= "id, username, password, first_name, last_name, middle_name, admin_type_id, email, phone, address, city, state, zip";
    private static final String ALL_MEMBERS=":id,:userName,:password,:firstName,:lastName,:middleName, :email, :phone, :city, :state, :zip";
    private static final String UPDATE_STRING="id=:id,username=:userName,password=:password,first_name=:firstName,last_name=:lastName,"
            + "middle_name=:middleName,email=:email,phone=:phone,address=:address,city=:city,state=:state,zip=:zip";
    
    private static final String GET_ALL_ADMIN = "SELECT " + ALL_FIELDS +" FROM Admin";
    private static final String GET_ADMIN = "SELECT " + ALL_FIELDS + " FROM Admin where id = ?";
    private static final String GET_ADMIN_BY_NAME = "SELECT " + ALL_FIELDS + " FROM Admin WHERE username = ? AND password = ?";
    private static String INSERT_ADMIN= "INSERT INTO Admin (" + ALL_FIELDS.replaceFirst("id,", "") + ") VALUES ("+ ALL_MEMBERS.replaceFirst(":id,", "") + ")";
    private static String UPDATE_ADMIN = "UPDATE Admin SET " + UPDATE_STRING + " WHERE id = :id";
    private static final String DELETE_ADMIN= "DELETE FROM A WHERE id =?";
    
    public AdminDaoJdbc (SimpleJdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    
    /**
     * Checks if the username and password belong to an admin in the DB.
     * @param user
     * @param pass
     * @return adminId if present else 0.
     */
    @Override
    public int authenticateAdmin(String user, String pass) {
        List<Admin> adminList = new ArrayList<Admin>();
        adminList = jdbc.query(GET_ADMIN_BY_NAME, mapper, user, pass);
        if(adminList.isEmpty()){
            return 0;
        }
        else {
            return adminList.get(0).getId();
        }
    }

    @Override
    public Admin getAdminById(int id) {
        return (Admin) jdbc.queryForObject(GET_ADMIN, mapper, id);
    }

    @Override
    public int addAdmin(Admin admin) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(admin);
        if (admin.getId()==0){
            return jdbc.update(INSERT_ADMIN, param);
        } else return 0;

    }

    @Override
    public int updateAdmin(Admin admin) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(admin);
        if(admin.getId()!=0){
            return jdbc.update(UPDATE_ADMIN, param);
        } else return 1;
    }

    @Override
    public int deleteAdmin(int id) {
        return jdbc.update(DELETE_ADMIN, id);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return jdbc.query(GET_ALL_ADMIN, mapper);
    }
    
    private RowMapper<Admin> mapper = new ParameterizedRowMapper<Admin>(){
    
    @Override
    public Admin mapRow(ResultSet rs, int i) throws SQLException{
        Admin admin = new Admin();
        admin.setId(rs.getInt("id"));
        admin.setUsername(rs.getString("username"));
        admin.setPassword(rs.getString("password"));
        admin.setFirst(rs.getString("first_name"));
        admin.setLast(rs.getString("last_name"));
        admin.setMiddle(rs.getString("middle_name"));
        admin.setEmail(rs.getString("email"));
        admin.setPhone(rs.getString("phone"));
        admin.setAddress(rs.getString("address"));
        admin.setCity(rs.getString("city"));
        admin.setState(rs.getString("state"));
        admin.setZip(rs.getString("zip"));
        return admin;
    }
   };

}
