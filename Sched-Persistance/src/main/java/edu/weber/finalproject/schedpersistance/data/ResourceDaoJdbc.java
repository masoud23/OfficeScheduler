/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.ResourceDAO;
import edu.weber.finalproject.schedschema.Resource;
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
public class ResourceDaoJdbc implements ResourceDAO{
    
    private SimpleJdbcTemplate jdbc;
    
    public ResourceDaoJdbc(SimpleJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    //*********************************************************************************************************************
    //      SQL string methods
    //*********************************************************************************************************************
    private String getResourcesSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, resource_id, name ");
        sql.append("FROM Resources");
        return sql.toString();
    }
    
    private String getResourceByIdSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getResourcesSQL());
        sql.append(" WHERE id = ?");
        return sql.toString();
    }
    
    private String insertResourceSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Resources (");
        sql.append("resource_id, name) ");
        sql.append("VALUES (");
        sql.append(":resourceId, :name)");
        return sql.toString();
    }
    
    private String updateResourceSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Resources ");
        sql.append("SET resource_id = :resourceId, name = :name ");
        sql.append("WHERE resource_id = :resourceId");
        return sql.toString();
    }
    //*******************************************************************************************************************
    //      ResourceDAO implementation methods
    //*******************************************************************************************************************
    @Override
    public Resource getResourceById(int id) {
        return jdbc.queryForObject(getResourceByIdSQL(), mapper, id);
    }

    @Override
    public List<Resource> getResources() {
        return jdbc.query(getResourcesSQL(), mapper);
    }

    @Override
    public int addResource(Resource resource) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(resource);
        if (resource.getId()==0){
            return jdbc.update(insertResourceSQL(), param);
        } else return 0;
    }

    @Override
    public int updateResource(Resource resource) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(resource);
        if(resource.getId()!=0){
            return jdbc.update(updateResourceSQL(), param);
        } else return 1;
    }

    @Override
    public int deleteResource(int id) {
        String deleteSQL = "DELETE FROM Resources WHERE id = ?";
        return jdbc.update(deleteSQL, id);
    }
    
    private RowMapper<Resource> mapper = new ParameterizedRowMapper<Resource>(){

        @Override
        public Resource mapRow(ResultSet rs, int i) throws SQLException {
            Resource resource = new Resource();
            resource.setId(rs.getInt("id"));
            resource.setOfficeId(rs.getInt("resource_id"));
            resource.setName(rs.getString("name"));
            return resource;
        }
   }; 
}
