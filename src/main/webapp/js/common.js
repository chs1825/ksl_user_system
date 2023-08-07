var workedAjax = 0;
setInterval( function () {
    if ( workedAjax != 0 ) {
        $(".ajaxWorked").show();
    } else {
        $(".ajaxWorked").hide();
    }
}, 100)
function ajaxConnect(url,param,callback, async = true){
    if (async){
        workedAjax--;
    }

    $.ajax({
        url:url,
        data:param,
        method:'post',
        async:async,
        success:callback,
        error:function (e){
            let msg = "오류가 발생하였습니다.";
            if (e.responseJSON && e.responseJSON.message) msg = e.responseJSON.message;
        },
        complete:function (e){
            if (async){
                workedAjax++;
            }
        }
    });
}