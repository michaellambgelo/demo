package dev.michaellamb.demo.service;

import java.io.IOException;

public interface ImageService {
    public String saveAsJpeg(String svgUri) throws Exception;

    public String saveAsPng(String svgUri) throws Exception;

    public byte[] getImageFile(String fileName) throws IOException;

    public void deleteImageFile(String fileName) throws Exception;

}
