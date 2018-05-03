package com.chenrd.app.business;

import com.chenrd.app.vo.NavbarVO;
import com.chenrd.oss.power.PowerBusiness;

/**
 * Created by chenrd on 2017/8/30.
 */
public interface NavbarManager extends PowerBusiness {

    void save(NavbarVO vo);

    void update(NavbarVO vo);
}
