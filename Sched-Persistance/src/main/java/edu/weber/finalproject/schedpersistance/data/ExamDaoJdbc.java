/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.ExamDAO;
import edu.weber.finalproject.schedschema.Exam;
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
public class ExamDaoJdbc implements ExamDAO{
    
    private SimpleJdbcTemplate jdbc;
    
    private static final String ALL_FIELDS = "id, resource_id, name, code, price , active, description, duration_mins ";
    
    public ExamDaoJdbc(SimpleJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    //********************************************************************************************************************
    //   SQL String methods
    //********************************************************************************************************************
    
    private String getExamByIdString() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(ALL_FIELDS);
        sql.append("FROM Exams ");
        sql.append("WHERE id = ?");
        return sql.toString();
    }
    
    private String getExamsByActiveString() {
        StringBuilder sql = new StringBuilder();
        sql.append(getAllExamsString());
        sql.append(" WHERE active = ?");
        return sql.toString();
    }
    
    private String getActiveExamsByResourceSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getAllExamsString());
        sql.append(" WHERE active = ?");
        sql.append(" AND resource_id ?");
        return sql.toString();
    }
    
    private String getAllExamsString() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(ALL_FIELDS);
        sql.append("FROM Exams");
        return sql.toString();
    }
    
    private String addExamString() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Exams (");
        sql.append("resource_id, name, code, price , active, description, duration_mins) ");
        sql.append("VALUES (");
        sql.append(":resourceId, :name, :code, :price, :active, :description, :durationMins)");
        return sql.toString();
    }
    
    private String updateExamString() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Exams ");
        sql.append("SET resource_id=:resourceId, name=:name, code=:code, price=:price, active=:active, ");
        sql.append("description = :description, duration_mins = :durationMins ");
        sql.append("WHERE id = :id");
        return sql.toString();
    }
    
    private String deleteExamString() {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM Exams ");
        sql.append("WHERE id = ?");
        return sql.toString();
    }
    //********************************************************************************************************************
    // ExamDAO implementation methods
    //********************************************************************************************************************
    @Override
    public Exam getExamById(int id) {
        return (Exam) jdbc.queryForObject(getExamByIdString(), mapper, id);
    }

    @Override
    public List<Exam> getAllExams() {
        return jdbc.query(getAllExamsString(), mapper);
    }

    @Override
    public List<Exam> getExamsByActive(boolean active) {
        return jdbc.query(getExamsByActiveString(), mapper, active);
    }
    
    @Override
    public List<Exam> getActiveExamsByResource(boolean active, int resourceId) {
        return jdbc.query(getActiveExamsByResourceSQL(), mapper, active, resourceId);
    }
    
    @Override
    public int addExam(Exam exam) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(exam);
        if (exam.getId()==0){
            return jdbc.update(addExamString(), param);
        } else return 0;
    }

    @Override
    public int updateExam(Exam exam) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(exam);
        if(exam.getId()!=0){
            return jdbc.update(updateExamString(), param);
        } else return 1;
    }

    @Override
    public int deleteExam(int id) {
        return jdbc.update(deleteExamString(), id);
    }
    
    private RowMapper<Exam> mapper = new ParameterizedRowMapper<Exam>(){

        @Override
        public Exam mapRow(ResultSet rs, int i) throws SQLException {
            Exam exam = new Exam();
            exam.setId(rs.getInt("id"));
            exam.setName(rs.getString("name"));
            exam.setCode(rs.getString("code"));
            exam.setPrice(rs.getBigDecimal("price")); 
            exam.setActive(rs.getBoolean("active"));
            exam.setDescription(rs.getString("description"));
            exam.setDurationMins(rs.getInt("duration_mins"));
            exam.setResourceId(rs.getInt("resource_id"));
            return exam;
        }
   };
}
