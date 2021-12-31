package org.dalvarez.shop.shop_core.article.domain.model;


import org.dalvarez.shop.shop_core.shop_common.shared.domain.exception.DomainException;

public class InvalidArticleException extends DomainException {

    private static final String ERROR_CODE_ID = Article.class.getSimpleName()
                                                             .toLowerCase();

    protected InvalidArticleException(String errorCode,
                                      String message) {
        super(buildErrorCode(errorCode), message);
    }

    private static String buildErrorCode(String errorCode) {
        return String.join(".", ERROR_CODE_ID, errorCode);
    }

}
