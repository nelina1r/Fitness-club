package ru.t1.dedov.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ru.t1.dedov.model.entity.enums.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE card SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Table(name = "client")
public class Client extends User implements Serializable {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "passport", unique = true)
    private String passport;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "home_address", nullable = false)
    private String homeAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "phone_number", length = 14, nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    @ToString.Exclude
    private Set<Card> cardSet;

    @ManyToMany(mappedBy = "clientSet")
    @JsonBackReference
    @ToString.Exclude
    private Set<Schedule> scheduleList;

    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return getId() != null && Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
