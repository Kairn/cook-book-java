package io.esoma.cbj.pe;

import org.tinylog.Logger;

/**
 * A program that listens to many server hosts for request metrics, aggregates them, and reports the
 * time series data to a central cluster. Individual hosts' data is retrieved via file abstraction
 * (streamed). The remote central host is communicated to via an open socket for simulation
 * purposes. Each server's metrics is formatted as follows:
 *
 * <p>hostname,api,status,requestBytes,responseBytes<br>
 * host1,/login,200,50,600<br>
 * host2,/login,200,50,600<br>
 * host3,/login,200,50,600<br>
 * host1,/getAccountName,200,50,600<br>
 * ...
 *
 * <p>Aggregated data is reported every a few seconds.
 */
public class HostLogReporter {

    static final String CLUSTER_1_FILE = "hosts/cluster1.csv";
    static final String CLUSTER_2_FILE = "hosts/cluster2.csv";

    public static void main(String[] args) {
        Logger.info("HostLogReporter works!");
    }
}
