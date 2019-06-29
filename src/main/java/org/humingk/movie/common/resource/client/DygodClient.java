package org.humingk.movie.common.resource.client;

import org.humingk.movie.common.resource.AbstractMovieResourceAdapter;
import org.humingk.movie.common.resource.resource.DygodResource;
import org.humingk.movie.common.resource.resource.Resource;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取 dygod 资源信息
 *
 * @author lzx
 * @author humingk
 */
public class DygodClient extends AbstractMovieResourceAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 基本 URL
     */
    private static final String BASE_URL = "https://www.dygod.net";

    /**
     * 获取电影搜索列表
     *
     * @param keyword 搜索关键字
     * @param max     搜索结果保留最大数
     */
    @Override
    public Map<String, String> getMovieList(String keyword, int max) {
        Map<String, String> result = null;
        String url = BASE_URL + "/e/search/index.php";
        try {
            String data = "show=title&tempid=1&keyboard=" + URLEncoder.encode(keyword, "gb2312") + "&Submit=%C1%A2%BC%B4%CB%D1%CB%F7";
            Document doc = httpUrlConnRequest(url, data, "POST");
            Elements movieList = doc.select("table.tbspan a");
            if (movieList.size() != 0) {
                result = new LinkedHashMap<>();
                for (int i = 0; i < max && i < movieList.size(); i++) {
                    String movieName = movieList.get(i).attr("title");
                    String movieUrl = BASE_URL + movieList.get(i).attr("href");
                    result.put(movieName, movieUrl);
                    logger.debug("(电影天堂)获取电影 " + movieName + " ...url: " + movieUrl);
                }
                logger.debug("(电影天堂)获取电影搜索列表成功,共 " + movieList.size() + " 条...keyword: " + keyword);
            } else {
                logger.debug("(电影天堂)获取电影搜索列表失败...keyword: " + keyword);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return result;
    }

    /**
     * 通过指定电影url获取资源
     *
     * @param movieName
     * @param movieUrl
     * @return
     */
    @Override
    public <T> T getMovie(String movieName, String movieUrl) {
        DygodResource result = null;
        try {
            Document doc = jsoupRequest(movieUrl, Connection.Method.GET);
            Elements movieList = doc.select("div#Zoom table a");
            if (movieList.size() != 0) {
                result = new DygodResource();
                result.setMovieName(movieName);
                result.setMovieUrl(movieUrl);
                // 资源分类
                List<Resource> thunder = new ArrayList<>();
                List<Resource> magnet = new ArrayList<>();
                // 解析迅雷链接和磁力链接
                parseResource(movieList, thunder, magnet);
                result.setThunder(thunder);
                result.setMagnet(magnet);
                logger.debug("(电影天堂)获取电影资源成功,共 " + movieList.size() + " 条...movieName: " + movieName);
            } else {
                logger.debug("(电影天堂)获取电影资源失败...movieName: " + movieName);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return (T) result;
    }
}
