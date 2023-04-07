package codesquad.cafe.domain.user.repository;

import codesquad.cafe.domain.article.domain.Article;
import codesquad.cafe.domain.user.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertAction;

    public JdbcUserRepository(final DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("cafe_user");
    }

    @Override
    public User save(final User user) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        insertAction.execute(params);
        return user;
    }

    @Override
    public Optional<User> findById(final String id) {
        String sql = "SELECT * FROM cafe_user WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(sql, params,
                    (rs, rowNum) -> new User(
                            rs.getString("id")
                            , rs.getString("password")
                            , rs.getString("name")
                            , rs.getString("email")));
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from cafe_user";
        return namedParameterJdbcTemplate.query(sql,
                (rs, rowNum) -> new User(
                        rs.getString("id")
                        , rs.getString("password")
                        , rs.getString("name")
                        , rs.getString("email")));
    }

    @Override
    public void update(final User user) {
        String sql = "update cafe_user set password = :password, name = :name, email = :email where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", user.getId());
        params.addValue("password", user.getPassword());
        params.addValue("name", user.getName());
        params.addValue("email", user.getEmail());

        namedParameterJdbcTemplate.update(sql, params);
    }
}