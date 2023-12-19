//package com.example.VodReco.domain;
//
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Document(collection = "log")
//@NoArgsConstructor
//public class LogEntity {
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
//
//    @Column(nullable = false)
//    private String divide;
//
//    @Column(nullable = false)
//    private String ipAddress;
//
//    @Column(nullable = false)
//    private String requestURL;
//    //사용자나 시스템에서 수행된 특정 작업 또는 활동
//    //로그인, 로그아웃, 버튼 클릭, 페이지 접근
//    @Column(nullable = false)
//    @Field(name = "actions")
//    private String action;
//    //해당 작업에 대한 상세 정보를 제공
//    //로그인 액션의 경우 로그인한 사용자 이름이나 ID, 성공 여부와 같은 정보 + 클릭한 시간
//    @Column(nullable = false)
//    private String details;
//
//    @Column(nullable = false)
//    private boolean isSuccess;
//
//    @Column(nullable = false)
//    private LocalDateTime timestamp;
//
//    @Column(nullable = false)
//    private String timeCategory;
//
//
//    @Builder
//    public LogEntity(String divide, String ipAddress, String requestURL, String action, String details, boolean isSuccess, LocalDateTime timestamp, String timeCategory) {
//        this.divide = divide;
//        this.ipAddress = ipAddress;
//        this.requestURL = requestURL;
//        this.action = action;
//        this.details = details;
//        this.isSuccess = isSuccess;
//        this.timestamp = timestamp;
//        this.timeCategory = timeCategory;
//    }
//
//}
