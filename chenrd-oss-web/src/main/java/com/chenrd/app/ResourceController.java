package com.chenrd.app;

import com.chenrd.app.business.ResourceManager;
import com.chenrd.app.request.ResourceQuery;
import com.chenrd.app.vo.ResourceVO;
import com.chenrd.common.FreemarkerController;
import com.chenrd.common.JQueryTableResult;
import com.chenrd.common.Paging;
import com.chenrd.common.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by chenrd on 2017/8/27.
 */
@RequestMapping("resource")
@Controller
public class ResourceController extends FreemarkerController {

    @Resource(name = "resourceManager")
    private ResourceManager resourceManager;

    @GetMapping("")
    public String index() {
        return getViewName("view/resource/rows");
    }

    @PostMapping("find")
    @ResponseBody
    public JQueryTableResult find(ResourceQuery queryInfo, Paging paging) {
        return new JQueryTableResult(resourceManager.find("find", ResourceVO.class, queryInfo, paging), paging);
    }

    @GetMapping("loadResource")
    public String loadResource() {
        return getViewName("view/resource/load");
    }

    @PostMapping("loadResource")
    @ResponseBody
    public WebResult loadResource(MultipartFile multipartFile) {

        return null;
    }



}
