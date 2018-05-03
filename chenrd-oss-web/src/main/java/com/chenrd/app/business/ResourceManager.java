package com.chenrd.app.business;

import com.chenrd.oss.power.PowerBusiness;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chenrd on 2017/8/27.
 */
public interface ResourceManager extends PowerBusiness {

    Integer loadResource(MultipartFile file);
}
