package org.dalvarez.ddd_example.shared.infrastructure.rest_api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cucumber.spring.CucumberContextConfiguration;
import org.dalvarez.ddd_example.shared.infrastructure.Application;
import org.dalvarez.ddd_example.shared.infrastructure.rest_api.controller.ApiConstants;
import org.dalvarez.ddd_example.shared.infrastructure.rest_api.exception_handler.ErrorResponse;
import org.dalvarez.ddd_example.shared.infrastructure.shared.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestConfig
@CucumberContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public abstract class ApiTestCase {

    @Autowired
    protected MockMvc mockMvc;

    @Value("${server.servlet.contextPath}")
    protected String contextPath;

    private final String requestPath;

    protected ApiTestCase() {
        this.requestPath = "";
    }

    protected ApiTestCase(final String requestPath) {
        this.requestPath = requestPath;
    }

    protected static <T> String convertObjectToJsonString(final T object) throws IOException {
        return new ObjectMapper().writeValueAsString(object);
    }

    protected <RES> RES shouldGetById(final String id,
                                      final Class<RES> responseClass) throws Exception {
        return getById(
                HttpStatus.OK.value(),
                responseClass,
                id
        );
    }

    protected void shouldNotGetById(final String id) throws Exception {
        getById(
                HttpStatus.NOT_FOUND.value(),
                ErrorResponse.class,
                id
        );
    }

    @SafeVarargs
    private <V, RES> RES getById(final Integer expectedStatusCode,
                                 final Class<RES> responseClass,
                                 final V... vars) throws Exception {
        return getBy(
                expectedStatusCode,
                responseClass,
                ApiConstants.ID_PATH_VAR,
                new LinkedMultiValueMap<>(),
                vars
        );
    }

    public <RES> RES getBy(
            final String path,
            final Class<RES> responseClass) throws Exception {
        return getBy(
                HttpStatus.OK.value(),
                responseClass,
                path,
                new LinkedMultiValueMap<>(),
                Collections.emptyList()
        );
    }

    protected <RES> RES shouldGetByCriteria(final Map<String, List<String>> params,
                                            final Class<RES> responseClass) throws Exception {
        return getByCriteria(
                HttpStatus.OK.value(),
                responseClass,
                params
        );
    }

    protected void shouldNotGetByCriteriaCauseFiltersNotMatchAnything(final Map<String, List<String>> params) throws Exception {
        getByCriteria(
                HttpStatus.NOT_FOUND.value(),
                ErrorResponse.class,
                params
        );
    }

    protected void shouldNotGetByCriteriaCauseParamsAreInvalid(final Map<String, List<String>> params) throws Exception {
        getByCriteria(
                HttpStatus.BAD_REQUEST.value(),
                ErrorResponse.class,
                params
        );
    }

    private <RES> RES getByCriteria(final Integer expectedStatusCode,
                                    final Class<RES> responseClass,
                                    final Map<String, List<String>> params) throws Exception {
        return getBy(
                expectedStatusCode,
                responseClass,
                ApiConstants.SEARCH_PATH,
                params
        );
    }

    @SafeVarargs
    private <V, RES> RES getBy(final Integer expectedStatusCode,
                               final Class<RES> responseClass,
                               final String endpoint,
                               final Map<String, List<String>> params,
                               final V... vars) throws Exception {
        final AtomicReference<RES> response = new AtomicReference<>();

        mockMvc.perform(get(buildPath(endpoint), vars)
                                .queryParams(new LinkedMultiValueMap<>(params))
                                .contextPath(contextPath))
               .andExpect(status().is(expectedStatusCode))
               .andDo(mvcResult -> {
                   if (!mvcResult.getResponse().getContentAsString().equals(""))
                       response.set(convertJsonStringToObject(mvcResult, responseClass));
               });

        return response.get();
    }

    protected <RES, REQ> RES shouldPost(final REQ request,
                                        final Class<RES> responseClass) throws Exception {
        return post(
                request,
                HttpStatus.CREATED.value(),
                responseClass
        );
    }

    private <RES, REQ> RES post(final REQ request,
                                final Integer expectedStatusCode,
                                final Class<RES> responseClass) throws Exception {
        final AtomicReference<RES> response = new AtomicReference<>();

        mockMvc.perform(MockMvcRequestBuilders.post(buildPath())
                                              .contextPath(contextPath)
                                              .content(convertObjectToJsonString(request))
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().is(expectedStatusCode))
               .andDo(mvcResult -> response.set(convertJsonStringToObject(mvcResult, responseClass)));

        return response.get();
    }

    protected <RES, REQ> RES shouldPut(final REQ request,
                                       final Class<RES> responseClass,
                                       final String id) throws Exception {
        return put(
                request,
                HttpStatus.OK.value(),
                responseClass,
                id
        );
    }

    protected static <T> T convertJsonStringToObject(final MvcResult mvcResult,
                                                     final Class<T> objectClass) throws IOException {
        final String json = mvcResult.getResponse()
                                     .getContentAsString();

        return convertJsonStringToObject(json, objectClass);
    }

    protected static <T> T convertJsonStringToObject(final String json,
                                                     final Class<T> objectClass) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        final JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.readValue(json, objectClass);
    }

    @SafeVarargs
    private <RES, REQ, V> RES put(final REQ request,
                                  final Integer expectedStatusCode,
                                  final Class<RES> responseClass,
                                  final V... vars) throws Exception {
        final AtomicReference<RES> response = new AtomicReference<>();

        mockMvc.perform(MockMvcRequestBuilders.put(buildPath(ApiConstants.ID_PATH_VAR), vars)
                                              .contextPath(contextPath)
                                              .content(convertObjectToJsonString(request))
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().is(expectedStatusCode))
               .andDo(mvcResult -> response.set(convertJsonStringToObject(mvcResult, responseClass)));

        return response.get();
    }

    protected void shouldDeleteById(final String id) throws Exception {
        shouldDeleteById(
                HttpStatus.NO_CONTENT.value(),
                id
        );
    }

    private void shouldDeleteById(final Integer expectedStatusCode,
                                  final String id) throws Exception {
        deleteBy(
                expectedStatusCode,
                ApiConstants.ID_PATH_VAR,
                id
        );
    }

    @SafeVarargs
    private <V> void deleteBy(final Integer expectedStatusCode,
                              final String endpoint,
                              final V... vars) throws Exception {
        mockMvc.perform(delete(buildPath(endpoint), vars)
                                .contextPath(contextPath))
               .andExpect(status().is(expectedStatusCode));
    }

    protected String buildPath(final String... paths) {
        return contextPath + requestPath + String.join("", paths);
    }

    protected <REQ> void shouldNotPutCauseNotExist(final REQ request,
                                                   final String id) throws Exception {
        put(
                request,
                HttpStatus.NOT_FOUND.value(),
                ErrorResponse.class,
                id
        );
    }

}
