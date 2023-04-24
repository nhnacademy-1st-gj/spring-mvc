package com.academy.nhnmartcs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Getter
public class Inquiry {
    Integer inquiryId;

    String title;

    InquiryCategory category;

    String comment;

    String writeDate;

    String id;

    List<MultipartFile> file;

    boolean isAnswered;


}
