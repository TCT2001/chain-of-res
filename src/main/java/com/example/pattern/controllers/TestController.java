package com.example.pattern.controllers;

import com.example.pattern.PatternApplication;
import com.example.pattern.actions.CaAction;
import com.example.pattern.actions.register.CloudStrategy;
import com.example.pattern.actions.register.CustomerCaAction;
import com.example.pattern.actions.register.RequestCaAction;
import com.example.pattern.factory.CaActionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/test")
public class TestController {
    Logger logger = LoggerFactory.getLogger("KPI");

    @GetMapping("/1")
    public String test2() {
        logger.info("123");
        logger.debug("111111111111111");
        logger.warn("2222222222222222");
        logger.error("333333333333333");
        return "123";
    }

    @GetMapping
    public String test1() {
        CaAction action = CaActionFactory.getCaAction("1");
        if (action == null) {
            return "Error null";
        }

        final CloudStrategy cloudStrategy = new CloudStrategy();
        var rqAction = new RequestCaAction(cloudStrategy);
        var ccAction = new CustomerCaAction(cloudStrategy);

        action
                .nextHandler(rqAction)
                .nextHandler(ccAction);

        action.doAction();

        return "test1";
    }

    //@GetMapping(value = "/download/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    //public ResponseEntity<StreamingResponseBody> test3(@PathVariable String fileName) throws FileNotFoundException {
    //    final HttpHeaders httpHeaders = new HttpHeaders();
    //    final File file = new File(PatternApplication.LOG_PATH + File.separator + fileName);
    //    try (InputStream inputStream = new FileInputStream(file)) {
    //        httpHeaders.set(HttpHeaders.LAST_MODIFIED, String.valueOf(file.lastModified()));
    //        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
    //        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
    //
    //        StreamingResponseBody responseBody = outputStream -> {
    //            int numberOfBytesToWrite;
    //            byte[] data = new byte[64 * 1024];
    //            while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
    //                outputStream.write(data, 0, numberOfBytesToWrite);
    //            }
    //            inputStream.close();
    //        };
    //        return ResponseEntity.ok()
    //                .headers(httpHeaders)
    //                .contentLength(file.length())
    //                .contentType(MediaType.APPLICATION_OCTET_STREAM)
    //                .body(responseBody);
    //    } catch (IOException e) {
    //        throw new RuntimeException(e);
    //    }
    //}

    //@GetMapping(value = "/download/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    //public ResponseEntity<StreamingResponseBody> downloadLog(@PathVariable String fileName) throws IOException {
    //    try (InputStream inputStream = new FileInputStream(PatternApplication.LOG_PATH + File.separator + fileName)) {
    //        StreamingResponseBody body = outputStream -> FileCopyUtils.copy(inputStream, outputStream);
    //        return ResponseEntity.ok()
    //                .header("Content-Disposition", "attachment;filename=" + fileName)
    //                .body(body);
    //    }
    //}

    @GetMapping(value = "/download/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> download(@PathVariable String fileName) throws IOException {
        final File file = new File(PatternApplication.LOG_PATH + File.separator + fileName);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.LAST_MODIFIED, String.valueOf(file.lastModified()));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/downloadLargeFile/{fileName}")
    public ResponseEntity<StreamingResponseBody> test3(@PathVariable String fileName) throws FileNotFoundException {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final File file = new File(PatternApplication.LOG_PATH + File.separator + fileName);
        final InputStream inputStream = new FileInputStream(file);
        httpHeaders.set(HttpHeaders.LAST_MODIFIED, String.valueOf(file.lastModified()));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));

        StreamingResponseBody responseBody = outputStream -> {
            int numberOfBytesToWrite;
            byte[] data = new byte[64 * 1024];
            while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, numberOfBytesToWrite);
            }
            inputStream.close();
        };

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(responseBody);
    }
}
