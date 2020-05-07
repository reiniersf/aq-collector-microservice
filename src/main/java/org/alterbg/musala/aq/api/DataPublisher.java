package org.alterbg.musala.aq.api;

public interface DataPublisher<D extends DataLog> {
  void pushDataLog(D logEntry);
}
