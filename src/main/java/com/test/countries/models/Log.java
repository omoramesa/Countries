    package com.test.countries.models;


    import javax.persistence.*;
    import java.time.LocalDateTime;

    @Entity
    @Table(name = "log")
    public class Log {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String country;
        private String url;
        private LocalDateTime timestamp;

        // Constructor vacío
        public Log() {
        }

        // Constructor con argumentos
        public Log(String country, String url, LocalDateTime timestamp) {
            this.country = country;
            this.url = url;
            this.timestamp = timestamp;
        }

        // Getters y setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }
