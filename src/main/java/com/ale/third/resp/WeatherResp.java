package com.ale.third.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class WeatherResp {
    private int status;
    private String desc;
    @JsonProperty("data")
    private DataDTO dataX;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("yesterday")
        private YesterdayDTO yesterday;
        @JsonProperty("city")
        private String city;
        @JsonProperty("forecast")
        private List<ForecastDTO> forecast;
        @JsonProperty("ganmao")
        private String ganmao;
        @JsonProperty("wendu")
        private String wendu;

        @NoArgsConstructor
        @Data
        public static class YesterdayDTO {
            @JsonProperty("date")
            private String date;
            @JsonProperty("high")
            private String high;
            @JsonProperty("fx")
            private String fx;
            @JsonProperty("low")
            private String low;
            @JsonProperty("fl")
            private String fl;
            @JsonProperty("type")
            private String type;
        }

        @NoArgsConstructor
        @Data
        public static class ForecastDTO {
            @JsonProperty("date")
            private String date;
            @JsonProperty("high")
            private String high;
            @JsonProperty("fengli")
            private String fengli;
            @JsonProperty("low")
            private String low;
            @JsonProperty("fengxiang")
            private String fengxiang;
            @JsonProperty("type")
            private String type;
        }
    }
}
