package dev.michaellamb.demo.service.impl;

import dev.michaellamb.demo.service.ImageService;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ImageServiceImpl implements ImageService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final AtomicLong counter = new AtomicLong();

    @Resource
    private RestTemplate restTemplate;

    @Value("#{'${dev.michaellamb.allowlist}'}")
    private List<String> allowList;
    
    private boolean isValidUrl(String url) {
        LOGGER.info("isValidUrl check: url supplied [{}], urls allowed {}", url, allowList);
        final boolean result = allowList.stream().anyMatch(u -> url.contains(u));
        return result;
    }

    @Override
    public String saveAsJpeg(String svgUri) throws Exception {
        String fileName;

        final String decodedSvgUri = URLDecoder.decode(svgUri, StandardCharsets.UTF_8.name());

        if (isValidUrl(decodedSvgUri)) {
            LOGGER.info("About to download and convert image from svgUrl {}", decodedSvgUri);

            // Create a JPEG transcoder
            final JPEGTranscoder transcoder = new JPEGTranscoder();
    
            // Set the transcoding hints.
            transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(1.0));
    
            // Create the transcoder input.
            final byte[] data = restTemplate.getForObject(decodedSvgUri, byte[].class);
            final InputStream istream = new ByteArrayInputStream(data);
            final TranscoderInput input = new TranscoderInput(istream);
    
            // Create the transcoder output.
            fileName = "output-" + counter.incrementAndGet() + ".jpeg";
            final OutputStream ostream = new FileOutputStream(fileName);
            final TranscoderOutput output = new TranscoderOutput(ostream);
    
            // Save the image.
            try {
                transcoder.transcode(input, output);
            } catch (TranscoderException te) {
                LOGGER.error("Error during transcode.", te);
                if(null != istream){
                    istream.close();
                }
                
                if(null != ostream){
                    ostream.flush();
                    ostream.close();
                }
            }finally {
                // Flush and close the stream.
                if(null != istream){
                    istream.close();
                }
                
                if(null != ostream){
                    ostream.flush();
                    ostream.close();
                }
            }
        } else {
            throw new Exception("Supplied URL not on allowlist. Aborted transcode...");
        }

        return fileName;
    }

    /**
     * This method shows how to transform an SVG document to a PNG image.
     *
     * @param svgUri - SVG document URI
     * @return converted PNG image.
     * */
    @Override
    public String saveAsPng(String svgUri) throws Exception {
        String fileName;

        final String decodedSvgUri = URLDecoder.decode(svgUri, StandardCharsets.UTF_8.name());

        if (isValidUrl(decodedSvgUri)) {

            LOGGER.info("About to download and convert image from svgUrl {}", decodedSvgUri);

            //Create a PNG transcoder
            final PNGTranscoder pngTranscoder = new PNGTranscoder();
    
            // Set the transcoding hints.
            pngTranscoder.addTranscodingHint(PNGTranscoder.KEY_GAMMA, new Float(1.0));
            
            //Create the PNG transcoder input
            final byte[] data = restTemplate.getForObject(decodedSvgUri, byte[].class);
            final InputStream istream = new ByteArrayInputStream(data);
            final TranscoderInput input = new TranscoderInput(istream);
    
            // Create the transcoder output.
            fileName = "output-" + counter.incrementAndGet() + ".png";
            final OutputStream ostream = new FileOutputStream(fileName);
            final TranscoderOutput output = new TranscoderOutput(ostream);
    
            // Save the image.
            try {
                pngTranscoder.transcode(input, output);
            } catch (TranscoderException te) {
                LOGGER.error("Error during transcode.", te);
                if(null != istream){
                    istream.close();
                }
                if(null != ostream){
                    ostream.flush();
                    ostream.close();
                }
        }finally {
                // Flush and close the stream.
                if(null != istream){
                    istream.close();
                }
                if(null != ostream){
                    ostream.flush();
                    ostream.close();
                }
            }
        } else {
            throw new Exception("Supplied URL not on allowlist. Aborted transcode...");
        }
        

        return fileName;
    }

    @Override
    public byte[] getImageFile(String fileName) throws IOException {
        try {
            InputStream inputStream = new FileInputStream(fileName);
            byte[] inputStreamArray = IOUtils.toByteArray(inputStream);
            inputStream.close();
            return inputStreamArray;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public void deleteImageFile(String fileName) throws Exception {
        try {
            File imageFile = new File(fileName);
            if(imageFile.exists()){
                imageFile.delete();
                LOGGER.info("File named " + fileName + " deleted successfully.");
                return;
            }else{
                throw new FileNotFoundException(fileName + " (The system cannot find the file specified)");
            }
        } catch (Exception e) {
            throw new Exception(e);
        } 
    }
}
