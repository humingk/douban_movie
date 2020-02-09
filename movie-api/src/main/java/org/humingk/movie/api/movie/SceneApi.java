package org.humingk.movie.api.movie;

import org.humingk.movie.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

//import org.springframework.web.bind.annotation.RestController;
//@RestController

/**
 * 电影场景API
 *
 * @author humingk
 */
@Validated
@FeignClient("movie-server-movie")
public interface SceneApi {
    /**
     * 获取电影场景详情
     *
     * @param id     豆瓣电影ID
     * @param offset 偏移量（可选，默认0）
     * @param limit  限制数（可选，默认10）
     * @return
     */
    @RequestMapping(value = "/mvoie/scene/details", method = RequestMethod.GET)
    Result details(@RequestParam("id") @NotNull Long id,
                   @RequestParam(value = "offset",
                           required = false,
                           defaultValue = "0") @PositiveOrZero Integer offset,
                   @RequestParam(value = "limit",
                           required = false,
                           defaultValue = "10") @PositiveOrZero Integer limit);
}