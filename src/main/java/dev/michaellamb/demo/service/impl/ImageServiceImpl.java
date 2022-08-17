package dev.michaellamb.demo.service.impl;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.michaellamb.demo.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final AtomicLong counter = new AtomicLong();

    @Resource
    private RestTemplate restTemplate;

    @Override
    public String saveAsJpeg(String svgUri) throws Exception {
        // Create a JPEG transcoder
        final JPEGTranscoder transcoder = new JPEGTranscoder();

        // Set the transcoding hints.
        transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(1.0));

        // Create the transcoder input.
        final String decodedSvgUri = URLDecoder.decode(svgUri, StandardCharsets.UTF_8.name());
        LOGGER.info("About to download and convert image from svgUrl {}", decodedSvgUri);
        final byte[] data = restTemplate.getForObject(decodedSvgUri, byte[].class);
        final InputStream istream = new ByteArrayInputStream(data);
        final TranscoderInput input = new TranscoderInput(istream);

        // Create the transcoder output.
        final String fileName = "output-" + counter.incrementAndGet() + ".jpeg";
        final OutputStream ostream = new FileOutputStream(fileName);
        final TranscoderOutput output = new TranscoderOutput(ostream);

        // Save the image.
        try {
            transcoder.transcode(input, output);
        } catch (TranscoderException te) {
            LOGGER.error("Error during transcode.", te);
            throw new Exception("Could not transcode");
        }

        // Flush and close the stream.
        ostream.flush();
        ostream.close();

        return fileName;
    }

    @Override
    public byte[] getJpegFile(String fileName) throws IOException {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            return IOUtils.toByteArray(inputStream);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
