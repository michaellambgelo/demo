package dev.michaellamb.demo.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.michaellamb.demo.agent.ImageAgent;

@Controller
public class ImageController {

    public static Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Resource
    private ImageAgent imageAgent;

    @GetMapping("/svg-to-jpg") 
    public ResponseEntity<byte[]> getSvgToJpg(@RequestParam("svgUri") String svgUri) {
        return ResponseEntity.ok()
        .contentType(MediaType.IMAGE_JPEG)
        .body(imageAgent.exchangeSvgUriForJpeg(svgUri));
    }

    @GetMapping("/svg-to-png")
    public ResponseEntity<byte[]> getSvgToPng(@RequestParam("svgUri") String svgUri) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageAgent.exchangeSvgUriForPng(svgUri));
    }

    //This endpoint is currently in progress.
   /* @GetMapping("/svg-to-tiff")
    public ResponseEntity<byte[]> getSvgToTiff(@RequestParam("svgUri") String svgUri) {
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/tiff"))
                .body(imageAgent.exchangeSvgUriForTiff(svgUri));
    }*/
}
