package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<ScheduleResponseDto> findAllSchedule() {

        return jdbcTemplate.query("select * from schedule order by modification_date desc" , scheduleRowMapper());
    }

    @Override
    public List<ScheduleResponseDto> findAllScheduleByUserId(Long user_id) {
        return jdbcTemplate.query("select * from schedule where user_id = ? order by modification_date desc" , scheduleRowMapper() , user_id);
    }

    @Override
    public List<ScheduleResponseDto> findAllScheduleByDate(Date date) {
        return jdbcTemplate.query("select * from schedule where modification_date = ? order by modification_date desc" , scheduleRowMapper() , date);
    }

    @Override
    public List<ScheduleResponseDto> findAllScheduleByUserIdAndDate(Long user_id, Date date) {
        return jdbcTemplate.query("select * from schedule where user_id = ? && modification_date = ? order by modification_date desc" , scheduleRowMapper() ,user_id, date);
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(),id);
        return result.stream().findAny().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = "+ id));
    }


    @Override
    public int updateSchedule(Long id, String password, Long user_id, String detail) {
        return 0;
    }

    @Override
    public int deleteSchedule(Long id, String password) {
        return 0;
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
