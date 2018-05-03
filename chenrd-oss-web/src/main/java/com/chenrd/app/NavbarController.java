package com.chenrd.app;

import com.chenrd.app.business.NavbarManager;
import com.chenrd.app.request.NavbarQuery;
import com.chenrd.app.vo.NavbarVO;
import com.chenrd.common.FreemarkerController;
import com.chenrd.common.JQueryTableResult;
import com.chenrd.common.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by chenrd on 2017/8/30.
 */
@RequestMapping("navbar")
@Controller
public class NavbarController extends FreemarkerController {

    @Resource
    private NavbarManager navbarManager;

    @GetMapping("")
    public String index() {
        return getViewName("view/navbar/rows");
    }

    @PostMapping("find")
    @ResponseBody
    public JQueryTableResult find(NavbarQuery queryInfo, Paging paging) {
        return new JQueryTableResult(navbarManager.find("find", NavbarVO.class, queryInfo, paging), paging);
    }

    @GetMapping("add")
    public String add() {
        return getViewName("view/navbar/add");
    }

    @PostMapping("add")
    @ResponseBody
    public void add(NavbarVO vo) {
        navbarManager.save(vo);
    }

    @GetMapping("modify/{id}")
    public String modify(@PathVariable Long id, ModelMap map) {
        map.put("bean", navbarManager.get(id, NavbarVO.class));
        return getViewName("view/navbar/edit");
    }

    @PostMapping("modify")
    @ResponseBody
    public void modify(NavbarVO vo) {
        navbarManager.update(vo);
    }

    @GetMapping("publish/{id}")
    @ResponseBody
    public void publish(@PathVariable Long id) {
        navbarManager.publish(id);
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        navbarManager.delete(id);
    }
}
