package resources;

import java.util.HashMap;
import java.util.Map;

public class DataRequest {

    public Map<String, String> getPayloads() {
        Map<String, String> payloads = new HashMap<>();

        payloads.put("addItem", "{\n" +
                "   \"name\": \"Poco F6\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2024,\n" +
                "      \"price\": 5500000,\n" +
                "      \"CPU model\": \"Snapdragon 8s Gen 3\",\n" +
                "      \"Hard disk size\": \"512 GB\"\n" +
                "   }\n" +
                "}");

        payloads.put("addItem2", "{\n" +
                "   \"name\": \"iPhone 15\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2023,\n" +
                "      \"price\": 15000000,\n" +
                "      \"CPU model\": \"A16 Bionic\",\n" +
                "      \"Hard disk size\": \"256 GB\"\n" +
                "   }\n" +
                "}");

        return payloads;
    }
}