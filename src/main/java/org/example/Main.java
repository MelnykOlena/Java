package org.example;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.InputStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            // Отримати ресурс як InputStream
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("spaces.json");
            // Переконатися, що файл існує
            if (inputStream == null) {
                throw new FileNotFoundException("Файл не знайдено: spaces.json");
            }
            // Читати JSON з InputStream
            JSONObject jsonData = (JSONObject) parser.parse(new InputStreamReader(inputStream));

            // Отримуємо масив "folders" з jsonData
            JSONArray folders = (JSONArray) jsonData.get("folders");

            // Виводимо space.name і space.id для lists, ім'я яких починається на "test"
            for (Object folderObj : folders) {
                JSONObject folder = (JSONObject) folderObj;
                JSONArray lists = (JSONArray) folder.get("lists");

                for (Object listObj : lists) {
                    JSONObject list = (JSONObject) listObj;
                    String name = (String) list.get("name");

                    // Виводимо space.name і space.id для lists, ім'я яких починається на "test"
                    if (name != null && name.startsWith("test")) {
                        JSONObject space = (JSONObject) list.get("space");
                        System.out.println("Space Name: " + space.get("name"));
                        System.out.println("Space ID: " + space.get("id"));
                    }

                    // Виводимо ID для всіх списків
                    System.out.println("List ID: " + list.get("id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
