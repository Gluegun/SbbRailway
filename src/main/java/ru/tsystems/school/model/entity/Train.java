package ru.tsystems.school.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "trains")
@Getter
@Setter
@NoArgsConstructor
public class Train extends AbstractPo implements Serializable {

    @Column(name = "number", nullable = false, unique = true)
    private String trainNumber;

    @Column(name = "seats_amount", nullable = false)
    private int seatsAmount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tickets",
            joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    private Set<Passenger> passengers;

    @OneToMany(mappedBy = "train")
    private Set<Schedule> schedules;

    public Train(String trainNumber, int seatsAmount) {
        this.setTrainNumber(trainNumber);
        this.setSeatsAmount(seatsAmount);
    }
}
