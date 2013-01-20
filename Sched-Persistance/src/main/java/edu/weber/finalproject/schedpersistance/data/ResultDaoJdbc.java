/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.ResultDAO;
import edu.weber.finalproject.schedschema.Result;
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
public class ResultDaoJdbc implements ResultDAO{
    
    private SimpleJdbcTemplate jdbc;
    
    public ResultDaoJdbc(SimpleJdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    //*******************************************************************************************************************
    //      SQL string methods
    //*******************************************************************************************************************
    
    private String getResultsSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, patient_exam_id, results ");
        sql.append("FROM Results");
        return sql.toString();
    }
    
    private String getResultByIdSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getResultsSQL());
        sql.append("WHERE id = ?");
        return sql.toString();
    }
    
    private String getResultsByPatientExamSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(getResultsSQL());
        sql.append("WHERE patient_exam_id = ?");
        return sql.toString();
    }
    
    private String insertResultSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Results (");
        sql.append("patient_exam_id, results) ");
        sql.append("VALUES (");
        sql.append(":patientExamId, :result)");
        return sql.toString();        
    }
    
    private String updateResultSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Results ");
        sql.append("SET patient_exam_id = :patientExamId, results = :result");
        return sql.toString();
    }
    //*******************************************************************************************************************
    //      ResultDAO implementation methods
    //*******************************************************************************************************************
    
    @Override
    public Result getResultById(int id) {
        return (Result) jdbc.queryForObject(getResultByIdSQL(), mapper, id);
    }

    @Override
    public List<Result> getResultsByPatientExam(int patientExamId) {
        return jdbc.query(getResultsByPatientExamSQL(), mapper, patientExamId);
    }

    @Override
    public List<Result> getAllResults() {
        return jdbc.query(getResultsSQL(), mapper);
    }

    @Override
    public int updateResult(Result result) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(result);
        if(result.getId()!=0){
            return jdbc.update(updateResultSQL(), param);
        } else return 1;
    }

    @Override
    public int addResult(Result result) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(result);
        if (result.getId()==0){
            return jdbc.update(insertResultSQL(), param);
        } else return 0;
    }

    @Override
    public int deleteResult(int id) {
        String deleteSQL = "DELETE FROM Results WHERE id = ?";
        return jdbc.update(deleteSQL, id);
    }
    
    private RowMapper<Result> mapper = new ParameterizedRowMapper<Result>(){

        @Override
        public Result mapRow(ResultSet rs, int i) throws SQLException {
            Result result = new Result();
            result.setId(rs.getInt("id"));
            result.setPatientExamId(rs.getInt("patient_exam_id"));
            result.setResult(rs.getString("result"));
            return result;
        }
   }; 
}
