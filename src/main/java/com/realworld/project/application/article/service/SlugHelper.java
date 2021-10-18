package com.realworld.project.application.article.service;

import java.util.Locale;

public class SlugHelper {

    /**
     * slug converter
     *
     * 대문자는 소문자로
     * @param text title
     * @return convert 된 값
     */
    public static String convert(String text) {
        text = text.replace(" ", "-");
        text = text.toLowerCase(Locale.ROOT);
        return text;
    }
}
