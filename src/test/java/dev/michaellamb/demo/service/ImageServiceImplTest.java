package dev.michaellamb.demo.service;

import dev.michaellamb.demo.service.impl.ImageServiceImpl;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTest {

    @InjectMocks
    private ImageServiceImpl imageServiceImpl;

    @Mock
    private RestTemplate restTemplate;

    private static final String testImageFileName = "blue-hero-3.svg";

    /**
     * This test case demonstrates how an SVG url/image can be transformed to JPEG.
     * Since we also don't want to store the generated files in our application, we will be deleting the
     * files at the end of this Junit test.
     * */
    @Test
    void testSvgToJpeg_Successful() throws Exception{
        String testSvgUri = "https://freesvg.org/blue-superhero";
        InputStream inputStream = new ClassPathResource(testImageFileName).getInputStream();
        byte[] data = IOUtils.toByteArray(inputStream);
        when(restTemplate.getForObject(testSvgUri, byte[].class)).thenReturn(data);
        String generatedJpegFile = imageServiceImpl.saveAsJpeg(testSvgUri);
        System.out.println(generatedJpegFile);
        assertTrue((StringUtils.isNotBlank(generatedJpegFile) && (generatedJpegFile.contains("output"))));
        String absPath = new FileSystemResource("").getFile().getAbsolutePath() + "/" + generatedJpegFile;
        imageServiceImpl.deleteImageFile(absPath);
        File imageFile = new File(absPath);
        assertTrue(!imageFile.exists());
    }

    /**
     * This test case will verify if the Service method throws valid exception
     * when an invalid content is passed as svg input to the method.
     * */
    @Test
    void testSvgToJpeg_Failure_Content_Not_Allowed() throws Exception{
        Throwable exception = assertThrows(Exception.class, () -> {
            String testSvgUri = "https://example.svg.com";
            byte[] data = new byte[10];
            when(restTemplate.getForObject(testSvgUri, byte[].class)).thenReturn(data);
            String generatedJpegFile = imageServiceImpl.saveAsJpeg(testSvgUri);
            System.out.println(generatedJpegFile);
        });
        assertEquals("Could not transcode", exception.getMessage());
    }

    /**
     * This test case demonstrates how an SVG url/image can be transformed to PNG.
     * */
    @Test
    void testSvgToPng_Successful() throws Exception{
        String testSvgUri = "https://freesvg.org/blue-superhero";
        InputStream inputStream = new ClassPathResource(testImageFileName).getInputStream();
        byte[] data = IOUtils.toByteArray(inputStream);
        when(restTemplate.getForObject(testSvgUri, byte[].class)).thenReturn(data);
        String generatedPngFile = imageServiceImpl.saveAsPng(testSvgUri);
        System.out.println(generatedPngFile);
        assertTrue((StringUtils.isNotBlank(generatedPngFile) && (generatedPngFile.contains("output"))));
        String absPath = new FileSystemResource("").getFile().getAbsolutePath() + "/" + generatedPngFile;
        imageServiceImpl.deleteImageFile(absPath);
        File imageFile = new File(absPath);
        assertTrue(!imageFile.exists());
    }

    /**
     * This test case will verify if the Service method throws valid exception
     * when an invalid content is passed as svg input to the method.
     * */
    @Test
    void testSvgToPng_Failure_Content_Not_Allowed() throws Exception{
        Throwable exception = assertThrows(Exception.class, () -> {
            String testSvgUri = "https://example.svg.com";
            byte[] data = new byte[10];
            when(restTemplate.getForObject(testSvgUri, byte[].class)).thenReturn(data);
            String generatedPngFile = imageServiceImpl.saveAsPng(testSvgUri);
            System.out.println(generatedPngFile);
        });
        assertEquals("Could not transcode", exception.getMessage());
    }

    /**
     * This test case demonstrates how a converted JPEG/PNG image can be retrieved successfully.
     * */
    @Test
    void testGetImageFile_Successful() throws Exception{
        File file = ResourceUtils.getFile(this.getClass().getResource("/blue-hero-3.svg"));
        byte[] imageByteArray = imageServiceImpl.getImageFile(file.getPath());
        assertNotNull(imageByteArray);
    }

    /**
     * This test case demonstrates how a getImageFile method will throw exception when a non-existing
     * filename is passed as input parameter.
     * */
    @Test
    void testGetImageFile_Failure_FileNotFound() throws Exception{
        Throwable exception = assertThrows(Exception.class, () -> {
            imageServiceImpl.getImageFile("invalid.jpeg");
        });
        System.out.println(exception.getMessage());
        assertEquals("java.io.FileNotFoundException: invalid.jpeg (The system cannot find the file specified)", exception.getMessage());
    }

    /**
     * This test case demonstrates how a deleteImageFile method will throw exception when a non-existing
     * filename is passed as input parameter.
     * */
    @Test
    void testDeleteImageFile_Failure_FileNotFound() throws Exception{
        Throwable exception = assertThrows(Exception.class, () -> {
            imageServiceImpl.deleteImageFile("invalid.jpeg");
        });
        System.out.println(exception.getMessage());
        assertEquals("java.io.FileNotFoundException: invalid.jpeg (The system cannot find the file specified)", exception.getMessage());
    }
}
