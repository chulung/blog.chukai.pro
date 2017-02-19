/**
 * 文章详情页js
 */
define(function () {
    var exports = {};
    exports.boundSubmitComments = function () {
        $('#submitComments').one("click", function () {
            if (!validate(["#comment", "#userName", "#email"])) {
                boundSubmitComments();
                return;
            }
            $.ajax({
                url: '/comments',
                type: 'post',
                dataType: 'json',
                data: $("#commentform").serialize(),
                success: function (data) {
                    if (data.success == 1) {
                        exports.listComments();
                    } else {
                        alert(data.message);
                    }

                }
            });
        });
    };
    exports.listComments = function () {
        $.ajax({
            url: '/comments/list/' + $("input[name='articleId']").val(),
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var vm = new Vue({
                    data: result,
                    el: "#ul_comments"
                })
            }
        });
    };
    exports.listRelevancy = function () {
        $.ajax({
            url: '/article/relevancy/' + $("input[name='articleId']").val(),
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var vm = new Vue({
                    data: result,
                    el: '#related-posts',
                    methods: {
                        defaultPic: function (pic) {
                            return pic || '//static.chulung.com/group1/M00/00/00/cHx_F1b31x6ASf2iAAAfnIyLLQI109_150x150.jpg';
                        }
                    }
                })
            }
        });
    };
    exports.init = function () {
        exports.boundSubmitComments();
        $(window).scroll(function () {
            function isFirstScrollOn(id, callBack) {
                if (typeof(exports[id]) == "undefined" && !!($(window).scrollTop() > $(id).offset().top - 500)) {
                    callBack();
                    exports[id] = 1;
                }
            }

            isFirstScrollOn('#comments', exports.listComments);
            isFirstScrollOn('#related-top', exports.listRelevancy);

        });

    };
    return exports;
});
function validate(ids) {
    for (i = 0; i < ids.length; i++) {
        if (!$(ids[i]).val()) {
            $(ids[i]).focus();
            return false;
        }
    }
    return true;
}