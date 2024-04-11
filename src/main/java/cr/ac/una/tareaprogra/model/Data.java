package cr.ac.una.tareaprogra.model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
/**
 *
 * @author PC
 */
public class Data {
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final String JSON_FILE_PATH = "C:\\ProgramData\\Cooperativa\\data\\context.json";
    
}
