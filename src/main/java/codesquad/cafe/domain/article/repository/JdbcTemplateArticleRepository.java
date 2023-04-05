package codesquad.cafe.domain.article.repository;

import codesquad.cafe.domain.article.domain.Article;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertAction;

    public JdbcTemplateArticleRepository(final DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("article");
    }

    @Override
    public void save(final Article article) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(article);
        insertAction.execute(params);
    }

    @Override
    public List<Article> findAll() {
        String sql = "select * from article";
        return namedParameterJdbcTemplate.query(sql,
                (rs, rowNum) -> new Article(
                            rs.getLong("id")
                            , rs.getString("writer")
                            , rs.getString("title")
                            , rs.getString("contents")
                            , rs.getTimestamp("date").toLocalDateTime()));
    }

    @Override
    public Article findById(final Long id){
        String sql = "SELECT * FROM article WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params,
                    (rs, rowNum) -> {
                        Article article = new Article(
                                rs.getLong("id")
                                , rs.getString("writer")
                                , rs.getString("title")
                                , rs.getString("contents")
                                , rs.getTimestamp("date").toLocalDateTime());
                    return article;
        });
    }
}