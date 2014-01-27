$(document).bind('mobileinit',function(){
    $.mobile.page.prototype.options.keepNative = ".data-role-none, .data-role-none *";
    $.mobile.ajaxEnabled = false;
});
