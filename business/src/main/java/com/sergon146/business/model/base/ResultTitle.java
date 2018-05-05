package com.sergon146.business.model.base;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 28.04.2018
 */

public class ResultTitle {
    public String keyword;
    public int totalCount;

    public ResultTitle(String keyword, int totalCount) {
        this.keyword = keyword;
        this.totalCount = totalCount;
    }
}
