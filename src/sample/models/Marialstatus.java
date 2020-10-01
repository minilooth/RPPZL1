package sample.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name="marialstatus")
public class Marialstatus implements Serializable {
    private int statusId;
    private String statusName;

    @OneToMany(mappedBy = "cityOfActualResidenceId", fetch = FetchType.EAGER)
    private Collection<Client> clients;

    @Id
    @Column(name = "StatusId", nullable = false)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "StatusName", nullable = true, length = 50)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marialstatus that = (Marialstatus) o;
        return statusId == that.statusId &&
                Objects.equals(statusName, that.statusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusName);
    }
}
