package io.esoma.cbj.pe;

import org.tinylog.Logger;

/**
 * A program that listens to many server hosts for request metrics, aggregates them, and reports the
 * time series data to a central cluster. Individual hosts' data is retrieved via file abstraction
 * (streamed). The remote central host is communicated to via an open socket for simulation
 * purposes. Each server's metrics is formatted as follows:
 *
 * <p>hostname,api,status,requestBytes,responseBytes,timestamp<br>
 * host1,/login,200,50,600,1727481264<br>
 * host2,/login,200,50,600,1727481265<br>
 * host3,/login,200,50,600,1727481266<br>
 * host1,/getAccountName,200,50,600,1727481263<br>
 * ...
 *
 * <p>It is NOT guaranteed that records are received in precise order. Aggregated data is reported
 * every a few seconds.
 */
public class HostLogReporter {

    public static void main(String[] args) {
        Logger.info("HostLogReporter works!");
    }
}
