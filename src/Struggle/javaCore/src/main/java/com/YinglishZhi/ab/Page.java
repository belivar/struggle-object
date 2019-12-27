package com.YinglishZhi.ab;

import lombok.Builder;
import lombok.Data;

/**
 * @author LDZ
 * @date 2019-12-16 20:05
 */
@Data
@Builder
public class Page {


    private int pageNum;

    private int pageSize;

    private int totalCount;

    private int totalPages;


    public static class PageBuilderEx extends PageBuilder {
        @Override
        public Page build() {
            Page page = super.build();
            page.totalPages = (page.getTotalCount() / page.getPageSize()) + (page.getTotalCount() / page.getPageSize()) == 0 ? 0 : 1;
            return page;
        }
    }

    public static PageBuilder builder() {
        return new PageBuilderEx();
    }
}
