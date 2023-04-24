package com.academy.nhnmartcs.controller;

import com.academy.nhnmartcs.domain.Inquiry;
import com.academy.nhnmartcs.domain.InquiryRegisterRequest;
import com.academy.nhnmartcs.domain.User;
import com.academy.nhnmartcs.exception.ValidationFailedException;
import com.academy.nhnmartcs.repository.InquiryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Slf4j
@Controller
@RequestMapping("/cs")
public class InquiryController {
    InquiryRepository inquiryRepository;

    public InquiryController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @ModelAttribute
    public User getUser(HttpServletRequest req) {
        return (User) req.getSession().getAttribute("LoginUser");
    }

    @GetMapping()
    public String getCustomerInquiryList(User user, Model model) {

        model.addAttribute("inquiries", inquiryRepository.getUserInquiryList(user.getId()));

        return "customerInquiryList";
    }

    @GetMapping("/inquiry")
    public String getWriteInquiryForm() {
        return "writeInquiry";
    }


    @PostMapping("/inquiry")
    public String doWriteInquiry(@Valid @ModelAttribute InquiryRegisterRequest inquiryRegisterRequest,
                                 BindingResult bindingResult,
                                 Model model, User user) throws ValidationFailedException, IOException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        List<MultipartFile> files = saveMultipartFiles(inquiryRegisterRequest);

        inquiryRepository.addInquiryToMap(inquiryRegisterRequest.getTitle(),
                inquiryRegisterRequest.getCategory(),
                inquiryRegisterRequest.getComment(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                user.getId(), files, false);

        model.addAttribute("inquiries", inquiryRepository.getUserInquiryList(user.getId()));

        return "customerInquiryList";
    }

    @GetMapping("/inquiryDetail")
    public String getInquiryDetailView(@RequestParam int id, User user, Model model) throws MalformedURLException {
        TreeMap<Integer, Inquiry> inquiryMap = inquiryRepository.getInquiryMap(user.getId());
        List<MultipartFile> files = inquiryMap.get(id).getFile();

        List<String> fileList = new ArrayList<>();

        for (MultipartFile file : files) {
            fileList.add(file.getOriginalFilename());

        }

        model.addAttribute("inquiry", inquiryMap.get(id));
        model.addAttribute("files", fileList);
        return "/inquiryDetail";

    }


    private List<MultipartFile> saveMultipartFiles(InquiryRegisterRequest inquiryRegisterRequest) throws IOException {
        final String UPLOAD_DIR = "/Users/jieunkim/Downloads/";

        List<MultipartFile> fileList = inquiryRegisterRequest.getFile();


        if (!fileList.isEmpty()) {
            for (MultipartFile file : fileList) {
                file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));
            }
        }

        return fileList;
    }


    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleValidationFailedException(ValidationFailedException ex, Model model) {
        model.addAttribute("exception", ex);
        return "error";
    }


}
