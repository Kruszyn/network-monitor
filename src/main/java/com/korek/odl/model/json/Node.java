package com.korek.odl.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Node {

    @JsonProperty(value = "node")
    List<NodeBody> node;

    public Node() {
    }

    public List<NodeBody> getNodeBodies() {
        return node;
    }

    public void setNodeBodies(List<NodeBody> nodeBodies) {
        this.node = nodeBodies;
    }
}
