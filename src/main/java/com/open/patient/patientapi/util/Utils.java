package com.open.patient.patientapi.util;

import java.io.*;

class Utils {

    private static final String WHITELIST = "java.lang.String,java.lang.Integer"; // Add classes that you expect to deserialize

    // Function to serialize an object and write it to a file
    public static void serializeToFile(Object obj, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            // Check if the class of the object is whitelisted
            if (!isWhitelisted(obj.getClass())) {
                throw new IOException("Invalid class type for serialization");
            }
            // Serialization of the object to file
            System.out.println("Serializing " + obj.toString() + " to " + filename);
            out.writeObject(obj);
        } catch (IOException e) {
            throw new IOException("Error serializing object: " + e.getMessage());
        }
    }

    // Function to deserialize an object from a file
    public static Object deserializeFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            // Deserialization of the object from file
            System.out.println("Deserializing from " + filename);
            Object obj = in.readObject();
            // Check if the class of the object is whitelisted
            if (!isWhitelisted(obj.getClass())) {
                throw new ClassNotFoundException("Invalid class type for deserialization");
            }
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Error deserializing object: " + e.getMessage());
        }
    }

    // Function to check if a class is whitelisted
    private static boolean isWhitelisted(Class<?> clazz) {
        for (String allowedClass : WHITELIST.split(",")) {
            if (clazz.getName().equals(allowedClass)) {
                return true;
            }
        }
        return false;
    }
}

