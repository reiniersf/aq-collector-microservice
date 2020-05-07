package org.alterbg.musala.aq.api;

import com.fasterxml.jackson.databind.JsonNode;

public interface DataTransformer <O extends DataLog>{
    O toDataLog(JsonNode rawData);
}
