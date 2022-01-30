package com.atguigu.cms.service.impl;

import com.atguigu.cms.enties.Banner;
import com.atguigu.cms.dao.BannerMapper;
import com.atguigu.cms.service.BannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author kly
 * @since 2022-01-23
 */
@Service
public class BannerServiceImp extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Cacheable(value = "banner", key = "'selectPageIndexList'")
    @Override
    public Page<Banner> getPage(Long current, Long size) {
        Page<Banner> bannerPage = new Page<>(current, size);
        Page<Banner> page = baseMapper.selectPage(bannerPage, null);
        return page;
    }

    @Override
    public boolean saveBanner(Banner banner) {
        int insert = baseMapper.insert(banner);
        return insert > 0;
    }

    @Override
    public boolean updateBannerById(Banner banner) {
        int updateById = baseMapper.updateById(banner);
        return updateById > 0;
    }

    @Override
    public Banner getBannerById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean removeBannerById(String id) {
        int deleteById = baseMapper.deleteById(id);
        return deleteById > 0;
    }

    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<Banner> getBannerlist() {
        return baseMapper.selectList(null);
    }
}
