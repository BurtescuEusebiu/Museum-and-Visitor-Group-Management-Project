package org.example;

public class Location {
    private String county;
    private Integer sirutaCode;
    private String locality;
    private String adminUnit;
    private String address;
    private Integer latitude;
    private Integer longitude;

    private Location(LocationBuilder builder) {
        this.county = builder.county;
        this.sirutaCode = builder.sirutaCode;
        this.locality = builder.locality;
        this.adminUnit = builder.adminUnit;
        this.address = builder.address;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public static class LocationBuilder {
        private String county;
        private Integer sirutaCode;
        private String locality;
        private String adminUnit;
        private String address;
        private Integer latitude;
        private Integer longitude;

        public LocationBuilder(String county, Integer sirutaCode) {
            this.county = county;
            this.sirutaCode = sirutaCode;
        }

        public LocationBuilder setLocality(String locality) {
            this.locality = locality;
            return this;
        }

        public LocationBuilder setAdminUnit(String adminUnit) {
            this.adminUnit = adminUnit;
            return this;
        }

        public LocationBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public LocationBuilder setLatitude(Integer latitude) {
            this.latitude = latitude;
            return this;
        }

        public LocationBuilder setLongitude(Integer longitude) {
            this.longitude = longitude;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}

