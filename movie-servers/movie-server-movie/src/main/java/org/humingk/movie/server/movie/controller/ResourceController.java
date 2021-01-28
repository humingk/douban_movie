package org.humingk.movie.server.movie.controller;

import org.humingk.movie.api.common.converter.ResourceMovieVoConverter;
import org.humingk.movie.api.common.vo.ResourceMovieVo;
import org.humingk.movie.api.movie.ResourceApi;
import org.humingk.movie.common.entity.Result;
import org.humingk.movie.service.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/** @author humingk */
@RestController
public class ResourceController implements ResourceApi {
      @Autowired
      private ResourceMovieVoConverter resourceMovieVoConverter;
      @Autowired private ResourceService resourceService;

      @Override
      public Result<List<ResourceMovieVo>> bases(
          @RequestParam("id") @NotNull Long id,
          @RequestParam(value = "limit", required = false, defaultValue = "30") @PositiveOrZero
              Integer limit) {
        return Result.success(
            resourceMovieVoConverter.toList(resourceService.getResourceListByMovieDoubanId(id,
     limit)));
      }
}
