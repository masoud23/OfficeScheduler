/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.weber.finalproject.schedpersistance.data;

import edu.weber.finalproject.schedpersistance.CalendarDAO;
import edu.weber.finalproject.schedschema.Calendar;
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
//@Component
public class CalendarDaoJdbc implements CalendarDAO{
 
    //@Autowired
    private SimpleJdbcTemplate jdbc;
    
    private static final String ALL_FIELDS = "id, dateTime, availability, start_time, end_time, slots_id  ";
    private static final String GET_CALENDARS_BY_AVAILABLE = "SELECT " + ALL_FIELDS + "FROM calendar WHERE availability = ?";
    private static final String DELETE_CALENDAR = "DELETE FROM calendar WHERE id = ?";


    
    public CalendarDaoJdbc() {}
    public CalendarDaoJdbc(SimpleJdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public void setJdbc(SimpleJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    //*********************************************************************************************************************
    //          SQL String methods
    //*********************************************************************************************************************
    
    private String getAllCalendarsSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, resource_id, slot_span_mins, include_saturday, include_sunday, workday_start, ");
        sql.append("workday_end ");
        sql.append("FROM Calendars");
        return sql.toString();
    }
    
    private String getCalendarByIdSQL() {
       StringBuilder sql = new StringBuilder();
       sql.append(getAllCalendarsSQL());
       sql.append(" WHERE id = ?");
       return sql.toString();
    }
    
    private String insertCalendarSQL() {
       StringBuilder sql = new StringBuilder();
       sql.append("INSERT INTO Calendars (");
       sql.append("resource_id, slot_span_mins, include_saturday, include_sunday, workday_start, workday_end) ");
       sql.append("VALUES (:resourceId, :slotSpanMins, :includeSats, :includeSuns, :workStartTime, :workEndTime)");
       return sql.toString();
    }
    
    private String updateCalendarSQL() {
       StringBuilder sql = new StringBuilder();
       sql.append("UPDATE Calendars ");
       sql.append("SET resource_id = :resourceId, slot_span_mins = :slotSpanMins, include_saturday = :includeSats, ");
       sql.append("include_sunday =:includeSuns, workday_start = :workStartTime, workday_end = :workEndTime");
       return sql.toString();
    }
    //*********************************************************************************************************************
    //          CalendarDAO implementation methods
    //*********************************************************************************************************************
    @Override
    public Calendar getCalendarById(int id) {
        return (Calendar) jdbc.queryForObject(getCalendarByIdSQL(), mapper, id);
    }

    /**
     * @deprecated Needs to be replaced since it will not work with new db schema
     * @param available
     * @return 
     */
    @Override
    public List<Calendar> getCalendarsByAvailability(int available) {
        return jdbc.query(GET_CALENDARS_BY_AVAILABLE, mapper, available);
    }

    @Override
    public int addCalendar(Calendar cal) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(cal);
        return jdbc.update(insertCalendarSQL(), param);
     //   return jdbc.queryForInt(GET_ID_NUM);            //may need the insert id?? -- used to
    }

    @Override
    public int updateCalendar(Calendar cal) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(cal);
        return jdbc.update(updateCalendarSQL(), param);
    }

    @Override
    public int deleteCalendar(int id) {
        return jdbc.update(DELETE_CALENDAR, id);
    }

    @Override
    public List<Calendar> getAllCalendars() {
        return jdbc.query(getAllCalendarsSQL(), mapper);
    }
    
    private RowMapper<Calendar> mapper = new ParameterizedRowMapper<Calendar>(){

        @Override
        public Calendar mapRow(ResultSet rs, int i) throws SQLException {
            Calendar calendar = new Calendar();
            calendar.setId(rs.getInt("id"));
            calendar.setResourceId(rs.getInt("resource_id"));
            calendar.setSlotSpanMins(rs.getInt("slot_span_mins"));
            calendar.setIncludeSats(rs.getBoolean("includeSaturday"));
            calendar.setIncludeSuns(rs.getBoolean("includeSunday"));
            calendar.setWorkStartTime(rs.getDate("workday_start"));
            calendar.setWorkEndTime(rs.getDate("workday_end"));
            return calendar;
        }
    
   };
}
