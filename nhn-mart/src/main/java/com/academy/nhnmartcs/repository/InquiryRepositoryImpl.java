package com.academy.nhnmartcs.repository;

import com.academy.nhnmartcs.domain.Inquiry;
import com.academy.nhnmartcs.domain.InquiryCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public class InquiryRepositoryImpl implements InquiryRepository {
    Map<String, TreeMap<Integer, Inquiry>> inquiryMap = new HashMap<String, TreeMap<Integer, Inquiry>>();
    //본인의 문의만 볼 수 있게끔, 고객의 아이디로 각각 트리맵을 생성합니다.
    //내부의 트리맵에는 키: 문의 번호 밸류: 문의 객체가 들어갑니다.
    static Integer inquiryId = 1;

    @Override
    public TreeMap<Integer, Inquiry> getInquiryMap(String userId) {
        return inquiryMap.get(userId);
    }

    @Override
    public Inquiry getInquiry(String userId, Integer inquiryId) {
        return inquiryMap.get(userId).get(inquiryId);
    }

    @Override
    public Inquiry addInquiryToMap(String title, InquiryCategory category, String comment, String writeDate,
                                   String userId, List<MultipartFile> file, boolean isAnswered) {
        if (getInquiryMap(userId) == null) {
            TreeMap<Integer, Inquiry> userInquiryMap = new TreeMap<>();
            userInquiryMap.put(inquiryId, new Inquiry(inquiryId, title, category, comment, writeDate, userId, file, isAnswered));
            inquiryMap.put(userId, userInquiryMap);
        }

        if (getInquiryMap(userId) != null) {
            getInquiryMap(userId).put(inquiryId, new Inquiry(inquiryId, title, category, comment, writeDate, userId, file, isAnswered));
        }

        inquiryId++;

        return inquiryMap.get(userId).get(inquiryId);
    }

    @Override
    public List<Inquiry> getUserInquiryList(String userId) {
        TreeMap<Integer, Inquiry> eachUserInquiryList = inquiryMap.get(userId);
        if (eachUserInquiryList == null) {
            return null;
        }

        return new ArrayList<>(eachUserInquiryList.values());
    }


}
