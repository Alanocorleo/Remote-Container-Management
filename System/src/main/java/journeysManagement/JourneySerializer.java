package journeysManagement;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JourneySerializer extends JsonSerializer<Journey> {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void serialize(Journey value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException{

		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, value);
		gen.writeFieldName(writer.toString());
		
	}

}
