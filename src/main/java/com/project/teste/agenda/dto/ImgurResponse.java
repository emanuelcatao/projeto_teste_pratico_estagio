package com.project.teste.agenda.dto;

import lombok.Getter;

@Getter
public class ImgurResponse {
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    @Getter
    public static class Data {
        private String link;

        public void setLink(String link) {
            this.link = link;
        }
    }
}
