package org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller;

public abstract class ApiController {

    protected static final String SEARCH_PATH = "/search";

    protected static final String ID_PATH_VAR = "/{id}";

    protected static final String SUM_PATH = "/sum";

    protected static final String COUNT_PATH = "/count";

    public static String searchPath() {
        return SEARCH_PATH;
    }

    public static String idPathVar() {
        return ID_PATH_VAR;
    }

    public static String sumPath() {
        return SUM_PATH;
    }

    public static String countPath() {
        return COUNT_PATH;
    }


}
