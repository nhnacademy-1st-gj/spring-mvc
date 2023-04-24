package com.academy.nhnmartcs.repository;

import com.academy.nhnmartcs.domain.Inquiry;
import com.academy.nhnmartcs.domain.InquiryCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.TreeMap;

public interface InquiryRepository {
    TreeMap<Integer, Inquiry> getInquiryMap(String userId);

    Inquiry getInquiry(String userId, Integer inquiryId);

    Inquiry addInquiryToMap(String title, InquiryCategory category, String comment, String writeDate,
                            String userId, List<MultipartFile> file, boolean isAnswered);

    List<Inquiry> getUserInquiryList(String userId);
}
