/*
Kristopher Kuenning
10/12/2025
CSD420
Module 11: Programming Assignment (Examples)
 */

//Creating an Object Model from JSON data in a text file

import java.io.FileReader;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;

JsonReader reader = Json.createReader(new FileReader("jsondata.txt"));
JsonStructure jsonst = reader.read();

//Creating an Object Model from Application Code

import javax.json.Json;
import javax.json.JsonObject;
...
JsonObject model = Json.createObjectBuilder()
        .add("firstName", "Duke")
        .add("lastName", "Java")
        .add("age", 18)
        .add("streetAddress", "100 Internet Dr")
        .add("city", "JavaTown")
        .add("state", "JA")
        .add("postalCode", "12345")
        .add("phoneNumbers", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("type", "mobile")
                        .add("number", "111-111-1111"))
                .add(Json.createObjectBuilder()
                        .add("type", "home")
                        .add("number", "222-222-2222")))
        .build();

