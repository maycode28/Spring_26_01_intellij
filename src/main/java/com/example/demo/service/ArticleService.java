package com.example.demo.service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
//        makeTestData();
    }
//    private void makeTestData() {
//        for (int i = 1; i <= 10; i++) {
//            String title = "제목 " + i;
//            String body = "내용 " + i;
//
//            articleRepository.writeArticle(title, body);
//        }
//    }
    public Article writeArticle(String title, String body) {
        articleRepository.writeArticle(title, body);
        return new Article(title,body);
    }

    public void deleteArticle(int id) {
        articleRepository.deleteArticle(id);
    }

    public void modifyArticle(int id, String title, String body) {
        articleRepository.modifyArticle(id, title, body);

    }

    public Article getArticleById(int id) {
        return articleRepository.getArticleById(id);
    }

    public List<Article> getArticles() {
        return articleRepository.getArticles();
    }

}
