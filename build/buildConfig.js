/**
 * requirejs 压缩配置，其中参数appDir baseUrl dir 定义在pom文件中
 */
({
    // CSS 优化方式，目前支持以下几种：
    // none: 不压缩，仅合并
    // standard: 标准压缩，移除注释、换行，以及可能导致 IE 解析出错的代码
    // standard.keepLines: 除标准压缩外，保留换行
    // standard.keepComments: 除标准压缩外，保留注释 (r.js 1.0.8+)
    // standard.keepComments.keepLines: 除标准压缩外，保留注释和换行 (r.js 1.0.8+)
    optimizeCss: "standard",
    removeCombined: true,//如果为true，将从输出目录中删除已合并的文件
    //打包配置path
    paths:{
        jquery:"empty:",//jquery不打包，global已自带
        main:"craft/js/main",
        global:"craft/js/global",
        article:"craft/js/article",
        sidebarInfo:"craft/js/sidebarInfo"
    },
    modules:[
        {
            name:"main"
            //这里将购物车所有依赖模块打包成一个cart_main.js供发布使用
        }
    ],
    // JS 文件优化方式，目前支持以下几种：
    //   uglify: （默认） 使用 UglifyJS 来压缩代码
    //   closure: 使用 Google's Closure Compiler 的简单优化模式
    //   closure.keepLines: 使用 closure，但保持换行
    //   none: 不压缩代码
    optimize: "uglify",

    // 使用 UglifyJS 时的可配置参数
    // See https://github.com/mishoo/UglifyJS for the possible values.
    // uglify: {
    //     toplevel: true
    // }

})
