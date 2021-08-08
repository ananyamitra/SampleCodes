import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


String jsonString = "";
JsonObject jsonObject = new JsonObject()
try {
      JsonParser parser = new JsonParser();
      JsonElement jsonElement = parser.parse(new FileReader(fileName));
      jsonObject = jsonElement.getAsJsonObject();
      jsonString = jsonObject.toString();
    } 
catch (FileNotFoundException e) {
           
 } 
catch (IOException ioe){
        
}
       

   

