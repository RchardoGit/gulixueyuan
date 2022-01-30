package com.atguigu.cms.service;

import com.atguigu.cms.enties.Banner;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author kly
 * @since 2022-01-23
 */
public interface BannerService extends IService<Banner> {

    Page<Banner> getPage(Long current, Long size);

    boolean saveBanner(Banner banner);

    boolean updateBannerById(Banner banner);

    Banner getBannerById(String id);

    boolean removeBannerById(String id);

    List<Banner> getBannerlist();

}
