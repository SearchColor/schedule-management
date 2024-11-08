package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleJoinResponseDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.erros.errorcode.CustomErrorCode;
import com.example.schedulemanagement.erros.exception.RestApiException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        LocalDate now = LocalDate.now();
        Date sqlNow = Date.valueOf(now);
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("password" , schedule.getPassword());
        parameters.put("user_id" , schedule.getUser_id());
        parameters.put("detail" , schedule.getDetail());
        parameters.put("registration_date" , now);
        parameters.put("modification_date" , now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new ScheduleResponseDto(key.longValue() , schedule.getPassword() , schedule.getUser_id() , schedule.getDetail() , sqlNow , sqlNow);
    }

    @Override
    public List<ScheduleJoinResponseDto> findAllSchedule() {

        return jdbcTemplate.query("select s.*, u.name from schedule as s  LEFT OUTER JOIN user as u ON s.user_id = u.id order by modification_date desc" , scheduleRowMapperV1());
    }

    @Override
    public List<ScheduleJoinResponseDto> findAllScheduleByPage(Integer pageNum, Integer pageSize) {

        String querySql = "select s.* ,u.name " +
                "from schedule as s LEFT OUTER JOIN user as u ON s.user_id = u.id " +
                "order by s.id desc LIMIT "+(pageNum-1)*pageSize+","+pageSize;

        return jdbcTemplate.query(querySql,scheduleRowMapperV1());
    }



    @Override
    public List<ScheduleJoinResponseDto> findAllScheduleByUserId(Long user_id) {
        return jdbcTemplate.query("select s.*, u.name from schedule as s  LEFT OUTER JOIN user as u ON s.user_id = u.id where s.user_id = ? order by modification_date desc" , scheduleRowMapperV1() , user_id);
    }

    @Override
    public List<ScheduleJoinResponseDto> findAllScheduleByDate(Date date) {
        return jdbcTemplate.query("select s.*, u.name from schedule as s  LEFT OUTER JOIN user as u ON s.user_id = u.id where s.modification_date = ? order by modification_date desc" , scheduleRowMapperV1() , date);
    }

    @Override
    public List<ScheduleJoinResponseDto> findAllScheduleByUserIdAndDate(Long user_id, Date date) {
        List<ScheduleJoinResponseDto> result= jdbcTemplate.query("select s.*, u.name " +
                "from schedule as s  LEFT OUTER JOIN user as u ON s.user_id = u.id " +
                "where s.user_id = ? && s.modification_date = ? order by modification_date desc" ,
                scheduleRowMapperV1() ,user_id, date);
        return result;
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(),id);
        return result.stream().findAny().orElseThrow(()->new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND));
    }


    @Override
    public int updateSchedule(Long id, String password, String detail) {
        int updateRow = jdbcTemplate.update("update schedule set detail = ? , modification_date = (current_date) where id = ? && password = ?" , detail, id, password );
        return updateRow;
    }

    @Override
    public int deleteSchedule(Long id, String password) {
        String deleteQuery = "delete from schedule where id = ? && password = ?";
        int result = jdbcTemplate.update(deleteQuery ,id ,password);
        return result;
    }

    @Override
    public int countScheduleById(Long id) {

        String selectQuery = "select count(id) as count from schedule where id = "+id;
        int result = jdbcTemplate.queryForObject(selectQuery , Integer.class);
        return result;
    }




    private RowMapper<ScheduleResponseDto> scheduleRowMapper(){
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("password"),
                        rs.getLong("user_id"),
                        rs.getString("detail"),
                        rs.getDate("registration_date"),
                        rs.getDate("modification_date")
                );
            }
        };
    }

    private RowMapper<ScheduleJoinResponseDto> scheduleRowMapperV1(){
        return new RowMapper<ScheduleJoinResponseDto>() {
            @Override
            public ScheduleJoinResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleJoinResponseDto(
                        rs.getLong("id"),
                        rs.getString("password"),
                        rs.getLong("user_id"),
                        rs.getString("name"),
                        rs.getString("detail"),
                        rs.getDate("registration_date"),
                        rs.getDate("modification_date")
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("password"),
                        rs.getLong("user_id"),
                        rs.getString("detail"),
                        rs.getDate("registration_date"),
                        rs.getDate("modification_date")
                );
            }
        };
    }
}
