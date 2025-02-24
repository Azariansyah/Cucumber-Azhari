package Resources;

import java.util.HashMap;
import java.util.Map;

public class DataRequest {
    public Map<String, String> getPayloads() {
        Map<String, String> payloads = new HashMap<>();

        payloads.put("addItem", "{ \"name\": \"Apple MacBook Pro 16\", \"data\": { \"year\": 2019, \"price\": 1849.99, \"CPU model\": \"Intel Core i9\", \"Hard disk size\": \"1 TB\" } }");
        payloads.put("addItem2", "{ \"name\": \"Dell XPS 13\", \"data\": { \"year\": 2021, \"price\": 1499.99, \"CPU model\": \"Intel Core i7\", \"Hard disk size\": \"512 GB\" } }");

        return payloads;
    }
}