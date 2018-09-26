package com.korek.odl.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.korek.odl.model.json.Node;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nodes {

    Node nodes;

    public Nodes(){}
    public Nodes(Node nodes) {
        this.nodes = nodes;
    }

    public Node getNodes() {
        return nodes;
    }

    public void setNodes(Node nodes) {
        this.nodes = nodes;
    }
}
