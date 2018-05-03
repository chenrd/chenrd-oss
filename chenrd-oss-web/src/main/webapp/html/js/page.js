/**
 * Created by Admin on 2017/3/2.
 */
//                            _ooOoo_
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖保佑             永无BUG

var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
var h = $('#J_top').height();
if(h>= $('#main').offset().top){
    $('.tt-news-nav').css({'position':'fixed','top':0,'z-index':'999999999'});
    $('.J_tuijian').css('margin-top','.8rem');
}
//if($('#redian').offset().top-h<=h){
//    $('#J_top').css({'position':'absolute','z-index':'99999999','top':$('#redian').offset().top-h-h+'px'});
//}else{
//    $('#J_top').css({'position':'fixed','z-index':'99999999','top':'0'});
//    $('#hot_list').css('margin-top',h+'px');
//}
/**
 * 更新搜索关键字
 * @param  {[type]} wd 搜索关键字
 * @return {[type]}    [description]
 */
function changeWord(wd){
    $('#J_search_input').val(wd);
}
function bdSearch(json){
    var k = json['q'],
        sd = json['s'],
        len = sd.length > 5 ? 5 : sd.length,
        $searchResult = $('#J_search_result'),
        $srList = $searchResult.children('.search-result-list'),
        qdid = '1012704x';
    if(tt_news_mid === 'pgzs' || tt_news_mid.indexOf('waitui') > -1){
        qdid = '1015785c';
        if(tt_news_mid === 'waitui040'){
            qdid = '1015785c';
        }
    }
    if(len == 0){return;}
    $srList.empty();
    for (var i = 0; i < len; i++) {
        var str = getNewStr(sd[i], [k]);
        $srList.append('<li class="search-result-item"><a href="https://m.baidu.com/s?word=' + sd[i] + '&ie=utf-8&from=' + qdid + '" aid="2" sid="0" pz="'+i+'" title="'+str+'">' + str + '</a><div class="ttp" onclick="changeWord(\'' + sd[i] + '\');"></div></li>');
    }
    $searchResult.show();
}

$(function(){
    var osType = getOsType();
    var userId = localStorage.getItem('myid');
    if(!userId){
        userId = (+new Date()) + Math.random().toString(10).substring(2, 6);
        _localStorage_setItem('myid',userId);
    }
    //输入框清空功能实现
    (function(){
        var $searchInput = $('#J_search_input'),
            $searchClear = $('#J_search_clear'),
            $searchResult = $('#J_search_result'),
            $closeSearchResult = $('#J_close_search_result');
        // 搜索框输入事件
        $searchInput.on('keyup', function(){
            if($.trim($searchInput.val()) !== ''){
                $searchClear.show();
                fillUrls();
            } else {
                $searchClear.hide();
                $searchResult.hide();
            }
        });
        // 搜索框清空按钮点击事件
        $searchClear.on('click', function(e){
            $searchInput.val('');
            $searchClear.hide();
            $searchResult.hide();
            e.preventDefault();
        });
        // 搜索提示列表关闭按钮点击事件
        $closeSearchResult.on('click', function(e){
            $searchResult.hide();
            e.preventDefault();
        });

        function fillUrls() {
            var wd = $.trim($searchInput.val());
            if(!wd){
                return;
            }
            var qsData = {
                'wd': wd,
                'p': '3',
                'ie': 'utf-8',
                'json': '1',
                'from': '1012704x',
                'cb': 'bdSearch'
            };

            $.ajax({
                async: false,
                url: "https://m.baidu.com/su",
                type: "GET",
                dataType: 'jsonp',
                jsonp: 'jsoncallback',
                data: qsData,
                timeout: 5000,
                success: function () {},
                error: function () {}
            });
        }
    })();
    //搜索下方的热词
    (function(){
        function reciStr(ele){
            var html = '';
            var qdid = 'm021_'+tt_news_mid;
            //if(tt_news_mid === 'pgzs' || tt_news_mid.indexOf('waitui') > -1){
            //    qdid = '1015785c';
            //    if(tt_news_mid === 'waitui040'){
            //        qdid = '1015785c';
            //    }
            //}
            for(var i = 0;i<3;i++){
                html += '<li class="swiper-slide">';
                for(var j = i*3;j<i*3+3;j++){
                    var sid = ele[j].url.substr(26,15);
                    html += '<a aid="3" sid="'+sid+'" pz="'+j+'" href="' + ele[j].url + '" target="_blank" title="'+ele[j].title+'" ';
                    if(j%3 == 2){
                        html += 'class="sp"';
                    }
                    var len = ele[j].title.length;
                    if(len > 7){
                        ele[j].title = ele[j].title.substr(0,7)
                    }
                    html += '>'+ele[j].title+'</a>';
                }
                html += '</li>'
            }
            $('#hot_word .swiper-wrapper').html(html);
        }
        var data = __reci[0].data.day_rd;
        reciStr(data);
    })();
    //搜索下方热词轮播
    (function(){
        var mySwiper = new Swiper ('.swiper-hot', {
            direction: 'vertical',
            loop: true,
            nextButton:'.change'
        })
    })();
    //热门推荐部分
    (function(){
        $('#tuijian .nav .right').on('click','a',function(){
            var n = $(this).parent().index();
            $('#tuijian .nav .right div').removeClass('active');
            $(this).parent().addClass('active');
            $('#tuijian .content ul').addClass('none');
            $('#tuijian .content ul').eq(n).removeClass('none');
        })
    })();
    //热点处轮播
    (function(){
        var m = 0;
        var bol = true;
        var bol0 = false,bol1 = false,bol2 = false,bol3 = false;
        var classid = 28;
        $('#redian .nav .right').on('click','a',function(){
            m = $(this).parent().index();
            classid = 28 + m;
            var type = $(this).attr('data-type');
            $('#redian .nav .right div').removeClass('active');
            $(this).parent().addClass('active');
            $('#redian .content .list-info').addClass('down');
            $('#redian .content .list-info').eq(m).removeClass('down');
            $('#redian .txt').addClass('none');
            $('#redian .txt').eq(m).removeClass('none');
            bol = true;
            if($('#redian .txt').eq(m).find('div').eq(1).attr('class') == 'none'){
                $('#redian .more-info span').html('显示更多新闻');
                $('#redian .more-info img').attr('src','img/gengduo.png');
            }else{
                $('#redian .more-info span').html('隐藏更多新闻');
                $('#redian .more-info img').attr('src','img/gengshao.png');
            }
        });
        function moreinfo(ele){
            if($('#redian .txt').eq(m).find('div').eq(1).attr('class') == 'none'){
                $('#redian .txt').eq(m).find('div').eq(1).removeClass('none');
                $('#redian .more-info span').html('隐藏更多新闻');
                $('#redian .more-info img').attr('src','img/gengshao.png');
            }else{
                $('#redian .txt').eq(m).find('div').eq(1).addClass('none');
                $('#redian .more-info span').html('显示更多新闻');
                $('#redian .more-info img').attr('src','img/gengduo.png');
            }
        }
        $('#redian .more-info').on('click','span',function(){
            moreinfo(this);
        });
        $('#redian .more-info').on('click','img',function(){
            moreinfo(this);
        });
        function lunboStr(ele,index){
            var html = '';
            classid = 28 + index;
            for(var i = 0;i<3;i++){
                html += '<div class="swiper-slide"><a href="'+ele[i].url+'?qid='+tt_news_qid+'" aid="6" sid="'+classid+'" pz="'+i+'" title="'+ele[i].topic+'"><img class="fl" src="'+ele[i].miniimg[0].src+'" alt=""><img class="fr" src="'+ele[i].miniimg[1].src+'" alt=""><span>'+ele[i].topic+'</span></a></div>';
            };
            $('#redian .list-info').eq(index).find('.swiper-wrapper').html(html);
        };
        function txtStr(ele,index){
            var html = '';
            classid = 28 + index;
            for(var i = 0;i<2;i++){
                html += '<div ';
                if(i == 1){
                    html += 'class="none"';
                }
                html +=  '>';
                for(var j = 5*i+3;j<5*i+8;j++){
                    html += '<a href="'+ele[j].url+'?qid='+tt_news_qid+'" target="_blank" aid="6" sid="'+classid+'" pz="'+j+'" title="'+ele[j].topic+'">'+ele[j].topic+'</a>'
                };
                html += '</div>';
            }
            $('#redian .txt-info .txt').eq(index).html(html);
        }
        function ajStr(type,index){
            $.ajax({
                url: '//m021topnews.dftoutiao.com/m021topnews/getnews',
                data: {
                    type:type,
                    newsnum:20,
                    os:osType,
                    uid:userId,
                    qid:tt_news_qid
                },
                dataType: 'jsonp',
                jsonp: "callback",
                timeout: 8000,
                beforeSend: function(){
                },
                success: function(data){
                    function sortNumber(a,b)
                    {
                        return b.miniimg.length - a.miniimg.length
                    }
                    var arr = data.data;
                    arr = arr.sort(sortNumber);
                    lunboStr(arr,index);
                    txtStr(arr,index);
                },
                error: function(e) {
                    alert(e);
                    console.info(e);
                },
                complete: function(){
                    if(index == 0){
                        bol0 = true;
                    }else if(index == 1){
                        bol1 = true;
                    }else if(index == 2){
                        bol2 = true;
                    } else if(index == 3){
                        bol3 = true;
                    }
                }
            });
        };
        ajStr('toutiao',0);
        ajStr('shehui',1);
        ajStr('junshi',2);
        ajStr('xingzuo',3);
        var timer = setInterval(function(){
            if(bol0 && bol1 && bol2 && bol3){
                var mySwiper = new Swiper ('.swiper-redian', {
                    direction: 'horizontal',
                    loop: true,
                    // 如果需要分页器
                    pagination: '.swiper-pagination',
                    paginationClickable :true,
                });
                $('#redian .more-info').show();
                $('#redian .loading').hide();
                clearInterval(timer);
            }

        },28)
    })();

    (function() {
        ttNewsNavSwiper = new Swiper('#J_ttnews_nav_container', {
            //direction : 'horizontal',
            //loop : false,
            freeMode: true,
            speed: 500,
            slidesPerView: 5.5
        });
        $(window).on('scroll',function(){
            if(getScrollTop()>= $('#main').offset().top){
                $('.tt-news-nav').css({'position':'fixed','top':0,'z-index':'999999999'});
                $('.sametype').css('margin-top','.8rem');
            }else{
                $('.tt-news-nav').css({'position':'relative'});
                $('.sametype').css('margin-top','0rem');
            }
            if($('#redian').offset().top-h<=getScrollTop()){
                //$('.flow-fixed').show(); //屏蔽原理的返回顶部
            }else{
                $('.flow-fixed').hide();
            }
        });
    })();
    $('.flow-fixed').on('click',function(){
        $('body').scrollTop(0);
    });

    $('#search-bottom .more').on('click',function(){
        $('#search-bottom .list').eq(1).toggle();
    });

});