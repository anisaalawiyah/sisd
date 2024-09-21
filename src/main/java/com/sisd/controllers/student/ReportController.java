package com.sisd.controllers.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sisd.dtostudent.GenericResponse;
import com.sisd.service.student.ReportService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/student")
@Tag(name="student")
public class ReportController {
    @Autowired
    ReportService reportService;
    @GetMapping("/report")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object>report(HttpServletResponse response){
        try {
            response.setHeader("Content - Disposition","attachment: filename=repost.xlsx");
            return ResponseEntity.ok(reportService.generateReport());
        }
        catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.internalServerError()
                .body(GenericResponse.eror(MassageConstant.ERROR_500));
        }
    }
}