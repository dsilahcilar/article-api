package com.example.controller;

import com.example.model.Article;
import com.example.service.ArticleCommand;
import com.example.service.ArticleQuery;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleCommand articleCommand;
    private final ArticleQuery articleQuery;

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "0", paramType = "query"),
            @ApiImplicitParam(name = "size", defaultValue = "20", paramType = "query")
    })
    public Collection<Article> articles(Pageable pageable) {
        return articleQuery.articles(pageable);
    }

    @GetMapping(path = "/{id}")
    public Article article(@PathVariable Long id) {
        return articleQuery.article(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Article article) {
        articleCommand.save(article);
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody Article article) {
        articleCommand.update(id, article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        articleCommand.delete(id);
    }

}
