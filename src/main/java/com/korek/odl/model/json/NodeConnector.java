package com.korek.odl.model.json;


import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeConnector {

    private String id;
    @JsonProperty(value = "opendaylight-port-statistics:flow-capable-node-connector-statistics")
    private Statistics statistics;
    public NodeConnector() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
