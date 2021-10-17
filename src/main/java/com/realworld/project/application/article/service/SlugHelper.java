package com.realworld.project.application.article.service;

public class SlugHelper {

    public static String convert(String text) {
        return text.replace(" ", "-");
    }
}
