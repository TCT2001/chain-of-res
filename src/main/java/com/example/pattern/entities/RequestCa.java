package com.example.pattern.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@NamedQueries({@NamedQuery(name = "RequestCa.findById", query = "FROM RequestCa WHERE id = :id", lockMode = LockModeType.NONE)})
public class RequestCa {
    @Id
    private long id;
}
