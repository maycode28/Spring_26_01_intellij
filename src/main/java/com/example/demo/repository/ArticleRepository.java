package com.example.demo.repository;

import com.example.demo.vo.Article;
import org.apache.ibatis.annotations.*;


import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ArticleRepository {

    public int writeArticle(String title, String body, int memberId, int boardId);

    public void deleteArticle(int id);

    public void modifyArticle(int id, String title, String body);

    public Article getArticleById(int id);


    int getLastInsertId();

    public Article getForPrintArticle(int id);

    public List<Article> getForPirntArticles(int boardId, int startsFrom, int articlesPerPage);

    int getArticleCount(int boardId);
}
