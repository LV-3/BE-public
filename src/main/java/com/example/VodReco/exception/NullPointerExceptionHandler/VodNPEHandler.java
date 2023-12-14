package com.example.VodReco.exception.NullPointerExceptionHandler;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class VodNPEHandler {
//    private final MongoTemplate mongoTemplate;
//
//    public void checkVodsCollection() throws IOException {
//
//        boolean vodsCollectionExists = mongoTemplate.collectionExists("vods");
//        boolean allCollectionsDeleted = false;

        // vods collection이 아예 삭제된 경우
//        if (!vodsCollectionExists) {
//
//            // VodRecoForView 데이터베이스 내부의 모든 collection 확인
//            List<String> collectionNames = mongoTemplate.getCollectionNames().stream().toList();
//            if (collectionNames.isEmpty()) {
//            allCollectionsDeleted = true;
//            }
//            // VodRecoForView 데이터베이스 내부의 모든 collection이 삭제되었는지 확인
////            for (String collection : collections) {
////                if (!collection.equals("system.indexes") &&
////                        !collection.equals("vods")) {
////                    allCollectionsDeleted = false;
////                    break;
////                }
//
//            // VodRecoForView 데이터베이스 내부의 모든 collection이 삭제된 경우
//            if (allCollectionsDeleted) {
//                // 여러 collection에서 데이터를 가져와서 백업하는 스크립트 실행. collection을 전부 create하는 것부터 시작
//                // user_rating_view, user_wish_view는 예외. mysql에서 복사해오기
////                1차 구현 시엔 일단 mongoDB에서 가져오기
//                Runtime.getRuntime().exec("bash /home/ubuntu/allcollectionsbackup.sh");
//            } else {
//                // vods collection과 함께 여러 collection이 삭제되었다면 vods collection 데이터만 백업하는 스크립트 실행
//                // 스크립트에서는 vods collection을 새로 create하고 내부 데이터 import
//                Runtime.getRuntime().exec("bash /home/ubuntu/vodscreatebackup.sh");
//            }
//        } else {
//            // vods collection이 존재하는 경우
////            vods 내부 document들 존재 확인
//            long count = mongoTemplate.count(new Query(), "vods"); // "vods" 컬렉션의 문서 개수 조회
//            //하나도 없으면
//            if (count == 0) {
//                System.out.println("스크립트 실행 전");
////                vod collection을 새로 만들지는 않고 내부 데이터만 import해오는 스크립트 실행
//            Runtime.getRuntime().exec("bash /home/ubuntu/vodsbackup.sh");
//            //하나라도 있으면 그대로 끝
//            }
//        }
//    }
        // ...


import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

        @Component
        @RequiredArgsConstructor
        public class VodNPEHandler {
            private final MongoTemplate mongoTemplate;

            public void checkVodsCollection() throws IOException {
                boolean vodsCollectionExists = mongoTemplate.collectionExists("vods");
                boolean allCollectionsDeleted = false;

                if (!vodsCollectionExists) {
                    List<String> collectionNames = mongoTemplate.getCollectionNames().stream().toList();
                    if (collectionNames.isEmpty()) {
                        allCollectionsDeleted = true;
                    }

                    if (allCollectionsDeleted) {
                        ProcessBuilder processBuilder = new ProcessBuilder("bash", "/home/ubuntu/allcollectionsbackup.sh");
                        processBuilder.inheritIO(); // 프로세스 입출력을 현재 프로세스에 연결
                        Process process = processBuilder.start();

                        try {
                            int exitCode = process.waitFor(); // 프로세스가 완료될 때까지 대기

                            // 프로세스 실행 후 로직 처리
                            if (exitCode == 0) {
                                // 성공적으로 실행됐을 때의 로직
                                // 원하는 작업 수행
                            } else {
                                // 비정상적으로 종료됐을 때의 로직
                                // 에러 처리
                            }
                        } catch (InterruptedException e) {
                            // 인터럽트 예외 처리
                            e.printStackTrace();
                            Thread.currentThread().interrupt(); // 현재 쓰레드 재인터럽트
                        }
                    } else {
                        ProcessBuilder processBuilder = new ProcessBuilder("bash", "/home/ubuntu/vodscreatebackup.sh");
                        // 이하 동일
                        processBuilder.inheritIO(); // 프로세스 입출력을 현재 프로세스에 연결
                        Process process = processBuilder.start();

                        try {
                            int exitCode = process.waitFor(); // 프로세스가 완료될 때까지 대기

                            // 프로세스 실행 후 로직 처리
                            if (exitCode == 0) {
                                // 성공적으로 실행됐을 때의 로직
                                // 원하는 작업 수행
                            } else {
                                // 비정상적으로 종료됐을 때의 로직
                                // 에러 처리
                            }
                        } catch (InterruptedException e) {
                            // 인터럽트 예외 처리
                            e.printStackTrace();
                            Thread.currentThread().interrupt(); // 현재 쓰레드 재인터럽트
                        }
                    }
                } else {
                    long count = mongoTemplate.count(new Query(), "vods");
                    if (count == 0) {
                        System.out.println("스크립트 실행 전");
                        ProcessBuilder processBuilder = new ProcessBuilder("bash", "/home/ubuntu/vodsbackup.sh");
                        // 이하 동일
                        processBuilder.inheritIO(); // 프로세스 입출력을 현재 프로세스에 연결
                        Process process = processBuilder.start();

                        try {
                            int exitCode = process.waitFor(); // 프로세스가 완료될 때까지 대기

                            // 프로세스 실행 후 로직 처리
                            if (exitCode == 0) {
                                // 성공적으로 실행됐을 때의 로직
                                // 원하는 작업 수행
                            } else {
                                // 비정상적으로 종료됐을 때의 로직
                                // 에러 처리
                            }
                        } catch (InterruptedException e) {
                            // 인터럽트 예외 처리
                            e.printStackTrace();
                            Thread.currentThread().interrupt(); // 현재 쓰레드 재인터럽트
                        }
                    }
                }
            }
        }
