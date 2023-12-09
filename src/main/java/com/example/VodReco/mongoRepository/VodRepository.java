package com.example.VodReco.mongoRepository;

import com.example.VodReco.domain.Vod;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.regex.Pattern;

//@Repository jpa가 아니라면 안 써도 됨
public interface VodRepository extends MongoRepository<Vod,String> {
//    Vod findByContentId(String contentId);
    Vod findByContentId(String contentId);
    List<Vod> findAllByGenre(String genre);

    List<Vod> findByMoodContaining(String mood);
    List<Vod> findBySeriesId(String seriesId);

    //검색기능
    //@Query("{'$or':[ {'title': { $regex: ?0, $options: 'i' }}, {'actors': { $regex: ?0, $options: 'i' }} ]}")
    //List<Vod> findByTitleIgnoreCaseContainingOrActorsIgnoreCaseContaining(Pattern title, Pattern actors);

    //@Query("{$or:[ {'actors': ?0} ]}")
    List<Vod> findByActorsIgnoreCaseContaining(String actor);
    //@Query("{$or:[ {'title': ?0} ]}")
    List<Vod> findByTitleContainingIgnoreCase(String title);

//    @Query("{$or:[ {'title': ?0} ]}")
//    List<Vod> findByExactTitle(String title);
//
//    @Query("{$or:[ {'actors': ?0} ]}")
//    List<Vod> findByExactActors(String actor);
//
//    @Query("{$or:[ {'title': ?0}, {'actors': ?0} ]}")
//    List<Vod> findByTitleOrActors(String searchTerm);

}