package com.korek.odl.model;

import com.korek.odl.model.json.ChartData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String type;
    private String timestamp;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static List<ChartData> convertToChartData(List<TrafficVolume> allByPort) {
        List<ChartData> chartDataList = new LinkedList<>();
        for (TrafficVolume t : allByPort) {
            ChartData chartData = new ChartData(t.getBytesVolume().toString(), "2017-01-01");
            chartDataList.add(chartData);
        }
        return chartDataList;
    }
}
