package com.korek.odl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class TrafficVolume {

    @Id
    @GeneratedValue
    private Long id;
    private String node;
    private String port;
    private Long bytesIn;
    private Long bytesOut;
}
