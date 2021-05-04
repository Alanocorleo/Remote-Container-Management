package serialization;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import management.Journey;

/**
 * JourneySerializer class is used to serialize a journey object.
 * It extends JsonSerializer<Journey> to override serialize to 
 * give specific instructions.
 */

public class JourneySerializer extends JsonSerializer<Journey> {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * This method serializes a journey from a journey object to a journey string
	 * with specific instructions.
	 */
	@Override
	public void serialize(Journey value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, value);
		gen.writeFieldName(writer.toString());	
	}

}
