package com.korek.odl.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.korek.odl.model.json.Node;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nodes {

   ArrayList<Node> node;

    public Nodes(){}
    public Nodes(ArrayList<Node> node) {
        this.node = node;
    }

    public ArrayList<Node> getNode() {
        return node;
    }

    public void setNodes(ArrayList<Node> node) {
        this.node = node;
    }
}
