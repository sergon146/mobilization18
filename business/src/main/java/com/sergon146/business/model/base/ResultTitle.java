package com.sergon146.business.model.base;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 28.04.2018
 */

public class ResultTitle {
    public String keyword;
    public int count;

    public ResultTitle(String keyword, int count) {
        this.keyword = keyword;
        this.count = count;
    }
}
