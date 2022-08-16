package dev.michaellamb.demo.service;

import java.io.IOException;

public interface ImageService {
    public String saveAsJpeg(String svgUri) throws Exception;

    public byte[] getJpegFile(String fileName) throws IOException;
}
