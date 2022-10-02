package dev.michaellamb.demo.agent.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.michaellamb.demo.agent.ImageAgent;
import dev.michaellamb.demo.service.ImageService;

@Service
public class ImageAgentImpl implements ImageAgent {

    private static Logger LOGGER = LoggerFactory.getLogger(ImageAgentImpl.class);

    @Resource
    private ImageService imageService;
    
    @Override
    public byte[] exchangeSvgUriForJpeg(String svgUri) {
        byte[] response = null;
        try {
            final String fileName = imageService.saveAsJpeg(svgUri);
            response = imageService.getJpegFile(fileName);
            imageService.deleteJpegFile(fileName);
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred.", e);
        }
        return response;
    }

}
