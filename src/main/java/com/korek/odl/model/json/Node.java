package com.korek.odl.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Node {

    private String id;
    @JsonProperty(value = "node-connector")
    private List<NodeConnector> nodeConnectorList;

    public Node(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<NodeConnector> getNodeConnectorList() {
        return nodeConnectorList;
    }

    public void setNodeConnectorList(List<NodeConnector> nodeConnectorList) {
        this.nodeConnectorList = nodeConnectorList;
    }
}
