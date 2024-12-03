package com.maathru.backend.External.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.maathru.backend.constant.Constant.IMAGE_DIRECTORY;

public class UploadImage {
    public static String upload(MultipartFile file) throws IOException {
        makeDirectoryIfNotExist(IMAGE_DIRECTORY);

        String originalFileName = file.getOriginalFilename();
        assert originalFileName != null;

        // Check if the file is an image
        if (!isImage(file)) {
            throw new IllegalArgumentException("Uploaded file is not a valid image.");
        }

        // Add a timestamp to the file name
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String extension = FilenameUtils.getExtension(originalFileName);
        String newFileName = FilenameUtils.getBaseName(originalFileName) + "_" + timestamp + "." + extension;

        Path fileNamePath = Paths.get(IMAGE_DIRECTORY, newFileName);

        Files.write(fileNamePath, file.getBytes());
        return newFileName;
    }

    private static boolean isImage(MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes();
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes)) {
                BufferedImage image = ImageIO.read(byteArrayInputStream);
                return image != null;
            }
        } catch (IOException e) {
            return false;
        }
    }

    private static void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
