package org.humingk.movie.dal.domain.douban;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.humingk.movie.dal.entity.MovieDoubanToCelebrityDouban;
import org.humingk.movie.dal.entity.RateMovieDouban;

import java.io.Serializable;
import java.util.List;

/**
 * 与豆瓣影人相关的豆瓣电影
 *
 * @author humingk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDoubanOfCelebrityDoubanDo implements Serializable {
  private static final long serialVersionUID = 1L;
  // moviedouban ------------------------------------

  /** 豆瓣电影ID */
  private Long id;

  /** 影片类型ID */
  private Byte idTypeVideo;

  /** IMDB电影ID 0-未匹配 1-已匹配 */
  private Long idMovieImdb;

  /** 上映时间 /year */
  private Short startYear;

  /** 豆瓣电影中文名 */
  private String nameZh;

  /** 豆瓣电影原始名 */
  private String nameOrigin;

  /** 豆瓣电影运行片长 /m */
  private Short runtime;

  /** 豆瓣电影海报ID */
  private Long urlPoster;

  /** 已看人数 */
  private Integer haveSeen;

  /** 想看人数 */
  private Integer wannaSee;

  /** 是否匹配到网易云音乐 0-未匹配 1-匹配无 2-匹配有 */
  private Byte isNetease;

  /** 是否匹配到知乎话题或问题 0-未匹配 1-匹配无 2-匹配有 */
  private Byte isZhihu;

  /** 简介 */
  private String summary;

  // rate ------

  /** 豆瓣电影评分 */
  private RateMovieDouban rateMovieDouban;

  // MovieDoubanToCelebrityDouban --------------------------------------

  /** 豆瓣电影-豆瓣影人 关系列表 */
  private List<MovieDoubanToCelebrityDouban> movieDoubanToCelebrityDoubanList;
}
