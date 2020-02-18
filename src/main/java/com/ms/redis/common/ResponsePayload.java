package com.ms.redis.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Common response payload
 * @author Eshan
 */

@Data
@ToString
public class ResponsePayload {

    private static Logger LOGGER = LogManager.getLogger(ResponsePayload.class.getSimpleName());

    @JsonProperty("items")
    private List<?> items;

    @JsonProperty("object")
    private Object object;

    @JsonProperty("message")
    private String message;

    @JsonProperty("httpCode")
    private Integer httpCode;

    public ResponsePayload(List<?> items, Object object, String message, Integer httpCode) {
        this.items = items;
        this.object = object;
        this.message = message;
        this.httpCode = httpCode;
    }

    @Data
    @ToString
    public static class Builder {

        private List<?> items;
        private Object object;
        private String message;

        private StringBuilder chainLog;
        private int httpCode;

        public Builder(HttpStatus httpSt, StringBuilder chainLog) {
            setHttpCode(httpSt.value());
            setChainLog(chainLog);
        }

        public Builder addPayload(List<?> items) {
            setItems(items);
            return this;
        }

        public Builder addPayload(Object object) {
            setObject(object);
            return this;
        }

        public Builder addPayload(String message) {
            setMessage(message);
            return this;
        }

        public ResponsePayload build() {

            ResponsePayload responsePayload = new ResponsePayload(this.items, this.object, this.message, this.httpCode);
            chainLog.append("|FinalResponse:").append(responsePayload.toString());
            LOGGER.info(chainLog.toString());
            return responsePayload;
        }

    }
}
