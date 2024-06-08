package com.basic.Basic_Project_Spring.AI;

import com.basic.Basic_Project_Spring.common.exception.AIException;
import com.basic.Basic_Project_Spring.common.response.Result;
import jakarta.validation.Valid;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {

    @PostMapping
    public ResponseEntity<Result<AIResponse>> predict(
            @Valid @RequestBody AIRequest aiRequest
    ) {
        try {
            Process process = Runtime.getRuntime().exec(
                    "python test.py " + aiRequest.latitude() + " " + aiRequest.longitude() + " " + aiRequest.wave_height() + " " + aiRequest.wind_speed() + " " + aiRequest.wave_frequency()
            );
            process.waitFor();

            BufferedReader reader = new BufferedReader(new FileReader("output.txt", StandardCharsets.UTF_8));
            String result = reader.readLine();

            return ResponseEntity.ok().body(new Result<>(new AIResponse(result), 1));

        } catch (IOException | InterruptedException e) {
            throw new AIException("오류 발생");
        }
    }
}
