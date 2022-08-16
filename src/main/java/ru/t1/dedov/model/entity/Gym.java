package ru.t1.dedov.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE card SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "gym")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "gym")
    @JsonBackReference
    private Set<Schedule> scheduleList;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "people_capacity", nullable = false)
    private Integer peopleCapacity;

    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Gym gym = (Gym) o;
        return id != null && Objects.equals(id, gym.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
