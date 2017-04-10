package com.example.controller;

import com.example.model.Article;
import com.example.model.Query;
import com.example.service.SearchService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "0", paramType = "query"),
            @ApiImplicitParam(name = "size", defaultValue = "20", paramType = "query")
    })
    @ApiOperation(
            notes = "All parameters are not required, just use whatever you want to search: " +
            " { author or publishDate or keywords }" +
                    "For detailed request examples see the readme file"
            , value = "Article search")
    @PostMapping(path = "/articles")
    public List<Article> articles(@RequestBody Query query, Pageable pageable) {
        return searchService.search(query, pageable);
    }

}
