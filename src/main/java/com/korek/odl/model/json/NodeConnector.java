package com.korek.odl.model.json;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeConnector {

    private String id;
    @JsonProperty(value = "flow-node-inventory:state")
    private InventoryState inventoryState;
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

    public InventoryState getInventoryState() {
        return inventoryState;
    }

    public void setInventoryState(InventoryState inventoryState) {
        this.inventoryState = inventoryState;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
