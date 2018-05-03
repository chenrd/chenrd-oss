var tt_news_mid = '', tt_news_qid = '';
(function(){
    // 获取渠道ID并赋值给全局变量TT_NEWS_QID;
    tt_news_qid = getChannelId();
    // 存储qid到cookie中(3天)
    var days = 3;
    var exp = new Date();
    exp.setTime(exp.getTime() + days * 24 * 60 * 60 * 1000);
    document.cookie = "qid="+ encodeURI(tt_news_qid) + ";expires=" + exp.toGMTString();

    /**
     * 获取渠道ID（渠道的两种形式, ?xxxx和?mid=xxxx）
     * @return {[type]} [description]
     */
    function getChannelId(){
        var curHost = window.location.host,
            prefix = '';
        tt_news_mid = getQueryString('mid');
        switch(curHost){
            case 'm.021.com': prefix = 'm021'; break;
            case 'mini.apdft.com': prefix = 'apdft'; break;
            default: prefix = 'm021'; break;
        }
        if(tt_news_mid){	// ?mid=xxxx
            return prefix + '_' + tt_news_mid;
        } else {	// 无渠道情况 和 ?xxxx
            tt_news_mid = getQueryString2();
            if(tt_news_mid){
                return prefix + '_' + tt_news_mid;
            } else {
                return prefix + 'dh';
            }
        }
    }

    /**
     * 获取url中参数的值
     * @param  {[type]} name 参数名
     * @return {[type]}      参数值
     */
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }

    /**
     * 获取url中?后面的字符串
     * @return {[type]} [description]
     */
    function getQueryString2(){
        return decodeURI(window.location.search.substr(1));
    }

})();

//获取滚动高度
//localStorage.clear();
function getScrollTop(){
    if (document.documentElement && document.documentElement.scrollTop) {
        return document.documentElement.scrollTop;
    } else if (document.body) {
        return document.body.scrollTop;
    }
}
/**
 * 获取文档高度
 * @return {[type]} [description]
 */
function getClientHeight(){
    if (document.body.clientHeight && document.documentElement.clientHeight) {
        return (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;
    } else {
        return (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;
    }
}
/**
 * 关键词高亮处理（递归算法）
 * @param  {String} txt   标题
 * @param  {Array} swArr  关键词数组(字符串)
 * @param  {Number} i     0
 * @return {String}       新的标题
 */
function getNewStr(txt, swArr, i){
    if(txt && swArr && swArr.length){
        var len = swArr.length;
        swArr.sort(function(a, b){
            return b.length - a.length;
        });
        if(!i){i = 0;}
        if(i == len || swArr[i] == '..'){
            return txt;
        } else {
            var reg = new RegExp(swArr[i], 'gi');
            var tempTxt = txt;
            var subTxtIndex = tempTxt.toLowerCase().indexOf(swArr[i].toLowerCase());
            var subTxt = txt.substring(subTxtIndex, subTxtIndex + swArr[i].length);
            return getNewStr(txt.replace(reg, '<em>' + subTxt + '</em>'), swArr, ++i);
        }
    } else {
        return '';
    }
}