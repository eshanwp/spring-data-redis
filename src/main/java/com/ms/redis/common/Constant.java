package com.ms.redis.common;

/**
 * Api Routing Constant
 * @author Eshan
 */
public interface Constant {

    String PRODUCE_TYPE_JSON = "application/json";
    String CONSUMES_TYPE_JSON = "application/json";

    String API_ROOT = "/v1/web";


    /**
     * User Type Controller
     */
    String STORE_STUDENT = "/student";
    String UPDATE_STUDENT = "/student";
    String FIND_STUDENT = "/student/{id}";
    String FIND_STUDENTS = "/students";
    String GET_ALL_STUDENTS = "/student/all";
}
