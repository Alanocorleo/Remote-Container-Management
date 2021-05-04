package serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import management.Journey;

/**
 * JourneyDeserializer class is used to deserialize the journey database.
 * It extends KeyDeserializer to override deserializeKey to give specific 
 * instructions.
 */

public class JourneyDeserializer extends KeyDeserializer {
	
	/**
	 * This method returns a journey by deserializing a journey string form the 
	 * database to a journey object with specific instructions.
	 */
    @Override
    public Journey deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // Use the string key here to return a real map key object
        return new Journey(key);
    }
	

}
