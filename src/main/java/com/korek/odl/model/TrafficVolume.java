package com.korek.odl.model;

import com.korek.odl.model.json.ChartData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table
public class TrafficVolume {

    @Id
    @GeneratedValue
    private Long id;
    private String node;
    private String iface;
    private Long bytesVolume;
    private String trafficType;
    private LocalDateTime timestamp;

    public TrafficVolume() {}

    public Long getId() {
        return id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIface() {
        return iface;
    }

    public void setIface(String iface) {
        this.iface = iface;
    }

    public Long getBytesVolume() {
        return bytesVolume;
    }

    public void setBytesVolume(Long bytesVolume) {
        this.bytesVolume = bytesVolume;
    }

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static ChartData[] convertToChartData(List<TrafficVolume> allByPort) {
        ChartData[] chartDataOut = new ChartData[allByPort.size()];
        int i = 0;
        for (TrafficVolume t : allByPort) {
            ZonedDateTime zdt = t.getTimestamp().atZone(ZoneId.of("GMT+02:00"));
            chartDataOut[i] = new ChartData(zdt.toInstant().toEpochMilli(), t.getBytesVolume());
            i++;
        }
        return chartDataOut;
    }
}
