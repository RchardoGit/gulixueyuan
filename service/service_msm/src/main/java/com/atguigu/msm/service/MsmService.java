package com.atguigu.msm.service;

import java.util.HashMap;

/**
 * @author konglingyang
 * @date 2022/1/24 20:29
 */
public interface MsmService {

    Boolean sendMsm(String phone, String templateCode, HashMap<String, String> map);
}
