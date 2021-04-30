package serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import management.Journey;

public class JourneyDeserializer extends KeyDeserializer {
	
    @Override
    public Journey deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        //Use the string key here to return a real map key object
        return new Journey(key);
    }
	

}
