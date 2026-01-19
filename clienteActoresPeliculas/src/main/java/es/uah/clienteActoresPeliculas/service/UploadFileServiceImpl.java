package es.uah.clienteActoresPeliculas.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

// Funciones creadas para la administración de las imágenes que se suban
@Service
public class UploadFileServiceImpl implements IUploadFileService {

    //Se usa para ver que rutas esta generando el código
    private final Logger log = LoggerFactory.getLogger(getClass());

    //Esta será la carpeta raíz donde se guardan las fotos
    private final static String UPLOADS_FOLDER = "../uploads";

    // Crea la carpeta en el disco duro si no existe
    @Override
    public void init() throws IOException {
        //Path representa una ubicación en el sistema de archivos
        //Se usa para interpretar direcciones de carpetas
        Path path = Paths.get(UPLOADS_FOLDER);
        // Solo crea la carpeta si no existe
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    //Ayuda a leer una imagen para mostrarla
    //Resource es el paquete físico del Path
    @Override
    public Resource load(String filename) throws MalformedURLException {
        //Saca la ubicación del archivo
        Path pathFoto = getPath(filename);
        //La muestra por consola
        log.info("pathFoto: " + pathFoto);

        //Localiza el archivo y lo deja listo para ser leído
        Resource recurso = new UrlResource(pathFoto.toUri());

        //Si no existe salta un error
        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto);
        }
        //Si existe lo devuelve
        return recurso;
    }

    //Función para copiar archivos a nuestra carpeta de uploads
    @Override
    public String copy(MultipartFile file) throws IOException {
        //Crea un nombre único para el archivo
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        //Saca la dirección del archivo
        Path rootPath = getPath(uniqueFilename);

        //La muestra por pantalla
        log.info("rootPath: " + rootPath);

        //Copia el archivo en la dirección con el nombre único
        Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }

    //Función para borrar archivos
    @Override
    public boolean delete(String filename) {
        //Saca la ubicación del archivo
        Path rootPath = getPath(filename);
        //Convierte el Path en un objeto clásico para que se entienda mejor
        File archivo = rootPath.toFile();

        //Si existe lo elimina
        if (archivo.exists() && archivo.canRead()) {
            if (archivo.delete()) {
                return true;
            }
        }
        return false;
    }

    // Obtiene el nombre absoluto de la ruta del archivo
    //Añade el nombre del archivo a la URI de UPLOADS_FOLDER
    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }

    //Borra lo que contiene la carpeta de uploads
    //----- USARLA CON CUIDADO ------
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

}

