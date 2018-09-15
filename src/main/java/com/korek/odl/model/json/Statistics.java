package com.korek.odl.model.json;

public class Statistics {

    private Bytes bytes;

    public Statistics() {
    }

    public Bytes getBytes() {
        return bytes;
    }

    public void setBytes(Bytes bytes) {
        this.bytes = bytes;
    }

    public  class Bytes {
        private String received;
        private String transmitted;

        public Bytes() {
        }

        public String getReceived() {
            return received;
        }

        public void setReceived(String received) {
            this.received = received;
        }

        public String getTransmitted() {
            return transmitted;
        }

        public void setTransmitted(String transmitted) {
            this.transmitted = transmitted;
        }
    }

}
