//package com.example.VodReco.service.category.viewCategories;
//
//import com.example.VodReco.domain.Genres;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CategoryViewCategoriesServiceImpl {
//    private final CategoryRepository categoryRepository;
//    //모든 장르 리스트
////    @Override
//    public List<String> viewCategoryList() throws UnsupportedEncodingException {
//        List<String> list = new ArrayList<>();
//        for (Category g : categoryRepository.findAll()) {
//            String category = g.getCategory();
//            list.add(category);
//            //서버에서 클라이언트에 한글 보낼 때는 필요없음(231128)
////            try {
////                String encodedGenre = URLEncoder.encode(genre, "utf-8");
////                list.add(encodedGenre);
////            } catch (UnsupportedEncodingException e) {
////                System.out.println("인코딩 불가: " + genre);
////            }
//        }
//        return list;
//    }
//}
