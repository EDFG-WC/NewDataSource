package com.laowang.datasource.guava.utilities;

import java.util.Calendar;
import java.util.Objects;

public class ObjectsExample {

  static class Guava {

    private final String manufacturer;
    private final String version;
    private final Calendar releaseDate;

    public Guava(String manufacturer, String version, Calendar releaseDate) {
      this.manufacturer = manufacturer;
      this.version = version;
      this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
      return "Guava{" +
          "manufacturer='" + manufacturer + '\'' +
          ", version='" + version + '\'' +
          ", releaseDate=" + releaseDate +
          '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Guava guava = (Guava) o;
      return manufacturer.equals(guava.manufacturer) &&
          version.equals(guava.version) &&
          releaseDate.equals(guava.releaseDate);
    }

    @Override
    public int hashCode() {
      return Objects.hash(manufacturer, version, releaseDate);
    }

    /*@Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Guava guava = (Guava) o;

            if (manufacturer != null ? !manufacturer.equals(guava.manufacturer) : guava.manufacturer != null)
                return false;
            if (version != null ? !version.equals(guava.version) : guava.version != null) return false;
            return releaseDate != null ? releaseDate.equals(guava.releaseDate) : guava.releaseDate == null;
        }

        @Override
        public int hashCode() {
            int result = manufacturer != null ? manufacturer.hashCode() : 0;
            result = 31 * result + (version != null ? version.hashCode() : 0);
            result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Guava{" +
                    "manufacturer='" + manufacturer + '\'' +
                    ", version='" + version + '\'' +
                    ", releaseDate=" + releaseDate +
                    '}';
        }*/
  }
}
