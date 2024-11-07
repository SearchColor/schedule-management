package com.example.schedulemanagement.repository;



import com.example.schedulemanagement.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class JdbcUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findUserById(Long id) {

        List<User> result = jdbcTemplate.query("select * from user where id = ? ", userRowMapper() , id );
        return result.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = "+id));
    }

    private RowMapper<User> userRowMapper(){
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("name")
                );
            }
        };
    }
}
