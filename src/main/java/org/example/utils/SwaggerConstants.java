package org.example.utils;

public class SwaggerConstants {

    public static final String MESSAGE_BY_ID = "{\"id\" : 2, \"nombre\" : \"Barcelona FC\", \"liga\" : \"La Liga\", \"pais\" : \"España\"}\n";
    public static final String MESSAGE_LIST = "[{\"id\" : 2, \"nombre\" : \"FC Barcelona\", \"liga\" : \"La Liga\", \"pais\" : \"España\"}," +
            "{\"id\" : 3, \"nombre\" : \"Manchester United\", \"liga\" : \"Premier League\", \"pais\" : \"Inglaterra\"}\n]";
    public static final String MESSAGE_LIST_BY_NAME = "[{\"id\" : 2, \"nombre\" : \"FC Barcelona\", \"liga\" : \"La Liga\", \"pais\" : \"España\"}," +
            "{\"id\" : 4, \"nombre\" : \"Liverpool FC\", \"liga\" : \"Premier League\", \"pais\" : \"Inglaterra\"}\n]";
    public static final String MESSAGE_ERROR_400 = "{\n" + "  \"mensaje\": \"La solicitud es invalida\",\n" + "  \"codigo\": 400\n" + "}";
    public static final String MESSAGE_ERROR_404 = "{\n" + "  \"mensaje\": \"Equipo no encontrado\",\n" + "  \"codigo\": 404\n" + "}";
    public static final String MESSAGE_CREATE = "{\"id\" : 25, \"nombre\" : \"Nuevo Equipo FC\", \"liga\" : \"Nueva Liga\", \"pais\" : \"Nuevo País\"}\n";
    public static final String MESSAGE_UPDATE = "{\"id\" : 25, \"nombre\" : \"Nuevo Equipo FC\", \"liga\" : \"Nueva Liga\", \"pais\" : \"Nuevo País\"}\n";
}