/**
 * Created by Admin on 2017/3/6.
 */
/**
 * browser的判断
 * @return {[type]} [description]
 */
function getBrowserType(){
    var agent = navigator.userAgent.toLowerCase();
    var browser_type = "";
    if (agent.indexOf("msie") > 0) {
        browser_type = "IE";
    }
    if (agent.indexOf("firefox") > 0) {
        browser_type = "firefox";
    }
    if (agent.indexOf("chrome") > 0
        && agent.indexOf("mb2345browser") < 0
        && agent.indexOf("360 aphone browser") < 0) {
        browser_type = "chrome";
    }
    if(agent.indexOf("360 aphone browser") > 0 || agent.indexOf("qhbrowser") > 0){
        browser_type = "360";
    }
    if (agent.indexOf("ucbrowser") > 0) {
        browser_type = "UC";
    }
    if (agent.indexOf("micromessenger") > 0) {
        browser_type = "WeChat";
    }
    if ((agent.indexOf("mqqbrowser") > 0 || agent.indexOf("qq")>0)
        && agent.indexOf("micromessenger") < 0) {
        browser_type = "QQ";
    }
    if (agent.indexOf("miuibrowser") > 0){
        browser_type = "MIUI";
    }
    if (agent.indexOf("mb2345browser") > 0){
        browser_type = "2345";
    }
    if (agent.indexOf("sogoumobilebrowser") > 0){
        browser_type = "sogou";
    }
    if (agent.indexOf("liebaofast") > 0){
        browser_type = "liebao";
    }
    if (agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0
        && agent.indexOf("ucbrowser") < 0
        && agent.indexOf("micromessenger") < 0
        && agent.indexOf("mqqbrowser") < 0
        && agent.indexOf("miuibrowser") < 0
        && agent.indexOf("mb2345browser") < 0
        && agent.indexOf("sogoumobilebrowser") < 0
        && agent.indexOf("liebaofast") < 0
        && agent.indexOf("qhbrowser") < 0) {
        browser_type = "safari";
    }
    return browser_type;
}
/**
 * OS的判断
 * @return {[type]} [description]
 */
function getOsType() {
    var agent = navigator.userAgent.toLowerCase();
    var os_type = "";
    if (/android/i.test(navigator.userAgent)) {
        var index = agent.indexOf("android");
        version = agent.substr(index+8,3);
        os_type = "Android ";
    }
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        var index = agent.indexOf("os");
        version = agent.substr(index+3,3);
        os_type = "iOS ";
    }
    if (/Linux/i.test(navigator.userAgent)
        && !/android/i.test(navigator.userAgent)
        && !/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        os_type = "Linux";
    }
    if(/windows|win32/i.test(navigator.userAgent)){
        os_type = "windows32";
    }
    if(/windows|win32/i.test(navigator.userAgent)){
        os_type = "windows64";
    }
    return os_type;
}
console.log(getBrowserType(),getOsType())
$(function(){
    var tuijianUrl = '//toutiao.eastday.com/toutiao_h5/RefreshJP';
    var pullurl = '//toutiao.eastday.com/toutiao_h5/NextJP';
    var idx = 1;
    var osType = getOsType();
    var userId = localStorage.getItem('myid');
    var page = 1;
    var nowtype = 'toutiao';
    var weizhi = 0;
    var aid = 9;
    var classid = 40;
    if(!userId){
        userId = (+new Date()) + Math.random().toString(10).substring(2, 6);
        _localStorage_setItem('myid',userId);
    }
    //主体新闻轮播部分
    function mainlunbo(ele,tab){
        var html = '';
        for(var i = 0;i<3;i++){
            var recommendtype = ele[i].recommendtype ? ele[i].recommendtype : '-1',
                hotnews = ele[i].hotnews;
            html += '<div class="swiper-slide"><a href="'+ele[i].url+'?qid=' + tt_news_qid + '&idx=' + (idx+i+1) + '&recommendtype=' + recommendtype + '&ishot=' + hotnews + '" data-tjname="all,'+tab+'" aid="'+aid+'" sid="'+classid+'" pz="'+i+'" title="'+ele[i].topic+'"><img src="'+ele[i].miniimg[0].src+'" alt=""><span>'+ele[i].topic+'</span></a></div>'
        };
        $('.J_'+tab+' .swiper-wrapper').html(html);
    };
    //主体新闻
    function mainInfo(ele,tab,start,end){
        var html = '';
        for(var i = start;i<end;i++){
            var recommendtype = ele[i].recommendtype ? ele[i].recommendtype : '-1',
                hotnews = ele[i].hotnews;
            if(ele[i].ispicnews == -1){
                html += '<li class="type3">'+
                    '<a href="'+ele[i].url+'?qid=' + tt_news_qid + '&idx=' + (idx+i+1) + '&recommendtype=' + recommendtype + '&ishot=' + hotnews + '" data-tjname="all,'+tab+'" aid="'+aid+'" sid="'+classid+'" pz="'+i+'" title="'+ele[i].topic+'">'+
                    '<h2>'+ele[i].topic+'</h2>'+
                    '<div class="info">';
                if(ele[i].hotnews == 1){
                    html += '<p>推荐</p>';
                }
                html += '<span>'+ele[i].date+'</span>'+
                    '<span>'+ele[i].source+'</span>'+
                    '</div>'+
                    '</a>'+
                    '</li>'
            }else if(ele[i].miniimg.length < 3){//判断type
                html += '<li class="type1">';
                html += '<a href="'+ele[i].url+'?qid=' + tt_news_qid + '&idx=' + (idx+i+1) + '&recommendtype=' + recommendtype + '&ishot=' + hotnews + '" data-tjname="all,'+tab+'" aid="'+aid+'" sid="'+classid+'" pz="'+i+'" title="'+ele[i].topic+'">';
                html += '<div class="left">';
                html += '<h2>'+ele[i].topic+'</h2>';
                html += '<div>';
                if(ele[i].hotnews == 1){
                    html += '<p>推荐</p>';
                }
                html += '<span>'+ele[i].comment_count+'评论</span>';
                html += '<span>2万阅读</span>';
                html += '<span class="fr">'+ele[i].source+'</span>';
                html += '</div>';
                html += '</div>';
                html += '<img src="'+ele[i].miniimg[0].src+'" alt="" class="right">';
                html += '</a>';
                html += '</li>';
            }else{
                html += '<li class="type2">';
                html += '<a href="'+ele[i].url+'?qid=' + tt_news_qid + '&idx=' + (idx+i+1) + '&recommendtype=' + recommendtype + '&ishot=' + hotnews + '" data-tjname="all,'+tab+'" aid="'+aid+'" sid="'+classid+'" pz="'+i+'" title="'+ele[i].topic+'">';
                html += '<h2>'+ele[i].topic+'</h2>';
                html += '<div class="pic">';
                html += '<img src="'+ele[i].miniimg[0].src+'" alt="">';
                html += '<img src="'+ele[i].miniimg[1].src+'" alt="">';
                html += '<img class="sp" src="'+ele[i].miniimg[2].src+'" alt="">';
                html += '</div>';
                html += '<div class="info">';
                if(ele[i].hotnews == 1){
                    html += '<p>推荐</p>';
                }
                html += '<span>'+ele[i].comment_count+'评论</span>';
                html += '<span>2万阅读</span>';
                html += '<span class="fr">'+ele[i].source+'</span>';
                html += '</div>';
                html += '</a>';
                html += '</li>';
            }
        }
        $('#Z_'+tab+' .tuwen').append(html);
    };

    //ajax调用
    function news(type){
        $.ajax({
            url: tuijianUrl,
            data: {
                type: type,
                endkey: '',
                domain: '021',
                os: osType,
                recgid: userId, // 用户ID
                picnewsnum: 1,
                qid: tt_news_qid,
                readhistory: '',
                idx: 0,
                pgnum: 1
            },
            dataType: 'jsonp',
            jsonp: "jsonpcallback",
            timeout: 8000,
            beforeSend: function(){
                //if(tab == 1){
                //    $('#J_loading').removeClass('none');
                //}
            },
            success: function(data){
                mainlunbo(data.data,type);
                mainInfo(data.data,type,3,10);
                _localStorage_setItem('K_'+type,data.endkey);
                var mainpage = 'page_'+type;
                var mySwiper = new Swiper ('.J_'+type+'', {
                    direction: 'horizontal',
                    loop: true,
                    // 如果需要分页器
                    pagination: '.swiper-pagination',
                    paginationClickable :true,
                });
            },
            complete: function(){
                $('#J_bg_loading').addClass('none');
                $('#J_loading').removeClass('none');
                var html = '<div id="Q_'+type+'0"></div>';
                var text = '<div id="Q_'+type+'1"></div>'
                $('#Z_'+type+' .tuwen li').eq(1).after(html);
                $('#Z_'+type+' .tuwen li').eq(6).after(text);
                $.getScript("//cpro.baidustatic.com/cpro/ui/cm.js", function() {
                    BAIDU_CLB_fillSlotAsync("u2901788","Q_"+type+'0');
                    BAIDU_CLB_fillSlotAsync("u2901788","Q_"+type+'1');
                });
            }
        });
    };
    //下一页
    function nextnews(type,page){
        var end = 20*page-1;
        $.ajax({
            url: pullurl,
            data: {
                type: type,
                startkey: localStorage.getItem('K_'+type),
                newsnum: 20,
                isnew: 1,
                domain: '021',
                os: osType,
                readhistory: '',
                idx: end,
                recgid: userId, // 用户ID
                pgnum: page,
                qid: tt_news_qid
            },
            dataType: 'jsonp',
            jsonp: "jsonpcallback",
            timeout: 8000,
            beforeSend: function(){
            },
            success: function(data){
                console.log(data,page)
                mainInfo(data.data,type,0,20);
                _localStorage_setItem('K_'+type,data.endkey);
            },
            complete: function(){
                if(page == 1 && type != 'toutiao'){
                    var html = '<div id="Q_'+type+'2"></div>';
                    var text = '<div id="Q_'+type+'3"></div>'
                    $('#Z_'+type+' .tuwen li').eq(11).after(html);
                    $('#Z_'+type+' .tuwen li').eq(16).after(text);
                    $.getScript("//cpro.baidustatic.com/cpro/ui/cm.js", function() {
                        BAIDU_CLB_fillSlotAsync("u2901788","Q_"+type+'2');
                        BAIDU_CLB_fillSlotAsync("u2901788","Q_"+type+'3');
                    });
                }
                if(type == 'toutiao'){
                    function addAD(m,n){
                        var html = '<div id="Q_toutiao'+m+'" class="myAD"></div>';
                        $('#Z_toutiao .tuwen li').eq(n).after(html);
                        $.getScript("//cpro.baidustatic.com/cpro/ui/cm.js", function() {
                            BAIDU_CLB_fillSlotAsync("u2901788",'Q_toutiao'+m);
                        });
                    }
                    //addAD(2,11);
                    for(var i = 0;i<4;i++){
                        addAD(4*(page-1)+i+2,5*i+20*(page-1)+11);
                    }
                }
            }
        });
    }
    var newsNavFlag = true;
    $(window).on('scroll',function(){
        var cHeight = getClientHeight();
        var scrollTop = getScrollTop();
        var footerOT = $('#J_loading').offset().top;
        var scrollTimer = '';
        $.cookie('T_'+nowtype,scrollTop);
        if(scrollTop + cHeight >= footerOT && newsNavFlag){
            // 上拉加载数据(延迟执行，防止操作过快多次加载)
            newsNavFlag = false;
            clearTimeout(scrollTimer);
            scrollTimer = setTimeout(function(){
                if($('.J_toutiao .swiper-wrapper').children().length == 0){
                    news('toutiao');
                    $.cookie('S_toutiao',1);
                }else{
                    console.log($.cookie('S_'+nowtype))
                    var sp = $.cookie('S_'+nowtype);
                    nextnews(nowtype,sp);
                    console.log(sp)
                    sp ++;
                    $.cookie('S_'+nowtype,sp);
                }
                newsNavFlag = true;
            }, 200);
        }
    });

    //点击头部分类
    $('#J_ttnews_nav_list div').on('click','a',function(){
        var s = $(this).parent().index();
        classid = 40 + s;
        var type = $(this).attr('data-type');
        $('.sameinfo').addClass('none');
        $('#Z_'+type).removeClass('none');
        $('.news-nav-item').removeClass('swiper-slide-active');
        $('#J_ttnews_nav_list a').removeClass('active');
        $(this).parent().addClass('swiper-slide-active');
        $(this).addClass('active');
        if($('.J_'+type+' .swiper-wrapper').children().length == 0){
            $.cookie('S_'+type,1);
            news(type);
        }else{
            var m = $.cookie('T_'+type);
            $('body').scrollTop(m);
        }
        nowtype = type;
        if($.cookie('S_'+type)){
            page = $.cookie('S_'+type);
        }else{
            page = 1;
            $.cookie('S_'+type,page);
        }
    });

    $('.morelianjie').on('click','a',function(){
        var n = 0;
        if($(this).index() == 1){
            n = 3;
        }else if($(this).index() == 2){
            n = 2;
        }
        var h = $('#main').offset().top;
        var type = $(this).attr('data-name');
        $('.sameinfo').addClass('none');
        $('#Z_'+type).removeClass('none');
        $('body, html').stop().animate({scrollTop: h}, 'normal');
        $('#J_ttnews_nav_list .news-nav-item').find('a').removeClass('active');
        $('#J_ttnews_nav_list .news-nav-item').eq(n).find('a').addClass('active');
        $('#J_ttnews_nav_list').css('transform','translate3d(0px, 0px, 0px)')
        if($('.J_'+type+' .swiper-wrapper').children().length == 0){
            $.cookie('S_'+type,1);
            news(type);
        }
    });
    $('.morelianjie2').on('click','.quxia',function(){
        var h = $('#main').offset().top;
        var type = $(this).attr('data-name');
        var n = 0;
        if($(this).index() == 1){
            n = 13;
            $('#J_ttnews_nav_list').css('transform','translate3d(-742px, 0px, 0px)');
        }else if($(this).index() == 2){
            n = 17;
            $('#J_ttnews_nav_list').css('transform','translate3d(-742px, 0px, 0px)')
        }
        $('.sameinfo').addClass('none');
        $('#Z_'+type).removeClass('none');
        $('body, html').stop().animate({scrollTop: h}, 'normal');
        $('#J_ttnews_nav_list .news-nav-item').find('a').removeClass('active');
        $('#J_ttnews_nav_list .news-nav-item').eq(n).find('a').addClass('active');
        if($('.J_xiaohua .swiper-wrapper').children().length == 0){
            $.cookie('S_'+type,1);
            news(type);
        }
    });
});