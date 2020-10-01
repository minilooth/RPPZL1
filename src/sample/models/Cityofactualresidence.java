package sample.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cityofactualresidence")
public class Cityofactualresidence implements Serializable {
    private int cityId;
    private String cityName;

    @OneToMany(mappedBy = "cityOfActualResidenceId", fetch = FetchType.EAGER)
    private Collection<Client> clients;

    @Id
    @Column(name = "CityId", nullable = false)
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "CityName", nullable = false, length = 50)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cityofactualresidence that = (Cityofactualresidence) o;
        return cityId == that.cityId &&
                Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityName);
    }
}
