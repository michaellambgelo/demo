package dev.michaellamb.demo.agent;

public interface ImageAgent {
    public byte[] exchangeSvgUriForJpeg(String svgUri);
    public byte[] exchangeSvgUriForPng(String svgUri);
}
