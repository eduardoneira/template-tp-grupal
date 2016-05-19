package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.model.GameBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

// TODO: this class should be part of the server
public class BuilderLoader {

    private static String escape(String name) {
        if (name.contains("/")) {
            name = name.replaceAll("/", ".");
        }
        if (name.contains("\\")) {
            name = name.replaceAll("\\\\", ".");
        }
        return name;
    }

    private static String processEntry(JarEntry entry) {
        String name = entry.getName();
        return escape(name.substring(0,name.lastIndexOf(".class")));
    }

    private static List<String> scanJar(File file) throws IOException, IllegalArgumentException {
        if (file == null || !file.exists() || !file.getName().endsWith(".jar")) {
            throw new IllegalArgumentException("file");
        }

        List<String> foundClasses = new ArrayList<>();
        try (JarFile jarFile = new JarFile(file)) {
            jarFile.stream()
                    .filter(e -> e.getName().endsWith(".class"))
                    .forEach(e -> foundClasses.add(processEntry(e)));
        }
        return foundClasses;
    }

    public static GameBuilder load(String filePath)
            throws ClassNotFoundException, IOException,
            IllegalAccessException, InstantiationException {
        File file = new File(filePath);
        System.out.print("5");
        URL[] urls = { new URL("jar:file:" + filePath + "!/") };
        System.out.print("6");
        ClassLoader loader = URLClassLoader.newInstance(urls);
        System.out.print("7");
        int aux=8;

        for (String classFile : scanJar(file)) {
            Class<?> foundClass;
            if (loader == null) {
                System.out.print("arriba"+aux);
                foundClass = Class.forName(classFile);
            } else {
                System.out.print("abajo"+aux);
                foundClass = Class.forName(classFile, true, loader);
                System.out.print("abajobis"+aux);
            }
            aux++;
            if (GameBuilder.class.isAssignableFrom(foundClass) && !foundClass.equals(GameBuilder.class)) {
                return (GameBuilder)foundClass.newInstance();
            }
        }
        return null;
    }
}
