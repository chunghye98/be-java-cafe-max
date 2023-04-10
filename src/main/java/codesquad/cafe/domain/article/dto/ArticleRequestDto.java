package codesquad.cafe.domain.article.dto;

import codesquad.cafe.domain.article.domain.Article;

import java.time.LocalDateTime;

public class ArticleRequestDto {

    private String writer;
    private String title;
    private String contents;

    public ArticleRequestDto(final String writer, final String title, final String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Article toEntity() {
        return new Article( writer, title, contents);
    }
}
