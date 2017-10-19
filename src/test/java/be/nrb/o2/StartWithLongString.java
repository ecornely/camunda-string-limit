package be.nrb.o2;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class StartWithLongString {

  private static final ObjectMapper om = new ObjectMapper();
  
  public static void main(String[] args) throws Exception {
    ObjectNode data = om.createObjectNode();
    data.put("businessKey", "Test with a long String : "+ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
    ObjectNode variables = data.putObject("variables");
    String stringLimit = generateRandomString(4000);
    variables.putObject("stringLimit").put("value", stringLimit).put("type", "String");
    JerseyClient client = JerseyClientBuilder.createClient(); 
    String uri = "http://localhost:8080/engine-rest/process-definition/key/Process/start";
    Response response = client
        .target(uri)
        .request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(data));
    System.out.printf("Sent request to %s with data %s%n", uri, om.writeValueAsString(data));
    String responseText = response.readEntity(String.class);
    response.close();
    System.out.println(responseText);
  }

  private static String generateRandomString(int length) {
    Random r = new Random();
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = (char)(r.nextInt(26)+65);
      if(i%80==0 && i>0) {
        c='\n';
      }
      sb.append(c);
    }
    return sb.toString();
  }

}
