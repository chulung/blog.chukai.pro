(function(){var k,n=this;function p(a){return void 0!==a}
function q(a,b,c){a=a.split(".");c=c||n;a[0]in c||!c.execScript||c.execScript("var "+a[0]);for(var d;a.length&&(d=a.shift());)!a.length&&p(b)?c[d]=b:c[d]?c=c[d]:c=c[d]={}}
function r(a,b){a=a.split(".");b=b||n;for(var c;c=a.shift();)if(null!=b[c])b=b[c];else return null;return b}
function t(){}
function aa(){throw Error("unimplemented abstract method");}
function ba(a){a.getInstance=function(){return a.Ia?a.Ia:a.Ia=new a}}
function ca(a){var b=typeof a;if("object"==b)if(a){if(a instanceof Array)return"array";if(a instanceof Object)return b;var c=Object.prototype.toString.call(a);if("[object Window]"==c)return"object";if("[object Array]"==c||"number"==typeof a.length&&"undefined"!=typeof a.splice&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("splice"))return"array";if("[object Function]"==c||"undefined"!=typeof a.call&&"undefined"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable("call"))return"function"}else return"null";
else if("function"==b&&"undefined"==typeof a.call)return"object";return b}
function da(a){return"array"==ca(a)}
function ea(a){var b=ca(a);return"array"==b||"object"==b&&"number"==typeof a.length}
function v(a){return"string"==typeof a}
function fa(a){return"function"==ca(a)}
function ga(a){var b=typeof a;return"object"==b&&null!=a||"function"==b}
function ha(a){return a[ja]||(a[ja]=++ka)}
var ja="closure_uid_"+(1E9*Math.random()>>>0),ka=0;function la(a,b,c){return a.call.apply(a.bind,arguments)}
function ma(a,b,c){if(!a)throw Error();if(2<arguments.length){var d=Array.prototype.slice.call(arguments,2);return function(){var c=Array.prototype.slice.call(arguments);Array.prototype.unshift.apply(c,d);return a.apply(b,c)}}return function(){return a.apply(b,arguments)}}
function w(a,b,c){w=Function.prototype.bind&&-1!=Function.prototype.bind.toString().indexOf("native code")?la:ma;return w.apply(null,arguments)}
function na(a,b){var c=Array.prototype.slice.call(arguments,1);return function(){var b=c.slice();b.push.apply(b,arguments);return a.apply(this,b)}}
function oa(a,b){for(var c in b)a[c]=b[c]}
var x=Date.now||function(){return+new Date};
function y(a,b){function c(){}
c.prototype=b.prototype;a.B=b.prototype;a.prototype=new c;a.prototype.constructor=a;a.ec=function(a,c,f){for(var d=Array(arguments.length-2),e=2;e<arguments.length;e++)d[e-2]=arguments[e];return b.prototype[c].apply(a,d)}}
;function pa(a){if(Error.captureStackTrace)Error.captureStackTrace(this,pa);else{var b=Error().stack;b&&(this.stack=b)}a&&(this.message=String(a))}
y(pa,Error);pa.prototype.name="CustomError";var qa;function ra(a){return/^[\s\xa0]*$/.test(a)}
var sa=String.prototype.trim?function(a){return a.trim()}:function(a){return a.replace(/^[\s\xa0]+|[\s\xa0]+$/g,"")};
function ta(a,b){return a<b?-1:a>b?1:0}
function ua(a){for(var b=0,c=0;c<a.length;++c)b=31*b+a.charCodeAt(c)>>>0;return b}
;var va=Array.prototype.indexOf?function(a,b,c){return Array.prototype.indexOf.call(a,b,c)}:function(a,b,c){c=null==c?0:0>c?Math.max(0,a.length+c):c;
if(v(a))return v(b)&&1==b.length?a.indexOf(b,c):-1;for(;c<a.length;c++)if(c in a&&a[c]===b)return c;return-1},z=Array.prototype.forEach?function(a,b,c){Array.prototype.forEach.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=v(a)?a.split(""):a,f=0;f<d;f++)f in e&&b.call(c,e[f],f,a)},wa=Array.prototype.filter?function(a,b,c){return Array.prototype.filter.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=[],f=0,g=v(a)?a.split(""):a,l=0;l<d;l++)if(l in g){var h=g[l];
b.call(c,h,l,a)&&(e[f++]=h)}return e},xa=Array.prototype.map?function(a,b,c){return Array.prototype.map.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=Array(d),f=v(a)?a.split(""):a,g=0;g<d;g++)g in f&&(e[g]=b.call(c,f[g],g,a));
return e},ya=Array.prototype.some?function(a,b,c){return Array.prototype.some.call(a,b,c)}:function(a,b,c){for(var d=a.length,e=v(a)?a.split(""):a,f=0;f<d;f++)if(f in e&&b.call(c,e[f],f,a))return!0;
return!1};
function za(a,b){a:{for(var c=a.length,d=v(a)?a.split(""):a,e=0;e<c;e++)if(e in d&&b.call(void 0,d[e],e,a)){b=e;break a}b=-1}return 0>b?null:v(a)?a.charAt(b):a[b]}
function Aa(a,b){return 0<=va(a,b)}
function Ca(a,b){b=va(a,b);0<=b&&Array.prototype.splice.call(a,b,1)}
function Da(a){return Array.prototype.concat.apply(Array.prototype,arguments)}
function Ea(a){var b=a.length;if(0<b){for(var c=Array(b),d=0;d<b;d++)c[d]=a[d];return c}return[]}
function Fa(a,b){for(var c=1;c<arguments.length;c++){var d=arguments[c];if(ea(d)){var e=a.length||0,f=d.length||0;a.length=e+f;for(var g=0;g<f;g++)a[e+g]=d[g]}else a.push(d)}}
function Ga(a,b,c,d){return Array.prototype.splice.apply(a,Ja(arguments,1))}
function Ja(a,b,c){return 2>=arguments.length?Array.prototype.slice.call(a,b):Array.prototype.slice.call(a,b,c)}
;function Ka(a,b){for(var c in a)b.call(void 0,a[c],c,a)}
function La(a){var b=Ma,c;for(c in b)if(a.call(void 0,b[c],c,b))return c}
function Na(){var a=Oa,b;for(b in a)return!1;return!0}
function Pa(a,b){if(null!==a&&b in a)throw Error('The object already contains the key "'+b+'"');a[b]=!0}
function Qa(a){var b={},c;for(c in a)b[c]=a[c];return b}
var Ra="constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" ");function Sa(a,b){for(var c,d,e=1;e<arguments.length;e++){d=arguments[e];for(c in d)a[c]=d[c];for(var f=0;f<Ra.length;f++)c=Ra[f],Object.prototype.hasOwnProperty.call(d,c)&&(a[c]=d[c])}}
;var A;a:{var Ta=n.navigator;if(Ta){var Ua=Ta.userAgent;if(Ua){A=Ua;break a}}A=""}function B(a){return-1!=A.indexOf(a)}
;function Va(){return(B("Chrome")||B("CriOS"))&&!B("Edge")}
;function Wa(){this.b="";this.f=Xa}
Wa.prototype.ua=!0;Wa.prototype.ta=function(){return this.b};
function Ya(a){return a instanceof Wa&&a.constructor===Wa&&a.f===Xa?a.b:"type_error:SafeUrl"}
var Za=/^(?:(?:https?|mailto|ftp):|[^&:/?#]*(?:[/?#]|$))/i;function $a(a){if(a instanceof Wa)return a;a=a.ua?a.ta():String(a);Za.test(a)||(a="about:invalid#zClosurez");return ab(a)}
var Xa={};function ab(a){var b=new Wa;b.b=a;return b}
ab("about:blank");function bb(){this.b="";this.f=cb}
bb.prototype.ua=!0;bb.prototype.ta=function(){return this.b};
var cb={};function db(){this.b=""}
db.prototype.ua=!0;db.prototype.ta=function(){return this.b};
function eb(a){var b=new db;b.b=a;return b}
eb("<!DOCTYPE html>");eb("");eb("<br>");function fb(a,b){b=b instanceof Wa?b:$a(b);a.href=Ya(b)}
function gb(a,b){a.rel="stylesheet";a.href=b instanceof bb&&b.constructor===bb&&b.f===cb?b.b:"type_error:TrustedResourceUrl"}
;function hb(a,b,c){a&&(a.dataset?a.dataset[ib(b)]=c:a.setAttribute("data-"+b,c))}
function C(a,b){return a?a.dataset?a.dataset[ib(b)]:a.getAttribute("data-"+b):null}
function jb(a,b){a&&(a.dataset?delete a.dataset[ib(b)]:a.removeAttribute("data-"+b))}
var kb={};function ib(a){return kb[a]||(kb[a]=String(a).replace(/\-([a-z])/g,function(a,c){return c.toUpperCase()}))}
;function lb(a){n.setTimeout(function(){throw a;},0)}
var mb;
function nb(){var a=n.MessageChannel;"undefined"===typeof a&&"undefined"!==typeof window&&window.postMessage&&window.addEventListener&&!B("Presto")&&(a=function(){var a=document.createElement("IFRAME");a.style.display="none";a.src="";document.documentElement.appendChild(a);var b=a.contentWindow,a=b.document;a.open();a.write("");a.close();var c="callImmediate"+Math.random(),d="file:"==b.location.protocol?"*":b.location.protocol+"//"+b.location.host,a=w(function(a){if(("*"==d||a.origin==d)&&a.data==
c)this.port1.onmessage()},this);
b.addEventListener("message",a,!1);this.port1={};this.port2={postMessage:function(){b.postMessage(c,d)}}});
if("undefined"!==typeof a&&!B("Trident")&&!B("MSIE")){var b=new a,c={},d=c;b.port1.onmessage=function(){if(p(c.next)){c=c.next;var a=c.Ga;c.Ga=null;a()}};
return function(a){d.next={Ga:a};d=d.next;b.port2.postMessage(0)}}return"undefined"!==typeof document&&"onreadystatechange"in document.createElement("SCRIPT")?function(a){var b=document.createElement("SCRIPT");
b.onreadystatechange=function(){b.onreadystatechange=null;b.parentNode.removeChild(b);b=null;a();a=null};
document.documentElement.appendChild(b)}:function(a){n.setTimeout(a,0)}}
;function ob(a,b,c){this.i=c;this.g=a;this.j=b;this.f=0;this.b=null}
ob.prototype.get=function(){var a;0<this.f?(this.f--,a=this.b,this.b=a.next,a.next=null):a=this.g();return a};
function pb(a,b){a.j(b);a.f<a.i&&(a.f++,b.next=a.b,a.b=b)}
;function qb(){this.f=this.b=null}
var sb=new ob(function(){return new rb},function(a){a.reset()},100);
qb.prototype.remove=function(){var a=null;this.b&&(a=this.b,this.b=this.b.next,this.b||(this.f=null),a.next=null);return a};
function rb(){this.next=this.scope=this.b=null}
rb.prototype.set=function(a,b){this.b=a;this.scope=b;this.next=null};
rb.prototype.reset=function(){this.next=this.scope=this.b=null};function tb(a,b){ub||vb();wb||(ub(),wb=!0);var c=xb,d=sb.get();d.set(a,b);c.f?c.f.next=d:c.b=d;c.f=d}
var ub;function vb(){var a=n.Promise;if(-1!=String(a).indexOf("[native code]")){var b=a.resolve(void 0);ub=function(){b.then(yb)}}else ub=function(){var a=yb;
!fa(n.setImmediate)||n.Window&&n.Window.prototype&&!B("Edge")&&n.Window.prototype.setImmediate==n.setImmediate?(mb||(mb=nb()),mb(a)):n.setImmediate(a)}}
var wb=!1,xb=new qb;function yb(){for(var a;a=xb.remove();){try{a.b.call(a.scope)}catch(b){lb(b)}pb(sb,a)}wb=!1}
;function D(){this.f=this.f;this.K=this.K}
D.prototype.f=!1;D.prototype.dispose=function(){this.f||(this.f=!0,this.A())};
function zb(a,b){a.f?p(void 0)?b.call(void 0):b():(a.K||(a.K=[]),a.K.push(p(void 0)?w(b,void 0):b))}
D.prototype.A=function(){if(this.K)for(;this.K.length;)this.K.shift()()};
function Ab(a){a&&"function"==typeof a.dispose&&a.dispose()}
function Bb(a){for(var b=0,c=arguments.length;b<c;++b){var d=arguments[b];ea(d)?Bb.apply(null,d):Ab(d)}}
;function E(a){D.call(this);this.j=1;this.g=[];this.i=0;this.b=[];this.H={};this.w=!!a}
y(E,D);k=E.prototype;k.subscribe=function(a,b,c){var d=this.H[a];d||(d=this.H[a]=[]);var e=this.j;this.b[e]=a;this.b[e+1]=b;this.b[e+2]=c;this.j=e+3;d.push(e);return e};
k.unsubscribe=function(a,b,c){if(a=this.H[a]){var d=this.b;if(a=za(a,function(a){return d[a+1]==b&&d[a+2]==c}))return this.T(a)}return!1};
k.T=function(a){var b=this.b[a];if(b){var c=this.H[b];0!=this.i?(this.g.push(a),this.b[a+1]=t):(c&&Ca(c,a),delete this.b[a],delete this.b[a+1],delete this.b[a+2])}return!!b};
k.N=function(a,b){var c=this.H[a];if(c){for(var d=Array(arguments.length-1),e=1,f=arguments.length;e<f;e++)d[e-1]=arguments[e];if(this.w)for(e=0;e<c.length;e++){var g=c[e];Cb(this.b[g+1],this.b[g+2],d)}else{this.i++;try{for(e=0,f=c.length;e<f;e++)g=c[e],this.b[g+1].apply(this.b[g+2],d)}finally{if(this.i--,0<this.g.length&&0==this.i)for(;c=this.g.pop();)this.T(c)}}return 0!=e}return!1};
function Cb(a,b,c){tb(function(){a.apply(b,c)})}
k.clear=function(a){if(a){var b=this.H[a];b&&(z(b,this.T,this),delete this.H[a])}else this.b.length=0,this.H={}};
k.ba=function(a){if(a){var b=this.H[a];return b?b.length:0}a=0;for(b in this.H)a+=this.ba(b);return a};
k.A=function(){E.B.A.call(this);this.clear();this.g.length=0};var Db=window.yt&&window.yt.config_||window.ytcfg&&window.ytcfg.data_||{};q("yt.config_",Db,void 0);q("yt.tokens_",window.yt&&window.yt.tokens_||{},void 0);var Eb=window.yt&&window.yt.msgs_||r("window.ytcfg.msgs")||{};q("yt.msgs_",Eb,void 0);function F(a){Fb(Db,arguments)}
function G(a,b){return a in Db?Db[a]:b}
function H(a,b){fa(a)&&(a=Gb(a));return window.setTimeout(a,b)}
function Hb(a){window.clearTimeout(a)}
function Gb(a){return a&&window.yterr?function(){try{return a.apply(this,arguments)}catch(b){Ib(b)}}:a}
function Ib(a,b){var c=r("yt.logging.errors.log");c?c(a,b,void 0,void 0,void 0):(c=G("ERRORS",[]),c.push([a,b,void 0,void 0,void 0]),F("ERRORS",c))}
function Fb(a,b){if(1<b.length){var c=b[0];a[c]=b[1]}else for(c in b=b[0],b)a[c]=b[c]}
var Jb=window.performance&&window.performance.timing&&window.performance.now?function(){return window.performance.timing.navigationStart+window.performance.now()}:function(){return(new Date).getTime()},Kb="Microsoft Internet Explorer"==navigator.appName;var Lb=r("yt.pubsub.instance_")||new E;E.prototype.subscribe=E.prototype.subscribe;E.prototype.unsubscribeByKey=E.prototype.T;E.prototype.publish=E.prototype.N;E.prototype.clear=E.prototype.clear;q("yt.pubsub.instance_",Lb,void 0);var Mb=r("yt.pubsub.subscribedKeys_")||{};q("yt.pubsub.subscribedKeys_",Mb,void 0);var Nb=r("yt.pubsub.topicToKeys_")||{};q("yt.pubsub.topicToKeys_",Nb,void 0);var Ob=r("yt.pubsub.isSynchronous_")||{};q("yt.pubsub.isSynchronous_",Ob,void 0);
var Pb=r("yt.pubsub.skipSubId_")||null;q("yt.pubsub.skipSubId_",Pb,void 0);function Qb(a,b,c){var d=Rb();if(d){var e=d.subscribe(a,function(){if(!Pb||Pb!=e){var d=arguments,g;g=function(){Mb[e]&&b.apply(c||window,d)};
try{Ob[a]?g():H(g,0)}catch(l){Ib(l)}}},c);
Mb[e]=!0;Nb[a]||(Nb[a]=[]);Nb[a].push(e);return e}return 0}
function Sb(a){var b=Rb();b&&("number"==typeof a?a=[a]:"string"==typeof a&&(a=[parseInt(a,10)]),z(a,function(a){b.unsubscribeByKey(a);delete Mb[a]}))}
function Tb(a,b){var c=Rb();return c?c.publish.apply(c,arguments):!1}
function Ub(a){Nb[a]&&(a=Nb[a],z(a,function(a){Mb[a]&&delete Mb[a]}),a.length=0)}
function Vb(a){var b=Rb();if(b)if(b.clear(a),a)Ub(a);else for(var c in Nb)Ub(c)}
function Rb(){return r("yt.pubsub.instance_")}
;function Wb(a,b){if(window.spf){var c="";if(a){var d=a.indexOf("jsbin/"),e=a.lastIndexOf(".js"),f=d+6;-1<d&&-1<e&&e>f&&(c=a.substring(f,e),c=c.replace(Xb,""),c=c.replace(Yb,""),c=c.replace("debug-",""),c=c.replace("tracing-",""))}spf.script.load(a,c,b)}else Zb(a,b)}
function Zb(a,b){var c=$b(a),d=document.getElementById(c),e=d&&C(d,"loaded"),f=d&&!e;e?b&&b():(b&&(e=Qb(c,b),b=""+ha(b),ac[b]=e),f||(d=bc(a,c,function(){C(d,"loaded")||(hb(d,"loaded","true"),Tb(c),H(na(Vb,c),0))})))}
function bc(a,b,c){var d=document.createElement("script");d.id=b;d.onload=function(){c&&setTimeout(c,0)};
d.onreadystatechange=function(){switch(d.readyState){case "loaded":case "complete":d.onload()}};
d.src=a;a=document.getElementsByTagName("head")[0]||document.body;a.insertBefore(d,a.firstChild);return d}
function cc(a,b){a&&b&&(a=""+ha(b),(a=ac[a])&&Sb(a))}
function $b(a){var b=document.createElement("a");fb(b,a);a=b.href.replace(/^[a-zA-Z]+:\/\//,"//");return"js-"+ua(a)}
var Xb=/\.vflset|-vfl[a-zA-Z0-9_+=-]+/,Yb=/-[a-zA-Z]{2,3}_[a-zA-Z]{2,3}(?=(\/|$))/,ac={};var dc=null;function ec(){var a=G("BG_I",null),b=G("BG_IU",null),c=G("BG_P",void 0);b?Wb(b,function(){dc=new botguard.bg(c)}):a&&(eval(a),dc=new botguard.bg(c))}
function fc(){return null!=dc}
function gc(){return dc?dc.invoke():null}
;var hc="StopIteration"in n?n.StopIteration:{message:"StopIteration",stack:""};function ic(){}
ic.prototype.next=function(){throw hc;};
ic.prototype.U=function(){return this};
function jc(a){if(a instanceof ic)return a;if("function"==typeof a.U)return a.U(!1);if(ea(a)){var b=0,c=new ic;c.next=function(){for(;;){if(b>=a.length)throw hc;if(b in a)return a[b++];b++}};
return c}throw Error("Not implemented");}
function kc(a,b){if(ea(a))try{z(a,b,void 0)}catch(c){if(c!==hc)throw c;}else{a=jc(a);try{for(;;)b.call(void 0,a.next(),void 0,a)}catch(c){if(c!==hc)throw c;}}}
function lc(a){if(ea(a))return Ea(a);a=jc(a);var b=[];kc(a,function(a){b.push(a)});
return b}
;function mc(){return B("iPhone")&&!B("iPod")&&!B("iPad")}
;function nc(a,b){var c=oc;return Object.prototype.hasOwnProperty.call(c,a)?c[a]:c[a]=b(a)}
;var pc=B("Opera"),I=B("Trident")||B("MSIE"),rc=B("Edge"),sc=B("Gecko")&&!(-1!=A.toLowerCase().indexOf("webkit")&&!B("Edge"))&&!(B("Trident")||B("MSIE"))&&!B("Edge"),tc=-1!=A.toLowerCase().indexOf("webkit")&&!B("Edge"),uc=B("Windows");function vc(){var a=n.document;return a?a.documentMode:void 0}
var wc;a:{var xc="",yc=function(){var a=A;if(sc)return/rv\:([^\);]+)(\)|;)/.exec(a);if(rc)return/Edge\/([\d\.]+)/.exec(a);if(I)return/\b(?:MSIE|rv)[: ]([^\);]+)(\)|;)/.exec(a);if(tc)return/WebKit\/(\S+)/.exec(a);if(pc)return/(?:Version)[ \/]?(\S+)/.exec(a)}();
yc&&(xc=yc?yc[1]:"");if(I){var zc=vc();if(null!=zc&&zc>parseFloat(xc)){wc=String(zc);break a}}wc=xc}var Ac=wc,oc={};
function J(a){return nc(a,function(){for(var b=0,c=sa(String(Ac)).split("."),d=sa(String(a)).split("."),e=Math.max(c.length,d.length),f=0;0==b&&f<e;f++){var g=c[f]||"",l=d[f]||"";do{g=/(\d*)(\D*)(.*)/.exec(g)||["","","",""];l=/(\d*)(\D*)(.*)/.exec(l)||["","","",""];if(0==g[0].length&&0==l[0].length)break;b=ta(0==g[1].length?0:parseInt(g[1],10),0==l[1].length?0:parseInt(l[1],10))||ta(0==g[2].length,0==l[2].length)||ta(g[2],l[2]);g=g[3];l=l[3]}while(0==b)}return 0<=b})}
var Bc;var Cc=n.document;Bc=Cc&&I?vc()||("CSS1Compat"==Cc.compatMode?parseInt(Ac,10):5):void 0;function Dc(a){return/^\s*$/.test(a)?!1:/^[\],:{}\s\u2028\u2029]*$/.test(a.replace(/\\["\\\/bfnrtu]/g,"@").replace(/(?:"[^"\\\n\r\u2028\u2029\x00-\x08\x0a-\x1f]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)[\s\u2028\u2029]*(?=:|,|]|}|$)/g,"]").replace(/(?:^|:|,)(?:[\s\u2028\u2029]*\[)+/g,""))}
function Ec(a){a=String(a);if(Dc(a))try{return eval("("+a+")")}catch(b){}throw Error("Invalid JSON string: "+a);}
function Fc(a){return eval("("+a+")")}
function Gc(a){var b=[];Hc(new Ic,a,b);return b.join("")}
function Ic(){}
function Hc(a,b,c){if(null==b)c.push("null");else{if("object"==typeof b){if(da(b)){var d=b;b=d.length;c.push("[");for(var e="",f=0;f<b;f++)c.push(e),e=d[f],Hc(a,e,c),e=",";c.push("]");return}if(b instanceof String||b instanceof Number||b instanceof Boolean)b=b.valueOf();else{c.push("{");f="";for(d in b)Object.prototype.hasOwnProperty.call(b,d)&&(e=b[d],"function"!=typeof e&&(c.push(f),Jc(d,c),c.push(":"),Hc(a,e,c),f=","));c.push("}");return}}switch(typeof b){case "string":Jc(b,c);break;case "number":c.push(isFinite(b)&&
!isNaN(b)?String(b):"null");break;case "boolean":c.push(String(b));break;case "function":c.push("null");break;default:throw Error("Unknown type: "+typeof b);}}}
var Kc={'"':'\\"',"\\":"\\\\","/":"\\/","\b":"\\b","\f":"\\f","\n":"\\n","\r":"\\r","\t":"\\t","\x0B":"\\u000b"},Lc=/\uffff/.test("\uffff")?/[\\\"\x00-\x1f\x7f-\uffff]/g:/[\\\"\x00-\x1f\x7f-\xff]/g;function Jc(a,b){b.push('"',a.replace(Lc,function(a){var b=Kc[a];b||(b="\\u"+(a.charCodeAt(0)|65536).toString(16).substr(1),Kc[a]=b);return b}),'"')}
;var Mc=/^(?:([^:/?#.]+):)?(?:\/\/(?:([^/?#]*)@)?([^/#?]*?)(?::([0-9]+))?(?=[/#?]|$))?([^?#]+)?(?:\?([^#]*))?(?:#([\s\S]*))?$/;function K(a){return a.match(Mc)}
function Nc(a){return a?decodeURI(a):a}
function Oc(a){if(a[1]){var b=a[0],c=b.indexOf("#");0<=c&&(a.push(b.substr(c)),a[0]=b=b.substr(0,c));c=b.indexOf("?");0>c?a[1]="?":c==b.length-1&&(a[1]=void 0)}return a.join("")}
function Pc(a,b,c){if(da(b))for(var d=0;d<b.length;d++)Pc(a,String(b[d]),c);else null!=b&&c.push("&",a,""===b?"":"=",encodeURIComponent(String(b)))}
function Qc(a,b,c){for(c=c||0;c<b.length;c+=2)Pc(b[c],b[c+1],a);return a}
function Rc(a,b){for(var c in b)Pc(c,b[c],a);return a}
function Sc(a){a=Rc([],a);a[0]="";return a.join("")}
function Tc(a,b){return Oc(2==arguments.length?Qc([a],arguments[1],0):Qc([a],arguments,1))}
;function Uc(a){"?"==a.charAt(0)&&(a=a.substr(1));a=a.split("&");for(var b={},c=0,d=a.length;c<d;c++){var e=a[c].split("=");if(1==e.length&&e[0]||2==e.length){var f=decodeURIComponent((e[0]||"").replace(/\+/g," ")),e=decodeURIComponent((e[1]||"").replace(/\+/g," "));f in b?da(b[f])?Fa(b[f],e):b[f]=[b[f],e]:b[f]=e}}return b}
function Vc(a,b){var c=a.split("#",2);a=c[0];var c=1<c.length?"#"+c[1]:"",d=a.split("?",2);a=d[0];var d=Uc(d[1]||""),e;for(e in b)d[e]=b[e];return Oc(Rc([a],d))+c}
;var Wc=null;"undefined"!=typeof XMLHttpRequest?Wc=function(){return new XMLHttpRequest}:"undefined"!=typeof ActiveXObject&&(Wc=function(){return new ActiveXObject("Microsoft.XMLHTTP")});
function Xc(a){switch(a&&"status"in a?a.status:-1){case 200:case 201:case 202:case 203:case 204:case 205:case 206:case 304:return!0;default:return!1}}
;function Yc(a,b,c,d,e,f,g){function l(){4==(h&&"readyState"in h?h.readyState:0)&&b&&Gb(b)(h)}
var h=Wc&&Wc();if(!("open"in h))return null;"onloadend"in h?h.addEventListener("loadend",l,!1):h.onreadystatechange=l;c=(c||"GET").toUpperCase();d=d||"";h.open(c,a,!0);f&&(h.responseType=f);g&&(h.withCredentials=!0);f="POST"==c;if(e=Zc(a,e))for(var m in e)h.setRequestHeader(m,e[m]),"content-type"==m.toLowerCase()&&(f=!1);f&&h.setRequestHeader("Content-Type","application/x-www-form-urlencoded");h.send(d);return h}
function Zc(a,b){b=b||{};var c;c||(c=window.location.href);var d=K(a)[1]||null,e=Nc(K(a)[3]||null);d&&e?(d=c,c=K(a),d=K(d),c=c[3]==d[3]&&c[1]==d[1]&&c[4]==d[4]):c=e?Nc(K(c)[3]||null)==e&&(Number(K(c)[4]||null)||null)==(Number(K(a)[4]||null)||null):!0;for(var f in $c){if((e=d=G($c[f]))&&!(e=c)){var e=f,g=G("CORS_HEADER_WHITELIST")||{},l=Nc(K(a)[3]||null);e=l?(g=g[l])?Aa(g,e):!1:!0}e&&(b[f]=d)}return b}
function ad(a,b){b.method="POST";b.C||(b.C={});bd(a,b)}
function cd(a,b){var c=G("XSRF_FIELD_NAME",void 0),d;b.headers&&(d=b.headers["Content-Type"]);return!b.gc&&(!Nc(K(a)[3]||null)||b.withCredentials||Nc(K(a)[3]||null)==document.location.hostname)&&"POST"==b.method&&(!d||"application/x-www-form-urlencoded"==d)&&!(b.C&&b.C[c])}
function bd(a,b){var c=b.format||"JSON";b.sb&&(a=document.location.protocol+"//"+document.location.hostname+(document.location.port?":"+document.location.port:"")+a);var d=G("XSRF_FIELD_NAME",void 0),e=G("XSRF_TOKEN",void 0),f=b.wa;f&&(f[d]&&delete f[d],a=Vc(a,f||{}));var g=b.postBody||"",f=b.C;cd(a,b)&&(f||(f={}),f[d]=e);f&&v(g)&&(d=Uc(g),Sa(d,f),g=b.Oa&&"JSON"==b.Oa?JSON.stringify(d):Sc(d));var l=!1,h,m=Yc(a,function(a){if(!l){l=!0;h&&Hb(h);var d=Xc(a),e=null;if(d||400<=a.status&&500>a.status)e=
dd(c,a,b.fc);if(d)a:if(204==a.status)d=!0;else{switch(c){case "XML":d=0==parseInt(e&&e.return_code,10);break a;case "RAW":d=!0;break a}d=!!e}var e=e||{},f=b.context||n;d?b.F&&b.F.call(f,a,e):b.onError&&b.onError.call(f,a,e);b.va&&b.va.call(f,a,e)}},b.method,g,b.headers,b.responseType,b.withCredentials);
b.R&&0<b.timeout&&(h=H(function(){l||(l=!0,m.abort(),Hb(h),b.R.call(b.context||n,m))},b.timeout))}
function dd(a,b,c){var d=null;switch(a){case "JSON":a=b.responseText;b=b.getResponseHeader("Content-Type")||"";a&&0<=b.indexOf("json")&&(d=Fc(a));break;case "XML":if(b=(b=b.responseXML)?ed(b):null)d={},z(b.getElementsByTagName("*"),function(a){d[a.tagName]=fd(a)})}c&&gd(d);
return d}
function gd(a){if(ga(a))for(var b in a){var c;(c="html_content"==b)||(c=b.length-5,c=0<=c&&b.indexOf("_html",c)==c);if(c){c=b;var d;d=eb(a[b]);a[c]=d}else gd(a[b])}}
function ed(a){return a?(a=("responseXML"in a?a.responseXML:a).getElementsByTagName("root"))&&0<a.length?a[0]:null:null}
function fd(a){var b="";z(a.childNodes,function(a){b+=a.nodeValue});
return b}
var $c={"X-YouTube-Client-Name":"INNERTUBE_CONTEXT_CLIENT_NAME","X-YouTube-Client-Version":"INNERTUBE_CONTEXT_CLIENT_VERSION","X-Youtube-Identity-Token":"ID_TOKEN","X-YouTube-Page-CL":"PAGE_CL","X-YouTube-Page-Label":"PAGE_BUILD_LABEL","X-YouTube-Variants-Checksum":"VARIANTS_CHECKSUM"};var hd={},id=0;function jd(a,b){isNaN(b)&&(b=void 0);var c=r("yt.scheduler.instance.addJob");return c?c(a,1,b):void 0===b?(a(),NaN):H(a,b||0)}
;var kd=[],ld=!1;function md(){function a(){ld=!0;"google_ad_status"in window?F("DCLKSTAT",1):F("DCLKSTAT",2)}
Wb("//static.doubleclick.net/instream/ad_status.js",a);kd.push(jd(function(){ld||"google_ad_status"in window||(cc("//static.doubleclick.net/instream/ad_status.js",a),F("DCLKSTAT",3))},5E3))}
function nd(){return parseInt(G("DCLKSTAT",0),10)}
;function od(a,b){this.width=a;this.height=b}
k=od.prototype;k.aspectRatio=function(){return this.width/this.height};
k.isEmpty=function(){return!(this.width*this.height)};
k.ceil=function(){this.width=Math.ceil(this.width);this.height=Math.ceil(this.height);return this};
k.floor=function(){this.width=Math.floor(this.width);this.height=Math.floor(this.height);return this};
k.round=function(){this.width=Math.round(this.width);this.height=Math.round(this.height);return this};function pd(a){this.type="";this.state=this.source=this.data=this.currentTarget=this.relatedTarget=this.target=null;this.charCode=this.keyCode=0;this.shiftKey=this.ctrlKey=this.altKey=!1;this.clientY=this.clientX=0;this.changedTouches=null;if(a=a||window.event){this.event=a;for(var b in a)b in qd||(this[b]=a[b]);(b=a.target||a.srcElement)&&3==b.nodeType&&(b=b.parentNode);this.target=b;if(b=a.relatedTarget)try{b=b.nodeName?b:null}catch(c){b=null}else"mouseover"==this.type?b=a.fromElement:"mouseout"==
this.type&&(b=a.toElement);this.relatedTarget=b;this.clientX=void 0!=a.clientX?a.clientX:a.pageX;this.clientY=void 0!=a.clientY?a.clientY:a.pageY;this.keyCode=a.keyCode?a.keyCode:a.which;this.charCode=a.charCode||("keypress"==this.type?this.keyCode:0);this.altKey=a.altKey;this.ctrlKey=a.ctrlKey;this.shiftKey=a.shiftKey}}
pd.prototype.preventDefault=function(){this.event&&(this.event.returnValue=!1,this.event.preventDefault&&this.event.preventDefault())};
pd.prototype.stopPropagation=function(){this.event&&(this.event.cancelBubble=!0,this.event.stopPropagation&&this.event.stopPropagation())};
pd.prototype.stopImmediatePropagation=function(){this.event&&(this.event.cancelBubble=!0,this.event.stopImmediatePropagation&&this.event.stopImmediatePropagation())};
var qd={stopImmediatePropagation:1,stopPropagation:1,preventMouseEvent:1,preventManipulation:1,preventDefault:1,layerX:1,layerY:1,scale:1,rotation:1,webkitMovementX:1,webkitMovementY:1};function rd(a){if(a.classList)return a.classList;a=a.className;return v(a)&&a.match(/\S+/g)||[]}
function sd(a,b){return a.classList?a.classList.contains(b):Aa(rd(a),b)}
function td(a,b){a.classList?a.classList.add(b):sd(a,b)||(a.className+=0<a.className.length?" "+b:b)}
function ud(a,b){a.classList?a.classList.remove(b):sd(a,b)&&(a.className=wa(rd(a),function(a){return a!=b}).join(" "))}
function vd(a,b,c){c?td(a,b):ud(a,b)}
;var wd=x().toString();function L(a,b){this.l=p(a)?a:0;this.o=p(b)?b:0}
L.prototype.equals=function(a){return a instanceof L&&(this==a?!0:this&&a?this.l==a.l&&this.o==a.o:!1)};
L.prototype.ceil=function(){this.l=Math.ceil(this.l);this.o=Math.ceil(this.o);return this};
L.prototype.floor=function(){this.l=Math.floor(this.l);this.o=Math.floor(this.o);return this};
L.prototype.round=function(){this.l=Math.round(this.l);this.o=Math.round(this.o);return this};!sc&&!I||I&&9<=Number(Bc)||sc&&J("1.9.1");var xd=I&&!J("9");function yd(a){zd();var b=new bb;b.b=a;return b}
var zd=t;function Ad(a){return a?new Bd(Dd(a)):qa||(qa=new Bd)}
function Ed(a){var b=document;return v(a)?b.getElementById(a):a}
function Fd(a){var b=document;return b.querySelectorAll&&b.querySelector?b.querySelectorAll("."+a):Gd(a)}
function Gd(a){var b,c,d,e;b=document;if(b.querySelectorAll&&b.querySelector&&a)return b.querySelectorAll(""+(a?"."+a:""));if(a&&b.getElementsByClassName){var f=b.getElementsByClassName(a);return f}f=b.getElementsByTagName("*");if(a){e={};for(c=d=0;b=f[c];c++){var g=b.className;"function"==typeof g.split&&Aa(g.split(/\s+/),a)&&(e[d++]=b)}e.length=d;return e}return f}
function Hd(a){a=a.document;a=Id(a)?a.documentElement:a.body;return new od(a.clientWidth,a.clientHeight)}
function Jd(a){var b=a.scrollingElement?a.scrollingElement:!tc&&Id(a)?a.documentElement:a.body||a.documentElement;a=a.parentWindow||a.defaultView;return I&&J("10")&&a.pageYOffset!=b.scrollTop?new L(b.scrollLeft,b.scrollTop):new L(a.pageXOffset||b.scrollLeft,a.pageYOffset||b.scrollTop)}
function Id(a){return"CSS1Compat"==a.compatMode}
function Kd(a){for(var b;b=a.firstChild;)a.removeChild(b)}
function Ld(a){if(!a)return null;if(a.firstChild)return a.firstChild;for(;a&&!a.nextSibling;)a=a.parentNode;return a?a.nextSibling:null}
function Md(a){if(!a)return null;if(!a.previousSibling)return a.parentNode;for(a=a.previousSibling;a&&a.lastChild;)a=a.lastChild;return a}
function Dd(a){return 9==a.nodeType?a:a.ownerDocument||a.document}
function Nd(a,b){if("textContent"in a)a.textContent=b;else if(3==a.nodeType)a.data=b;else if(a.firstChild&&3==a.firstChild.nodeType){for(;a.lastChild!=a.firstChild;)a.removeChild(a.lastChild);a.firstChild.data=b}else Kd(a),a.appendChild(Dd(a).createTextNode(String(b)))}
var Od={SCRIPT:1,STYLE:1,HEAD:1,IFRAME:1,OBJECT:1},Pd={IMG:" ",BR:"\n"};function Qd(a){if(xd&&null!==a&&"innerText"in a)a=a.innerText.replace(/(\r\n|\r|\n)/g,"\n");else{var b=[];Rd(a,b,!0);a=b.join("")}a=a.replace(/ \xAD /g," ").replace(/\xAD/g,"");a=a.replace(/\u200B/g,"");xd||(a=a.replace(/ +/g," "));" "!=a&&(a=a.replace(/^\s*/,""));return a}
function Rd(a,b,c){if(!(a.nodeName in Od))if(3==a.nodeType)c?b.push(String(a.nodeValue).replace(/(\r\n|\r|\n)/g,"")):b.push(a.nodeValue);else if(a.nodeName in Pd)b.push(Pd[a.nodeName]);else for(a=a.firstChild;a;)Rd(a,b,c),a=a.nextSibling}
function Sd(a){var b=Td.ab;return b?Ud(a,function(a){return!b||v(a.className)&&Aa(a.className.split(/\s+/),b)},!0,void 0):null}
function Ud(a,b,c,d){c||(a=a.parentNode);for(c=0;a&&(null==d||c<=d);){if(b(a))return a;a=a.parentNode;c++}return null}
function Bd(a){this.b=a||n.document||document}
Bd.prototype.getElementsByTagName=function(a,b){return(b||this.b).getElementsByTagName(String(a))};
Bd.prototype.createElement=function(a){return this.b.createElement(String(a))};
Bd.prototype.isElement=function(a){return ga(a)&&1==a.nodeType};
Bd.prototype.contains=function(a,b){if(!a||!b)return!1;if(a.contains&&1==b.nodeType)return a==b||a.contains(b);if("undefined"!=typeof a.compareDocumentPosition)return a==b||!!(a.compareDocumentPosition(b)&16);for(;b&&a!=b;)b=b.parentNode;return b==a};var Vd=r("yt.dom.getNextId_");if(!Vd){Vd=function(){return++Wd};
q("yt.dom.getNextId_",Vd,void 0);var Wd=0}function Xd(){var a=document,b;ya(["fullscreenElement","webkitFullscreenElement","mozFullScreenElement","msFullscreenElement"],function(c){b=a[c];return!!b});
return b}
;var Ma=r("yt.events.listeners_")||{};q("yt.events.listeners_",Ma,void 0);var Yd=r("yt.events.counter_")||{count:0};q("yt.events.counter_",Yd,void 0);function Zd(a,b,c,d){a.addEventListener&&("mouseenter"!=b||"onmouseenter"in document?"mouseleave"!=b||"onmouseenter"in document?"mousewheel"==b&&"MozBoxSizing"in document.documentElement.style&&(b="MozMousePixelScroll"):b="mouseout":b="mouseover");return La(function(e){return e[0]==a&&e[1]==b&&e[2]==c&&e[4]==!!d})}
function M(a,b,c,d){if(!a||!a.addEventListener&&!a.attachEvent)return"";d=!!d;var e=Zd(a,b,c,d);if(e)return e;var e=++Yd.count+"",f=!("mouseenter"!=b&&"mouseleave"!=b||!a.addEventListener||"onmouseenter"in document),g;g=f?function(d){d=new pd(d);if(!Ud(d.relatedTarget,function(b){return b==a},!0))return d.currentTarget=a,d.type=b,c.call(a,d)}:function(b){b=new pd(b);
b.currentTarget=a;return c.call(a,b)};
g=Gb(g);a.addEventListener?("mouseenter"==b&&f?b="mouseover":"mouseleave"==b&&f?b="mouseout":"mousewheel"==b&&"MozBoxSizing"in document.documentElement.style&&(b="MozMousePixelScroll"),a.addEventListener(b,g,d)):a.attachEvent("on"+b,g);Ma[e]=[a,b,c,g,d];return e}
function $d(a){a&&("string"==typeof a&&(a=[a]),z(a,function(a){if(a in Ma){var b=Ma[a],d=b[0],e=b[1],f=b[3],b=b[4];d.removeEventListener?d.removeEventListener(e,f,b):d.detachEvent&&d.detachEvent("on"+e,f);delete Ma[a]}}))}
;function ae(){if(null==r("_lact",window)){var a=parseInt(G("LACT"),10),a=isFinite(a)?x()-Math.max(a,0):-1;q("_lact",a,window);-1==a&&be();M(document,"keydown",be);M(document,"keyup",be);M(document,"mousedown",be);M(document,"mouseup",be);Qb("page-mouse",be);Qb("page-scroll",be);Qb("page-resize",be)}}
function be(){null==r("_lact",window)&&(ae(),r("_lact",window));var a=x();q("_lact",a,window);Tb("USER_ACTIVE")}
function ce(){var a=r("_lact",window);return null==a?-1:Math.max(x()-a,0)}
;function de(a){return G("EXPERIMENT_FLAGS",{})[a]}
;function ee(){}
ee.prototype.set=aa;ee.prototype.get=aa;ee.prototype.remove=aa;function fe(){}
y(fe,ee);fe.prototype.ba=function(){var a=0;kc(this.U(!0),function(){a++});
return a};
fe.prototype.U=aa;fe.prototype.clear=function(){var a=lc(this.U(!0)),b=this;z(a,function(a){b.remove(a)})};function ge(a){this.b=a}
y(ge,fe);k=ge.prototype;k.isAvailable=function(){if(!this.b)return!1;try{return this.b.setItem("__sak","1"),this.b.removeItem("__sak"),!0}catch(a){return!1}};
k.set=function(a,b){try{this.b.setItem(a,b)}catch(c){if(0==this.b.length)throw"Storage mechanism: Storage disabled";throw"Storage mechanism: Quota exceeded";}};
k.get=function(a){a=this.b.getItem(a);if(!v(a)&&null!==a)throw"Storage mechanism: Invalid value was encountered";return a};
k.remove=function(a){this.b.removeItem(a)};
k.ba=function(){return this.b.length};
k.U=function(a){var b=0,c=this.b,d=new ic;d.next=function(){if(b>=c.length)throw hc;var d=c.key(b++);if(a)return d;d=c.getItem(d);if(!v(d))throw"Storage mechanism: Invalid value was encountered";return d};
return d};
k.clear=function(){this.b.clear()};
k.key=function(a){return this.b.key(a)};function he(){var a=null;try{a=window.localStorage||null}catch(b){}this.b=a}
y(he,ge);function ie(){var a=null;try{a=window.sessionStorage||null}catch(b){}this.b=a}
y(ie,ge);function je(a){this.b=a}
je.prototype.set=function(a,b){p(b)?this.b.set(a,Gc(b)):this.b.remove(a)};
je.prototype.get=function(a){var b;try{b=this.b.get(a)}catch(c){return}if(null!==b)try{return Ec(b)}catch(c){throw"Storage: Invalid value was encountered";}};
je.prototype.remove=function(a){this.b.remove(a)};function ke(a){this.b=a}
y(ke,je);function le(a){this.data=a}
function me(a){return!p(a)||a instanceof le?a:new le(a)}
ke.prototype.set=function(a,b){ke.B.set.call(this,a,me(b))};
ke.prototype.f=function(a){a=ke.B.get.call(this,a);if(!p(a)||a instanceof Object)return a;throw"Storage: Invalid value was encountered";};
ke.prototype.get=function(a){if(a=this.f(a)){if(a=a.data,!p(a))throw"Storage: Invalid value was encountered";}else a=void 0;return a};function ne(a){this.b=a}
y(ne,ke);ne.prototype.set=function(a,b,c){if(b=me(b)){if(c){if(c<x()){ne.prototype.remove.call(this,a);return}b.expiration=c}b.creation=x()}ne.B.set.call(this,a,b)};
ne.prototype.f=function(a,b){var c=ne.B.f.call(this,a);if(c){if(b=!b){b=c.creation;var d=c.expiration;b=!!d&&d<x()||!!b&&b>x()}if(b)ne.prototype.remove.call(this,a);else return c}};function oe(a){this.b=a}
y(oe,ne);function pe(){if(!qe&&!re||!window.JSON)return null;var a;try{a=qe.get("yt-player-two-stage-token")}catch(b){}if(!v(a))try{a=re.get("yt-player-two-stage-token")}catch(b){}if(!v(a))return null;try{a=JSON.parse(a,void 0)}catch(b){}return a}
var re,se=new he;re=se.isAvailable()?new oe(se):null;var qe,ue=new ie;qe=ue.isAvailable()?new oe(ue):null;var ve=B("Firefox"),we=mc()||B("iPod"),xe=B("iPad"),ye=B("Android")&&!(Va()||B("Firefox")||B("Opera")||B("Silk")),ze=Va(),Ae=B("Safari")&&!(Va()||B("Coast")||B("Opera")||B("Edge")||B("Silk")||B("Android"))&&!(mc()||B("iPad")||B("iPod"));function Be(a,b,c){this.b=a;this.g=b;this.f=c}
var Ce=1;function De(a){var b={};void 0!==a.b?b.trackingParams=a.b:(b.veType=a.g,null!=a.f&&(b.veCounter=a.f));return b}
;var Ee={log_event:"events",log_interaction:"interactions"},Fe={},Ge={},He=0,Oa=r("yt.logging.transport.logsQueue_")||{};q("yt.logging.transport.logsQueue_",Oa,void 0);function Ie(a,b){Oa[a.endpoint]=Oa[a.endpoint]||[];var c=Oa[a.endpoint];c.push(a.Na);Ge[a.endpoint]=b;c.length>=(Number(de("web_logging_max_batch")||0)||20)?Je():Ke()}
function Je(){Hb(He);if(!Na()){for(var a in Oa){var b=Fe[a];if(!b){b=Ge[a];if(!b)continue;b=new b;Fe[a]=b}var c=b.f();c.requestTimeMs=Math.round(Jb());c[Ee[a]]=Oa[a];b.g(a,c,{});delete Oa[a]}Na()||Ke()}}
function Ke(){Hb(He);He=H(Je,G("LOGGING_BATCH_TIMEOUT",1E4))}
;function Le(a,b,c){Me(Ne(),{attachChild:{csn:a,parentVisualElement:De(b),visualElements:[De(c)]}},void 0)}
function Oe(a,b){var c=Ne();b=xa(b,function(a){return De(a)});
Me(c,{visibilityUpdate:{csn:a,visualElements:b}})}
function Me(a,b,c){b.eventTimeMs=Math.round(Jb());b.lactMs=ce();c&&(b.clientData=Pe(c));Ie({endpoint:"log_interaction",Na:b},a)}
function Pe(a){var b={};a.analyticsChannelData&&(b.analyticsDatas=xa(a.analyticsChannelData,function(a){return{tabName:a.tabName,cardName:a.cardName,isChannelScreen:a.isChannelScreen,insightId:a.insightId,externalChannelId:a.externalChannelId,externalContentOwnerId:a.externalContentOwnerId}}));
return{playbackData:{clientPlaybackNonce:a.clientPlaybackNonce},analyticsChannelData:b,externalLinkData:a.externalLinkData}}
;function Qe(){var a=G("ROOT_VE_TYPE",void 0);return a?new Be(void 0,a,void 0):null}
function Re(){var a=G("client-screen-nonce",void 0);a||(a=G("EVENT_ID",void 0));return a}
;function Se(a){this.b=a||{cookie:""}}
var Te=/\s*;\s*/;k=Se.prototype;k.isEnabled=function(){return navigator.cookieEnabled};
k.set=function(a,b,c,d,e,f){if(/[;=\s]/.test(a))throw Error('Invalid cookie name "'+a+'"');if(/[;\r\n]/.test(b))throw Error('Invalid cookie value "'+b+'"');p(c)||(c=-1);e=e?";domain="+e:"";d=d?";path="+d:"";f=f?";secure":"";c=0>c?"":0==c?";expires="+(new Date(1970,1,1)).toUTCString():";expires="+(new Date(x()+1E3*c)).toUTCString();this.b.cookie=a+"="+b+e+d+c+f};
k.get=function(a,b){for(var c=a+"=",d=(this.b.cookie||"").split(Te),e=0,f;f=d[e];e++){if(0==f.lastIndexOf(c,0))return f.substr(c.length);if(f==a)return""}return b};
k.remove=function(a,b,c){var d=p(this.get(a));this.set(a,"",0,b,c);return d};
k.isEmpty=function(){return!this.b.cookie};
k.ba=function(){return this.b.cookie?(this.b.cookie||"").split(Te).length:0};
k.clear=function(){for(var a=(this.b.cookie||"").split(Te),b=[],c=[],d,e,f=0;e=a[f];f++)d=e.indexOf("="),-1==d?(b.push(""),c.push(e)):(b.push(e.substring(0,d)),c.push(e.substring(d+1)));for(a=b.length-1;0<=a;a--)this.remove(b[a])};
var Ue=new Se("undefined"==typeof document?null:document);Ue.f=3950;function Ve(a,b,c){var d=G("EVENT_ID");d&&(b||(b={}),b.ei||(b.ei=d));if(b){var d=a,e=G("VALID_SESSION_TEMPDATA_DOMAINS",[]),f=Nc(K(window.location.href)[3]||null);f&&e.push(f);f=Nc(K(d)[3]||null);if(Aa(e,f)||!f&&0==d.lastIndexOf("/",0)){de("autoescape_tempdata_url")&&(e=document.createElement("a"),fb(e,d),d=e.href);var f=K(d),d=f[5],e=f[6],f=f[7],g="";d&&(g+=d);e&&(g+="?"+e);f&&(g+="#"+f);d=g;e=d.indexOf("#");if(d=0>e?d:d.substr(0,e))de("enable_more_related_ve_logging")&&(b.itct||b.ved)&&(b.csn=b.csn||
Re()),d="ST-"+ua(d).toString(36),e=b?Sc(b):"",Ue.set(""+d,e,5,"/","youtube.com"),b&&(b=b.itct||b.ved,d=r("yt.logging.screen.storeParentElement"),b&&d&&d(new Be(b)))}}if(c)return!1;(window.ytspf||{}).enabled?spf.navigate(a):(c=window.location,a=Oc(Rc([a],{}))+"",a=a instanceof Wa?a:$a(a),c.href=Ya(a));return!0}
;function We(a){a=a||{};this.url=a.url||"";this.urlV9As2=a.url_v9as2||"";this.args=a.args||Qa(Xe);this.assets=a.assets||{};this.attrs=a.attrs||Qa(Ye);this.params=a.params||Qa(Ze);this.minVersion=a.min_version||"8.0.0";this.fallback=a.fallback||null;this.fallbackMessage=a.fallbackMessage||null;this.html5=!!a.html5;this.disable=a.disable||{};this.loaded=!!a.loaded;this.messages=a.messages||{}}
var Xe={enablejsapi:1},Ye={},Ze={allowscriptaccess:"always",allowfullscreen:"true",bgcolor:"#000000"};function $e(a){a instanceof We||(a=new We(a));return a}
function af(a){var b=new We,c;for(c in a)if(a.hasOwnProperty(c)){var d=a[c];b[c]="object"==ca(d)?Qa(d):d}return b}
;function bf(a,b,c,d){this.top=a;this.right=b;this.bottom=c;this.left=d}
k=bf.prototype;k.getHeight=function(){return this.bottom-this.top};
k.contains=function(a){return this&&a?a instanceof bf?a.left>=this.left&&a.right<=this.right&&a.top>=this.top&&a.bottom<=this.bottom:a.l>=this.left&&a.l<=this.right&&a.o>=this.top&&a.o<=this.bottom:!1};
k.ceil=function(){this.top=Math.ceil(this.top);this.right=Math.ceil(this.right);this.bottom=Math.ceil(this.bottom);this.left=Math.ceil(this.left);return this};
k.floor=function(){this.top=Math.floor(this.top);this.right=Math.floor(this.right);this.bottom=Math.floor(this.bottom);this.left=Math.floor(this.left);return this};
k.round=function(){this.top=Math.round(this.top);this.right=Math.round(this.right);this.bottom=Math.round(this.bottom);this.left=Math.round(this.left);return this};function cf(a,b,c,d){this.left=a;this.top=b;this.width=c;this.height=d}
cf.prototype.contains=function(a){return a instanceof L?a.l>=this.left&&a.l<=this.left+this.width&&a.o>=this.top&&a.o<=this.top+this.height:this.left<=a.left&&this.left+this.width>=a.left+a.width&&this.top<=a.top&&this.top+this.height>=a.top+a.height};
cf.prototype.ceil=function(){this.left=Math.ceil(this.left);this.top=Math.ceil(this.top);this.width=Math.ceil(this.width);this.height=Math.ceil(this.height);return this};
cf.prototype.floor=function(){this.left=Math.floor(this.left);this.top=Math.floor(this.top);this.width=Math.floor(this.width);this.height=Math.floor(this.height);return this};
cf.prototype.round=function(){this.left=Math.round(this.left);this.top=Math.round(this.top);this.width=Math.round(this.width);this.height=Math.round(this.height);return this};function df(a,b){var c=Dd(a);return c.defaultView&&c.defaultView.getComputedStyle&&(a=c.defaultView.getComputedStyle(a,null))?a[b]||a.getPropertyValue(b)||"":""}
function ef(a,b){return df(a,b)||(a.currentStyle?a.currentStyle[b]:null)||a.style&&a.style[b]}
function ff(a){var b;try{b=a.getBoundingClientRect()}catch(c){return{left:0,top:0,right:0,bottom:0}}I&&a.ownerDocument.body&&(a=a.ownerDocument,b.left-=a.documentElement.clientLeft+a.body.clientLeft,b.top-=a.documentElement.clientTop+a.body.clientTop);return b}
function gf(a,b){"number"==typeof a&&(a=(b?Math.round(a):a)+"px");return a}
function hf(a){var b=jf;if("none"!=ef(a,"display"))return b(a);var c=a.style,d=c.display,e=c.visibility,f=c.position;c.visibility="hidden";c.position="absolute";c.display="inline";a=b(a);c.display=d;c.position=f;c.visibility=e;return a}
function jf(a){var b=a.offsetWidth,c=a.offsetHeight,d=tc&&!b&&!c;return p(b)&&!d||!a.getBoundingClientRect?new od(b,c):(a=ff(a),new od(a.right-a.left,a.bottom-a.top))}
function kf(a,b){if(/^\d+px?$/.test(b))return parseInt(b,10);var c=a.style.left,d=a.runtimeStyle.left;a.runtimeStyle.left=a.currentStyle.left;a.style.left=b;b=a.style.pixelLeft;a.style.left=c;a.runtimeStyle.left=d;return+b}
function lf(a,b){return(b=a.currentStyle?a.currentStyle[b]:null)?kf(a,b):0}
var mf={thin:2,medium:4,thick:6};function nf(a,b){if("none"==(a.currentStyle?a.currentStyle[b+"Style"]:null))return 0;b=a.currentStyle?a.currentStyle[b+"Width"]:null;return b in mf?mf[b]:kf(a,b)}
;function of(){this.g=this.f=this.b=0;this.i="";var a=r("window.navigator.plugins"),b=r("window.navigator.mimeTypes"),a=a&&a["Shockwave Flash"],b=b&&b["application/x-shockwave-flash"],b=a&&b&&b.enabledPlugin&&a.description||"";if(a=b){var c=a.indexOf("Shockwave Flash");0<=c&&(a=a.substr(c+15));for(var c=a.split(" "),d="",a="",e=0,f=c.length;e<f;e++)if(d)if(a)break;else a=c[e];else d=c[e];d=d.split(".");c=parseInt(d[0],10)||0;d=parseInt(d[1],10)||0;e=0;if("r"==a.charAt(0)||"d"==a.charAt(0))e=parseInt(a.substr(1),
10)||0;a=[c,d,e]}else a=[0,0,0];this.i=b;b=a;this.b=b[0];this.f=b[1];this.g=b[2];if(0>=this.b){var g,l,h,m;if(Kb)try{g=new ActiveXObject("ShockwaveFlash.ShockwaveFlash")}catch(u){g=null}else h=document.body,m=document.createElement("object"),m.setAttribute("type","application/x-shockwave-flash"),g=h.appendChild(m);if(g&&"GetVariable"in g)try{l=g.GetVariable("$version")}catch(u){l=""}h&&m&&h.removeChild(m);(g=l||"")?(g=g.split(" ")[1].split(","),g=[parseInt(g[0],10)||0,parseInt(g[1],10)||0,parseInt(g[2],
10)||0]):g=[0,0,0];this.b=g[0];this.f=g[1];this.g=g[2]}}
ba(of);function pf(a,b,c,d){b="string"==typeof b?b.split("."):[b,c,d];b[0]=parseInt(b[0],10)||0;b[1]=parseInt(b[1],10)||0;b[2]=parseInt(b[2],10)||0;return a.b>b[0]||a.b==b[0]&&a.f>b[1]||a.b==b[0]&&a.f==b[1]&&a.g>=b[2]}
;function qf(a){if(window.spf){var b=a.match(rf);spf.style.load(a,b?b[1]:"",void 0)}else sf(a)}
function sf(a){var b=tf(a),c=document.getElementById(b),d=c&&C(c,"loaded");d||c&&!d||(c=uf(a,b,function(){C(c,"loaded")||(hb(c,"loaded","true"),Tb(b),H(na(Vb,b),0))}))}
function uf(a,b,c){var d=document.createElement("link");d.id=b;d.onload=function(){c&&setTimeout(c,0)};
a=yd(a);gb(d,a);(document.getElementsByTagName("head")[0]||document.body).appendChild(d);return d}
function tf(a){var b=document.createElement("a");fb(b,a);a=b.href.replace(/^[a-zA-Z]+:\/\//,"//");return"css-"+ua(a)}
var rf=/cssbin\/(?:debug-)?([a-zA-Z0-9_-]+?)(?:-2x|-web|-rtl|-vfl|.css)/;var vf;var wf=A,wf=wf.toLowerCase();if(-1!=wf.indexOf("android")){var xf=wf.match(/android\D*(\d\.\d)[^\;|\)]*[\;\)]/);if(xf)vf=Number(xf[1]);else{var yf={cupcake:1.5,donut:1.6,eclair:2,froyo:2.2,gingerbread:2.3,honeycomb:3,"ice cream sandwich":4,jellybean:4.1},zf=[],Af=0,Bf;for(Bf in yf)zf[Af++]=Bf;var Cf=wf.match("("+zf.join("|")+")");vf=Cf?yf[Cf[0]]:0}}else vf=void 0;var Df=['video/mp4; codecs="avc1.42001E, mp4a.40.2"','video/webm; codecs="vp8.0, vorbis"'],Ef=['audio/mp4; codecs="mp4a.40.2"'];I&&J("9");!tc||J("528");sc&&J("1.9b")||I&&J("8")||pc&&J("9.5")||tc&&J("528");sc&&!J("8")||I&&J("9");var Ff;var Gf=A,Hf=Gf.match(/\((iPad|iPhone|iPod)( Simulator)?;/);if(!Hf||2>Hf.length)Ff=void 0;else{var If=Gf.match(/\((iPad|iPhone|iPod)( Simulator)?; (U; )?CPU (iPhone )?OS (\d+_\d)[_ ]/);Ff=If&&6==If.length?Number(If[5].replace("_",".")):0}0<=Ff&&0<=A.search("Safari")&&A.search("Version");function Jf(a){D.call(this);this.b=[];this.g=a||this}
y(Jf,D);function Kf(a,b,c,d){d=Gb(w(d,a.g));d={target:b,name:c,pa:d};b.addEventListener(c,d.pa,void 0);a.b.push(d)}
function Lf(a){for(;a.b.length;){var b=a.b.pop();b.target.removeEventListener(b.name,b.pa)}}
Jf.prototype.A=function(){Lf(this);Jf.B.A.call(this)};function Mf(a){a.prototype.then=a.prototype.then;a.prototype.$goog_Thenable=!0}
;function N(a,b){this.b=0;this.D=void 0;this.i=this.f=this.g=null;this.j=this.w=!1;if(a!=t)try{var c=this;a.call(b,function(a){Nf(c,2,a)},function(a){Nf(c,3,a)})}catch(d){Nf(this,3,d)}}
function Of(){this.next=this.context=this.f=this.g=this.b=null;this.i=!1}
Of.prototype.reset=function(){this.context=this.f=this.g=this.b=null;this.i=!1};
var Pf=new ob(function(){return new Of},function(a){a.reset()},100);
function Qf(a,b,c){var d=Pf.get();d.g=a;d.f=b;d.context=c;return d}
function Rf(a){if(a instanceof N)return a;var b=new N(t);Nf(b,2,a);return b}
function Sf(a){return new N(function(b,c){c(a)})}
N.prototype.then=function(a,b,c){return Tf(this,fa(a)?a:null,fa(b)?b:null,c)};
Mf(N);N.prototype.cancel=function(a){0==this.b&&tb(function(){var b=new Uf(a);Vf(this,b)},this)};
function Vf(a,b){if(0==a.b)if(a.g){var c=a.g;if(c.f){for(var d=0,e=null,f=null,g=c.f;g&&(g.i||(d++,g.b==a&&(e=g),!(e&&1<d)));g=g.next)e||(f=g);e&&(0==c.b&&1==d?Vf(c,b):(f?(d=f,d.next==c.i&&(c.i=d),d.next=d.next.next):Wf(c),Xf(c,e,3,b)))}a.g=null}else Nf(a,3,b)}
function Yf(a,b){a.f||2!=a.b&&3!=a.b||Zf(a);a.i?a.i.next=b:a.f=b;a.i=b}
function Tf(a,b,c,d){var e=Qf(null,null,null);e.b=new N(function(a,g){e.g=b?function(c){try{var e=b.call(d,c);a(e)}catch(m){g(m)}}:a;
e.f=c?function(b){try{var e=c.call(d,b);!p(e)&&b instanceof Uf?g(b):a(e)}catch(m){g(m)}}:g});
e.b.g=a;Yf(a,e);return e.b}
N.prototype.G=function(a){this.b=0;Nf(this,2,a)};
N.prototype.K=function(a){this.b=0;Nf(this,3,a)};
function Nf(a,b,c){if(0==a.b){a===c&&(b=3,c=new TypeError("Promise cannot resolve to itself"));a.b=1;var d;a:{var e=c,f=a.G,g=a.K;if(e instanceof N)Yf(e,Qf(f||t,g||null,a)),d=!0;else{var l;if(e)try{l=!!e.$goog_Thenable}catch(m){l=!1}else l=!1;if(l)e.then(f,g,a),d=!0;else{if(ga(e))try{var h=e.then;if(fa(h)){$f(e,h,f,g,a);d=!0;break a}}catch(m){g.call(a,m);d=!0;break a}d=!1}}}d||(a.D=c,a.b=b,a.g=null,Zf(a),3!=b||c instanceof Uf||ag(a,c))}}
function $f(a,b,c,d,e){function f(a){l||(l=!0,d.call(e,a))}
function g(a){l||(l=!0,c.call(e,a))}
var l=!1;try{b.call(a,g,f)}catch(h){f(h)}}
function Zf(a){a.w||(a.w=!0,tb(a.J,a))}
function Wf(a){var b=null;a.f&&(b=a.f,a.f=b.next,b.next=null);a.f||(a.i=null);return b}
N.prototype.J=function(){for(var a;a=Wf(this);)Xf(this,a,this.b,this.D);this.w=!1};
function Xf(a,b,c,d){if(3==c&&b.f&&!b.i)for(;a&&a.j;a=a.g)a.j=!1;if(b.b)b.b.g=null,bg(b,c,d);else try{b.i?b.g.call(b.context):bg(b,c,d)}catch(e){cg.call(null,e)}pb(Pf,b)}
function bg(a,b,c){2==b?a.g.call(a.context,c):a.f&&a.f.call(a.context,c)}
function ag(a,b){a.j=!0;tb(function(){a.j&&cg.call(null,b)})}
var cg=lb;function Uf(a){pa.call(this,a)}
y(Uf,pa);Uf.prototype.name="cancel";function dg(){return{apiaryHost:G("APIARY_HOST",void 0),Ea:G("APIARY_HOST_FIRSTPARTY",void 0),gapiHintOverride:G("GAPI_HINT_OVERRIDE"),gapiHintParams:G("GAPI_HINT_PARAMS",void 0),innertubeApiKey:G("INNERTUBE_API_KEY",void 0),innertubeApiVersion:G("INNERTUBE_API_VERSION",void 0),tb:G("INNERTUBE_CONTEXT_CLIENT_NAME","WEB"),innertubeContextClientVersion:G("INNERTUBE_CONTEXT_CLIENT_VERSION",void 0),vb:G("INNERTUBE_CONTEXT_HL",void 0),ub:G("INNERTUBE_CONTEXT_GL",void 0),Vb:G("XHR_APIARY_HOST",void 0)}}
function eg(a){a={client:{hl:a.vb,gl:a.ub,clientName:a.tb,clientVersion:a.innertubeContextClientVersion}};G("DELEGATED_SESSION_ID")&&(a.user={onBehalfOfUser:G("DELEGATED_SESSION_ID")});return a}
;function fg(){this.b=dg();gg||(gg=hg(this.b))}
var gg=null;function hg(a){return(new N(function(b){Wb(G("GAPI_LOADER_URL",void 0),function(){try{r("yt.gapi.load")("client",{gapiHintOverride:a.gapiHintOverride,_c:{jsl:{h:a.gapiHintParams}},callback:b})}catch(c){Ib(c)}})})).then(function(){})}
fg.prototype.i=function(){var a=r("gapi.config.update");a("googleapis.config/auth/useFirstPartyAuth",!0);var b=this.b.apiaryHost;ra(null==b?"":String(b))||a("googleapis.config/root",(-1==b.indexOf("://")?"//":"")+b);b=this.b.Ea;ra(null==b?"":String(b))||a("googleapis.config/root-1p",(-1==b.indexOf("://")?"//":"")+b);a("googleapis.config/sessionIndex",G("SESSION_INDEX"));r("gapi.client.setApiKey")(this.b.innertubeApiKey)};
fg.prototype.f=function(){return{context:eg(this.b)}};
fg.prototype.g=function(a,b,c){var d,e=!1;0<c.timeout&&(d=H(function(){e||(e=!0,c.R&&c.R())},c.timeout));
ig(this,a,b,function(a){if(!e)if(e=!0,d&&Hb(d),a)c.F&&c.F(a);else if(c.onError)c.onError()})};
function ig(a,b,c,d){var e={path:"/youtubei/"+a.b.innertubeApiVersion+"/"+b,headers:{"X-Goog-Visitor-Id":G("VISITOR_DATA")},method:"POST",body:Gc(c)},f=w(a.i,a);gg.then(function(){f();r("gapi.client.request")(e).execute(d||t)})}
;function jg(a,b,c){var d={};d.eventTimeMs=Math.round(c||Jb());d[a]=b;Ie({endpoint:"log_event",Na:d},fg)}
;var kg={},lg=0;function mg(a){var b=new Image,c=""+lg++;kg[c]=b;b.onload=b.onerror=function(){delete kg[c]};
b.src=a}
;function O(a,b){this.version=a;this.args=b}
function ng(a){if(!a.Va){var b={};a.call(b);a.Va=b.version}return a.Va}
function og(a,b){function c(){a.apply(this,b.args)}
if(!b.args||!b.version)throw Error("yt.pubsub2.Data.deserialize(): serializedData is incomplete.");var d;try{d=ng(a)}catch(e){}if(!d||b.version!=d)throw Error("yt.pubsub2.Data.deserialize(): serializedData version is incompatible.");c.prototype=a.prototype;try{return new c}catch(e){throw e.message="yt.pubsub2.Data.deserialize(): "+e.message,e;}}
function P(a,b){this.topic=a;this.b=b}
P.prototype.toString=function(){return this.topic};var pg=r("yt.pubsub2.instance_")||new E;E.prototype.subscribe=E.prototype.subscribe;E.prototype.unsubscribeByKey=E.prototype.T;E.prototype.publish=E.prototype.N;E.prototype.clear=E.prototype.clear;q("yt.pubsub2.instance_",pg,void 0);var qg=r("yt.pubsub2.subscribedKeys_")||{};q("yt.pubsub2.subscribedKeys_",qg,void 0);var rg=r("yt.pubsub2.topicToKeys_")||{};q("yt.pubsub2.topicToKeys_",rg,void 0);var sg=r("yt.pubsub2.isAsync_")||{};q("yt.pubsub2.isAsync_",sg,void 0);q("yt.pubsub2.skipSubKey_",null,void 0);
function Q(a,b){var c=tg();c&&c.publish.call(c,a.toString(),a,b)}
function R(a,b,c){var d=tg();if(!d)return 0;var e=d.subscribe(a.toString(),function(d,g){if(!window.yt.pubsub2.skipSubKey_||window.yt.pubsub2.skipSubKey_!=e){var f=function(){if(qg[e])try{if(g&&a instanceof P&&a!=d)try{g=og(a.b,g)}catch(h){throw h.message="yt.pubsub2 cross-binary conversion error for "+a.toString()+": "+h.message,h;}b.call(c||window,g)}catch(h){Ib(h)}};
sg[a.toString()]?r("yt.scheduler.instance")?jd(f,void 0):H(f,0):f()}});
qg[e]=!0;rg[a.toString()]||(rg[a.toString()]=[]);rg[a.toString()].push(e);return e}
function ug(a){var b=tg();b&&("number"==typeof a&&(a=[a]),z(a,function(a){b.unsubscribeByKey(a);delete qg[a]}))}
function tg(){return r("yt.pubsub2.instance_")}
;var vg=window.performance||window.mozPerformance||window.msPerformance||window.webkitPerformance||{};function wg(a){O.call(this,1,arguments)}
y(wg,O);var xg=new P("timing-sent",wg);var yg={vc:!0},zg={ad_at:"adType",cpn:"clientPlaybackNonce",csn:"clientScreenNonce",yt_lt:"loadType",yt_ad:"isMonetized",yt_ad_pr:"prerollAllowed",yt_red:"isRedSubscriber",yt_vis:"isVisible"},Ag=["isMonetized","prerollAllowed","isRedSubscriber","isVisible"],Bg=w(vg.clearResourceTimings||vg.webkitClearResourceTimings||vg.mozClearResourceTimings||vg.msClearResourceTimings||vg.oClearResourceTimings||t,vg);
function Cg(a){if("_"!=a[0]){var b=a;vg.mark&&(0==b.lastIndexOf("mark_",0)||(b="mark_"+b),vg.mark(b))}var b=Dg(),c=Jb();b[a]&&(b["_"+a]=b["_"+a]||[b[a]],b["_"+a].push(c));b[a]=c;Eg()["tick_"+a]=void 0;Jb();de("csi_on_gel")?(b=Fg(),"_start"==a?jg("latencyActionBaselined",{clientActionNonce:b},void 0):jg("latencyActionTicked",{tickName:a,clientActionNonce:b},void 0),a=!0):a=!1;a||(a=!!r("yt.timing.pingSent_")&&!!de("navigation_only_csi_reset"));if(!a&&(b=G("TIMING_ACTION",void 0),a=Dg(),r("yt.timing.ready_")&&
b&&a._start&&Gg())){b=!0;c=G("TIMING_WAIT",[]);if(c.length)for(var d=0,e=c.length;d<e;++d)if(!(c[d]in a)){b=!1;break}b&&Hg()}}
function Ig(){var a=Jg().info.yt_lt="hot_bg";Eg().info_yt_lt=a;if(de("csi_on_gel"))if("yt_lt"in zg){var b={},c=zg.yt_lt;Aa(Ag,c)&&(a=!!a);b[c]=a;a=Fg();b.clientActionNonce=a;jg("latencyActionInfo",b)}else Ib(Error("Unknown label yt_lt logged with GEL CSI."))}
function Gg(){var a=Dg();if(a.aft)return a.aft;for(var b=G("TIMING_AFT_KEYS",["ol"]),c=b.length,d=0;d<c;d++){var e=a[b[d]];if(e)return e}return NaN}
function Hg(){if(!de("csi_on_gel")){var a=Dg(),b=Jg().info,c=a._start,d;for(d in a)if(0==d.lastIndexOf("_",0)&&da(a[d])){var e=d.slice(1);if(e in yg){var f=xa(a[d],function(a){return Math.round(a-c)});
b["all_"+e]=f.join()}delete a[d]}e=!!b.ap;if(f=r("yt.timing.reportbuilder_")){if(f=f(a,b,void 0))Kg(f,e),Lg(),Bg(),Mg(!1,void 0)}else{var g=G("CSI_SERVICE_NAME","youtube"),f={v:2,s:g,action:G("TIMING_ACTION",void 0)},l=b.srt;delete b.srt;void 0===a.srt&&(l||0===l||(l=vg.timing||{},l=Math.max(0,l.responseStart-l.navigationStart),isNaN(l)&&b.pt&&(l=b.pt)),l||0===l)&&(b.srt=Math.round(l));if(b.h5jse){var h=window.location.protocol+r("ytplayer.config.assets.js");(h=vg.getEntriesByName?vg.getEntriesByName(h)[0]:
null)?b.h5jse=Math.round(b.h5jse-h.responseEnd):delete b.h5jse}a.aft=Gg();Ng()&&"youtube"==g&&(Ig(),g=a.vc,h=a.pbs,delete a.aft,b.aft=Math.round(h-g));for(var m in b)"_"!=m.charAt(0)&&(f[m]=b[m]);a.ps=Jb();b={};m=[];for(d in a)"_"!=d.charAt(0)&&(g=Math.round(a[d]-c),b[d]=g,m.push(d+"."+g));f.rt=m.join(",");(a=r("ytdebug.logTiming"))&&a(f,b);de("navigation_only_csi_reset")||(Lg(),Bg(),Mg(!1,void 0));Kg(f,e,void 0);Q(xg,new wg(b.aft+(l||0)))}}}
function Kg(a,b,c){if(de("debug_csi_data")){var d=r("yt.timing.csiData");d||(d=[],q("yt.timing.csiData",d,void 0));d.push({page:location.href,time:new Date,args:a})}var d="",e;for(e in a)d+="&"+e+"="+a[e];a="/csi_204?"+d.substring(1);if(window.navigator&&window.navigator.sendBeacon&&b)try{window.navigator&&window.navigator.sendBeacon&&window.navigator.sendBeacon(a,"")||a&&mg(a)}catch(f){a&&mg(a)}else a&&mg(a);Mg(!0,c)}
function Fg(){var a=Jg().nonce;if(!a){var b;a:{if(window.crypto&&window.crypto.getRandomValues)try{var c=Array(16),d=new Uint8Array(16);window.crypto.getRandomValues(d);for(a=0;a<c.length;a++)c[a]=d[a];b=c;break a}catch(e){}b=Array(16);for(c=0;16>c;c++){d=x();for(a=0;a<d%23;a++)b[c]=Math.random();b[c]=Math.floor(256*Math.random())}if(wd)for(c=1,d=0;d<wd.length;d++)b[c%16]=b[c%16]^b[(c-1)%16]/4^wd.charCodeAt(d),c++}c=[];for(d=0;d<b.length;d++)c.push("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_".charAt(b[d]&
63));a=c.join("");Jg().nonce=a}return a}
function Dg(){return Jg().tick}
function Eg(){var a=Jg();"gel"in a||(a.gel={});return a.gel}
function Jg(){return r("ytcsi.data_")||Lg()}
function Lg(){var a={tick:{},info:{}};q("ytcsi.data_",a,void 0);return a}
function Mg(a,b){q("yt.timing."+(b||"")+"pingSent_",a,void 0)}
function Ng(){var a=Dg(),b=a.pbr,c=a.vc,a=a.pbs;return b&&c&&a&&b<c&&c<a&&1==Jg().info.yt_pvis}
;var Og={"api.invalidparam":2,auth:150,"drm.auth":150,"heartbeat.net":150,"heartbeat.servererror":150,"heartbeat.stop":150,"html5.unsupportedads":5,"fmt.noneavailable":5,"fmt.decode":5,"fmt.unplayable":5,"html5.missingapi":5,"html5.unsupportedlive":5,"drm.unavailable":5};function Pg(a,b){D.call(this);this.D=this.w=a;this.$=b;this.G=!1;this.g={};this.fa=this.Z=null;this.aa=new E;zb(this,na(Ab,this.aa));this.j={};this.L=this.ga=this.i=this.oa=this.b=null;this.ca=!1;this.O=this.J=this.X=this.Y=null;this.ha={};this.hb=["onReady"];this.da=new Jf(this);zb(this,na(Ab,this.da));this.ma=null;this.Ba=NaN;this.ea={};Qg(this);this.M("onDetailedError",w(this.Bb,this));this.M("onTabOrderChange",w(this.kb,this));this.M("onTabAnnounce",w(this.Ca,this));this.M("WATCH_LATER_VIDEO_ADDED",
w(this.Cb,this));this.M("WATCH_LATER_VIDEO_REMOVED",w(this.Db,this));ve||(this.M("onMouseWheelCapture",w(this.zb,this)),this.M("onMouseWheelRelease",w(this.Ab,this)));this.M("onAdAnnounce",w(this.Ca,this));this.P=new Jf(this);zb(this,na(Ab,this.P));this.na=!1;this.la=null}
y(Pg,D);var Rg=["drm.unavailable","fmt.noneavailable","html5.missingapi","html5.unsupportedads","html5.unsupportedlive"];k=Pg.prototype;k.xa=function(a,b){this.f||(Sg(this,a),Tg(this,b),this.G&&Ug(this))};
function Sg(a,b){a.oa=b;a.b=af(b);a.i=a.b.attrs.id||a.i;"video-player"==a.i&&(a.i=a.$,a.b.attrs.id=a.$);a.D.id==a.i&&(a.i+="-player",a.b.attrs.id=a.i);a.b.args.enablejsapi="1";a.b.args.playerapiid=a.$;a.ga||(a.ga=Vg(a,a.b.args.jsapicallback||"onYouTubePlayerReady"));a.b.args.jsapicallback=null;if(b=a.b.attrs.width)a.D.style.width=gf(Number(b)||b,!0);if(b=a.b.attrs.height)a.D.style.height=gf(Number(b)||b,!0)}
k.pb=function(){return this.oa};
function Ug(a){a.b.loaded||(a.b.loaded=!0,"0"!=a.b.args.autoplay?a.g.loadVideoByPlayerVars(a.b.args):a.g.cueVideoByPlayerVars(a.b.args))}
function Wg(a){if(!p(a.b.disable.flash)){var b=a.b.disable,c;c=pf(of.getInstance(),a.b.minVersion);b.flash=!c}return!a.b.disable.flash}
function Xg(a,b){if((!b||(5!=(Og[b.errorCode]||5)?0:-1!=Rg.indexOf(b.errorCode)))&&Wg(a)){(b=Yg(a))&&b.stopVideo&&b.stopVideo();var c=a.b;b&&b.getUpdatedConfigurationData&&(b=b.getUpdatedConfigurationData(),c=$e(b));c.args.autoplay=1;c.args.html5_unavailable="1";Sg(a,c);Tg(a,"flash")}}
function Tg(a,b){if(!a.f){if(!b){if(!(b=!a.b.html5&&Wg(a))){if(!p(a.b.disable.html5)){var c;b=!0;void 0!=a.b.args.deviceHasDisplay&&(b=a.b.args.deviceHasDisplay);if(2.2==vf)c=!0;else{a:{var d=b;b=r("yt.player.utils.videoElement_");b||(b=document.createElement("VIDEO"),q("yt.player.utils.videoElement_",b,void 0));try{if(b.canPlayType)for(var d=d?Df:Ef,e=0;e<d.length;e++)if(b.canPlayType(d[e])){c=null;break a}c="fmt.noneavailable"}catch(f){c="html5.missingapi"}}c=!c}c&&(c=Zg(a)||a.b.assets.js);a.b.disable.html5=
!c;c||(a.b.args.html5_unavailable="1")}b=!!a.b.disable.html5}b=b?Wg(a)?"flash":"unsupported":"html5"}("flash"==b?a.Tb:a.Ub).call(a)}}
function Zg(a){var b=!0,c=Yg(a);c&&a.b&&(a=a.b,b=C(c,"version")==a.assets.js);return b&&!!r("yt.player.Application.create")}
k.Ub=function(){if(!this.ca){var a=Zg(this);a&&"html5"==$g(this)?(this.L="html5",this.G||this.W()):(ah(this),this.L="html5",a&&this.X?(this.w.appendChild(this.X),this.W()):(this.b.loaded=!0,this.Y=w(function(){var a=this.w,c=af(this.b);r("yt.player.Application.create")(a,c);this.W()},this),this.ca=!0,a?this.Y():(Wb(this.b.assets.js,this.Y),qf(this.b.assets.css))))}};
k.Tb=function(){var a=af(this.b);if(!this.J){var b=Yg(this);b&&(this.J=document.createElement("SPAN"),this.J.tabIndex=0,Kf(this.da,this.J,"focus",this.Ja),this.O=document.createElement("SPAN"),this.O.tabIndex=0,Kf(this.da,this.O,"focus",this.Ja),b.parentNode&&b.parentNode.insertBefore(this.J,b),b.parentNode&&b.parentNode.insertBefore(this.O,b.nextSibling))}a.attrs.width=a.attrs.width||"100%";a.attrs.height=a.attrs.height||"100%";if("flash"==$g(this))this.L="flash",this.G||this.W();else{ah(this);this.L=
"flash";this.b.loaded=!0;var b=of.getInstance(),c=(-1<b.i.indexOf("Gnash")&&-1==b.i.indexOf("AVM2")||9==b.b&&1==b.f||9==b.b&&0==b.f&&1==b.g?0:9<=b.b)||-1<navigator.userAgent.indexOf("Sony/COM2")&&!pf(b,9,1,58)?a.url:a.urlV9As2;window!=window.top&&document.referrer&&(a.args.framer=document.referrer.substring(0,128));b=this.w;if(c){var b=v(b)?Ed(b):b,d=Qa(a.attrs);d.tabindex=0;var e=Qa(a.params);e.flashvars=Sc(a.args);if(Kb){d.classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000";e.movie=c;var a=document.createElement("object"),
f;for(f in d)a.setAttribute(f,d[f]);for(f in e)d=document.createElement("param"),d.setAttribute("name",f),d.setAttribute("value",e[f]),a.appendChild(d)}else{d.type="application/x-shockwave-flash";d.src=c;a=document.createElement("embed");a.setAttribute("name",d.id);for(f in d)a.setAttribute(f,d[f]);for(f in e)a.setAttribute(f,e[f])}f=document.createElement("div");f.appendChild(a);b.innerHTML=f.innerHTML}this.W()}};
k.Ja=function(){Yg(this).focus()};
function Yg(a){var b=Ed(a.i);!b&&a.D&&a.D.querySelector&&(b=a.D.querySelector("#"+a.i));return b}
k.W=function(){if(!this.f){var a=Yg(this),b=!1;try{a&&a.getApiInterface&&a.getApiInterface()&&(b=!0)}catch(f){}if(b)if(this.ca=!1,a.isNotServable&&a.isNotServable(this.b.args.video_id))Xg(this);else{Qg(this);this.G=!0;a=Yg(this);a.addEventListener&&(this.Z=bh(this,a,"addEventListener"));a.removeEventListener&&(this.fa=bh(this,a,"removeEventListener"));for(var b=a.getApiInterface(),b=b.concat(a.getInternalApiInterface()),c=0;c<b.length;c++){var d=b[c];this.g[d]||(this.g[d]=bh(this,a,d))}for(var e in this.j)this.Z(e,
this.j[e]);Ug(this);this.ga&&this.ga(this.g);this.aa.N("onReady",this.g)}else this.Ba=H(w(this.W,this),50)}};
function bh(a,b,c){var d=b[c];return function(){try{return a.ma=null,d.apply(b,arguments)}catch(e){"Bad NPObject as private data!"!=e.message&&"sendAbandonmentPing"!=c&&(e.message+=" ("+c+")",a.ma=e,Ib(e,"WARNING"))}}}
function Qg(a){a.G=!1;if(a.fa)for(var b in a.j)a.fa(b,a.j[b]);for(var c in a.ea)Hb(parseInt(c,10));a.ea={};a.Z=null;a.fa=null;for(var d in a.g)a.g[d]=null;a.g.addEventListener=w(a.M,a);a.g.removeEventListener=w(a.Lb,a);a.g.destroy=w(a.dispose,a);a.g.getLastError=w(a.qb,a);a.g.getPlayerType=w(a.rb,a);a.g.getCurrentVideoConfig=w(a.pb,a);a.g.loadNewVideoConfig=w(a.xa,a);a.g.isReady=w(a.Wb,a)}
k.Wb=function(){return this.G};
k.M=function(a,b){if(!this.f&&(b=Vg(this,b))){if(!Aa(this.hb,a)&&!this.j[a]){var c=ch(this,a);this.Z&&this.Z(a,c)}this.aa.subscribe(a,b);"onReady"==a&&this.G&&H(na(b,this.g),0)}};
k.Lb=function(a,b){this.f||(b=Vg(this,b))&&this.aa.unsubscribe(a,b)};
function Vg(a,b){var c=b;if("string"==typeof b){if(a.ha[b])return a.ha[b];c=function(){var a=r(b);a&&a.apply(n,arguments)};
a.ha[b]=c}return c?c:null}
function ch(a,b){var c="ytPlayer"+b+a.$;a.j[b]=c;n[c]=function(c){var d=H(function(){if(!a.f){a.aa.N(b,c);var e=a.ea,g=String(d);g in e&&delete e[g]}},0);
Pa(a.ea,String(d))};
return c}
k.kb=function(a){a=a?Md:Ld;for(var b=a(document.activeElement);b&&(1!=b.nodeType||b==this.J||b==this.O||(b.focus(),b!=document.activeElement));)b=a(b)};
k.Ca=function(a){Tb("a11y-announce",a)};
k.Bb=function(a){Xg(this,a)};
k.Cb=function(a){Tb("WATCH_LATER_VIDEO_ADDED",a)};
k.Db=function(a){Tb("WATCH_LATER_VIDEO_REMOVED",a)};
k.zb=function(){this.na||(ze?(this.la=Jd(document),Kf(this.P,window,"scroll",this.Ib),Kf(this.P,this.w,"touchmove",this.Gb)):(Kf(this.P,this.w,"mousewheel",this.Ma),Kf(this.P,this.w,"wheel",this.Ma)),this.na=!0)};
k.Ab=function(){Lf(this.P);this.na=!1};
k.Ma=function(a){a=a||window.event;a.returnValue=!1;a.preventDefault&&a.preventDefault()};
k.Ib=function(){window.scrollTo(this.la.l,this.la.o)};
k.Gb=function(a){a.preventDefault()};
k.rb=function(){return this.L||$g(this)};
k.qb=function(){return this.ma};
function $g(a){return(a=Yg(a))?"div"==a.tagName.toLowerCase()?"html5":"flash":null}
function ah(a){Cg("dcp");a.cancel();Qg(a);a.L=null;a.b&&(a.b.loaded=!1);var b=Yg(a);"html5"==$g(a)?a.X=b:b&&b.destroy&&b.destroy();Kd(a.w);Lf(a.da);a.J=null;a.O=null}
k.cancel=function(){this.Y&&cc(this.b.assets.js,this.Y);Hb(this.Ba);this.ca=!1};
k.A=function(){ah(this);if(this.X&&this.b)try{this.X.destroy()}catch(b){Ib(b)}this.ha=null;for(var a in this.j)n[this.j[a]]=null;this.oa=this.b=this.g=null;delete this.w;delete this.D;Pg.B.A.call(this)};var dh={},eh="player_uid_"+(1E9*Math.random()>>>0);function fh(a,b){a=v(a)?Ed(a):a;b=$e(b);var c=eh+"_"+ha(a),d=dh[c];if(d)return d.xa(b),d.g;d=new Pg(a,c);dh[c]=d;Tb("player-added",d.g);zb(d,na(gh,d));H(function(){d.xa(b)},0);
return d.g}
function gh(a){dh[a.$]=null}
function hh(a){a=Ed(a);if(!a)return null;var b=eh+"_"+ha(a),c=dh[b];c||(c=new Pg(a,b),dh[b]=c);return c.g}
;var ih=r("yt.abuse.botguardInitialized")||fc;q("yt.abuse.botguardInitialized",ih,void 0);var jh=r("yt.abuse.invokeBotguard")||gc;q("yt.abuse.invokeBotguard",jh,void 0);var kh=r("yt.abuse.dclkstatus.checkDclkStatus")||nd;q("yt.abuse.dclkstatus.checkDclkStatus",kh,void 0);var lh=r("yt.player.exports.navigate")||Ve;q("yt.player.exports.navigate",lh,void 0);var mh=r("yt.player.embed")||fh;q("yt.player.embed",mh,void 0);var nh=r("yt.player.getPlayerByElement")||hh;q("yt.player.getPlayerByElement",nh,void 0);
var oh=r("yt.util.activity.init")||ae;q("yt.util.activity.init",oh,void 0);var ph=r("yt.util.activity.getTimeSinceActive")||ce;q("yt.util.activity.getTimeSinceActive",ph,void 0);var qh=r("yt.util.activity.setTimestamp")||be;q("yt.util.activity.setTimestamp",qh,void 0);function rh(a){O.call(this,1,arguments);this.b=a}
y(rh,O);function S(a){O.call(this,1,arguments);this.b=a}
y(S,O);function sh(a,b,c){O.call(this,3,arguments);this.g=a;this.f=b;this.b=null!=c?!!c:null}
y(sh,O);function th(a,b,c,d,e){O.call(this,2,arguments);this.f=a;this.b=b;this.i=c||null;this.g=d||null;this.source=e||null}
y(th,O);function uh(a,b,c){O.call(this,1,arguments);this.b=a;this.subscriptionId=b}
y(uh,O);function vh(a,b,c,d,e,f,g){O.call(this,1,arguments);this.f=a;this.subscriptionId=b;this.b=c;this.j=d||null;this.i=e||null;this.g=f||null;this.source=g||null}
y(vh,O);
var wh=new P("subscription-batch-subscribe",rh),xh=new P("subscription-batch-unsubscribe",rh),yh=new P("subscription-subscribe",th),zh=new P("subscription-subscribe-loading",S),Ah=new P("subscription-subscribe-loaded",S),Bh=new P("subscription-subscribe-success",uh),Ch=new P("subscription-subscribe-external",th),Dh=new P("subscription-unsubscribe",vh),Eh=new P("subscription-unsubscirbe-loading",S),Fh=new P("subscription-unsubscribe-loaded",S),Gh=new P("subscription-unsubscribe-success",S),Hh=new P("subscription-external-unsubscribe",
vh),Ih=new P("subscription-enable-ypc",S),Jh=new P("subscription-disable-ypc",S),Kh=new P("subscription-prefs",sh),Lh=new P("subscription-prefs-success",sh),Mh=new P("subscription-prefs-failure",sh);function Nh(a,b){var c=document.location.protocol+"//"+document.domain+"/post_login";b&&(c=Tc(c,"mode",b));b=Tc("/signin?context=popup","next",c);b=Tc(b,"feature","sub_button");if(b=window.open(b,"loginPopup","width=375,height=440,resizable=yes,scrollbars=yes",!0))c=Qb("LOGGED_IN",function(b){Sb(G("LOGGED_IN_PUBSUB_KEY",void 0));F("LOGGED_IN",!0);a(b)}),F("LOGGED_IN_PUBSUB_KEY",c),b.moveTo((screen.width-375)/2,(screen.height-440)/2)}
q("yt.pubsub.publish",Tb,void 0);function Oh(){var a=G("PLAYER_CONFIG");return a&&a.args&&void 0!==a.args.authuser?!0:!(!G("SESSION_INDEX")&&!G("LOGGED_IN"))}
;function Ph(){var a=Xd();return a?a:null}
;function Qh(a,b){(a=Ed(a))&&a.style&&(a.style.display=b?"":"none",vd(a,"hid",!b))}
function Rh(a){z(arguments,function(a){!ea(a)||a instanceof Element?Qh(a,!0):z(a,function(a){Rh(a)})})}
function Sh(a){z(arguments,function(a){!ea(a)||a instanceof Element?Qh(a,!1):z(a,function(a){Sh(a)})})}
;var Th={},Uh="ontouchstart"in document;function Vh(a,b,c){var d;switch(a){case "mouseover":case "mouseout":d=3;break;case "mouseenter":case "mouseleave":d=9}return Ud(c,function(a){return sd(a,b)},!0,d)}
function T(a){var b="mouseover"==a.type&&"mouseenter"in Th||"mouseout"==a.type&&"mouseleave"in Th,c=a.type in Th||b;if("HTML"!=a.target.tagName&&c){if(b){var b="mouseover"==a.type?"mouseenter":"mouseleave",c=Th[b],d;for(d in c.H){var e=Vh(b,d,a.target);e&&!Ud(a.relatedTarget,function(a){return a==e},!0)&&c.N(d,e,b,a)}}if(b=Th[a.type])for(d in b.H)(e=Vh(a.type,d,a.target))&&b.N(d,e,a.type,a)}}
M(document,"blur",T,!0);M(document,"change",T,!0);M(document,"click",T);M(document,"focus",T,!0);M(document,"mouseover",T);M(document,"mouseout",T);M(document,"mousedown",T);M(document,"keydown",T);M(document,"keyup",T);M(document,"keypress",T);M(document,"cut",T);M(document,"paste",T);Uh&&(M(document,"touchstart",T),M(document,"touchend",T),M(document,"touchcancel",T));function Wh(a){this.j=a;this.g={};this.ja=[];this.i=[]}
function U(a,b){return"yt-uix"+(a.j?"-"+a.j:"")+(b?"-"+b:"")}
Wh.prototype.register=aa;Wh.prototype.unregister=function(){Sb(this.ja);this.ja.length=0;ug(this.i);this.i.length=0};
Wh.prototype.init=t;Wh.prototype.dispose=t;function Xh(a,b,c){a.i.push(R(b,c,a))}
function Yh(a,b,c){var d=U(a,void 0),e=w(c,a);b in Th||(Th[b]=new E);Th[b].subscribe(d,e);a.g[c]=e}
function Zh(a,b,c){if(b in Th){var d=Th[b];d.unsubscribe(U(a,void 0),a.g[c]);0>=d.ba()&&(d.dispose(),delete Th[b])}delete a.g[c]}
function $h(a,b){hb(a,"tooltip-text",b)}
;function ai(){Wh.call(this,"tooltip");this.b=0;this.f={}}
y(ai,Wh);ba(ai);k=ai.prototype;k.register=function(){Yh(this,"mouseover",this.ia);Yh(this,"mouseout",this.S);Yh(this,"focus",this.Ha);Yh(this,"blur",this.Fa);Yh(this,"click",this.S);Yh(this,"touchstart",this.Ua);Yh(this,"touchend",this.ka);Yh(this,"touchcancel",this.ka)};
k.unregister=function(){Zh(this,"mouseover",this.ia);Zh(this,"mouseout",this.S);Zh(this,"focus",this.Ha);Zh(this,"blur",this.Fa);Zh(this,"click",this.S);Zh(this,"touchstart",this.Ua);Zh(this,"touchend",this.ka);Zh(this,"touchcancel",this.ka);this.dispose();ai.B.unregister.call(this)};
k.dispose=function(){for(var a in this.f)this.S(this.f[a]);this.f={}};
k.ia=function(a){if(!(this.b&&1E3>x()-this.b)){var b=parseInt(C(a,"tooltip-hide-timer"),10);b&&(jb(a,"tooltip-hide-timer"),Hb(b));var b=w(function(){bi(this,a);jb(a,"tooltip-show-timer")},this),c=parseInt(C(a,"tooltip-show-delay"),10)||0,b=H(b,c);
hb(a,"tooltip-show-timer",b.toString());a.title&&($h(a,ci(a)),a.title="");b=ha(a).toString();this.f[b]=a}};
k.S=function(a){var b=parseInt(C(a,"tooltip-show-timer"),10);b&&(Hb(b),jb(a,"tooltip-show-timer"));b=w(function(){if(a){var b=Ed(di(this,a));b&&(ei(b),b&&b.parentNode&&b.parentNode.removeChild(b),jb(a,"content-id"));(b=Ed(di(this,a,"arialabel")))&&b.parentNode&&b.parentNode.removeChild(b)}jb(a,"tooltip-hide-timer")},this);
b=H(b,50);hb(a,"tooltip-hide-timer",b.toString());if(b=C(a,"tooltip-text"))a.title=b;b=ha(a).toString();delete this.f[b]};
k.Ha=function(a){this.b=0;this.ia(a)};
k.Fa=function(a){this.b=0;this.S(a)};
k.Ua=function(a,b,c){c.changedTouches&&(this.b=0,a=Vh(b,U(this),c.changedTouches[0].target),this.ia(a))};
k.ka=function(a,b,c){c.changedTouches&&(this.b=x(),a=Vh(b,U(this),c.changedTouches[0].target),this.S(a))};
function fi(a,b){$h(a,b);a=C(a,"content-id");(a=Ed(a))&&Nd(a,b)}
function ci(a){return C(a,"tooltip-text")||a.title}
function bi(a,b){if(b){var c=ci(b);if(c){var d=Ed(di(a,b));if(!d){d=document.createElement("div");d.id=di(a,b);d.className=U(a,"tip");var e=document.createElement("div");e.className=U(a,"tip-body");var f=document.createElement("div");f.className=U(a,"tip-arrow");var g=document.createElement("div");g.setAttribute("aria-hidden","true");g.className=U(a,"tip-content");var l=gi(a,b),h=di(a,b,"content");g.id=h;hb(b,"content-id",h);e.appendChild(g);l&&d.appendChild(l);d.appendChild(e);d.appendChild(f);var m=
Qd(b),h=di(a,b,"arialabel"),f=document.createElement("div");td(f,U(a,"arialabel"));f.id=h;m=b.hasAttribute("aria-label")?b.getAttribute("aria-label"):"rtl"==document.body.getAttribute("dir")?c+" "+m:m+" "+c;Nd(f,m);b.setAttribute("aria-labelledby",h);h=Ph()||document.body;h.appendChild(f);h.appendChild(d);fi(b,c);(c=parseInt(C(b,"tooltip-max-width"),10))&&e.offsetWidth>c&&(e.style.width=c+"px",td(g,U(a,"normal-wrap")));g=sd(b,U(a,"reverse"));hi(a,b,d,e,l,g)||hi(a,b,d,e,l,!g);var u=U(a,"tip-visible");
H(function(){td(d,u)},0)}}}}
function hi(a,b,c,d,e,f){var g;vd(c,U(a,"tip-reverse"),f);var l=0;f&&(l=1);a=hf(b);f=new L((a.width-10)/2,f?a.height:0);var h=Dd(b);g=new L(0,0);var m;m=h?Dd(h):document;m=!I||9<=Number(Bc)||Id(Ad(m).b)?m.documentElement:m.body;b!=m&&(m=ff(b),h=Jd(Ad(h).b),g.l=m.left+h.l,g.o=m.top+h.o);f=new L(g.l+f.l,g.o+f.o);f=new L(f.l,f.o);g=(l&8&&"rtl"==ef(c,"direction")?l^4:l)&-9;l=hf(c);h=new od(l.width,l.height);f=new L(f.l,f.o);h=new od(h.width,h.height);0!=g&&(g&4?f.l-=h.width+0:g&2&&(f.l-=h.width/2),g&
1&&(f.o-=h.height+0));g=new cf(0,0,0,0);g.left=f.l;g.top=f.o;g.width=h.width;g.height=h.height;f=g;g=0;if(!(g&496||(g=f,h=new L(g.left,g.top),h instanceof L?(g=h.l,h=h.o):(g=h,h=void 0),c.style.left=gf(g,!1),c.style.top=gf(h,!1),h=new od(f.width,f.height),l==h||l&&h&&l.width==h.width&&l.height==h.height)))if(l=h,g=Id(Ad(Dd(c)).b),!I||J("10")||g&&J("8"))f=c.style,sc?f.MozBoxSizing="border-box":tc?f.WebkitBoxSizing="border-box":f.boxSizing="border-box",f.width=Math.max(l.width,0)+"px",f.height=Math.max(l.height,
0)+"px";else if(f=c.style,g){if(I){g=lf(c,"paddingLeft");h=lf(c,"paddingRight");m=lf(c,"paddingTop");var u=lf(c,"paddingBottom");g=new bf(m,h,u,g)}else g=df(c,"paddingLeft"),h=df(c,"paddingRight"),m=df(c,"paddingTop"),u=df(c,"paddingBottom"),g=new bf(parseFloat(m),parseFloat(h),parseFloat(u),parseFloat(g));if(!I||9<=Number(Bc))h=df(c,"borderLeftWidth"),m=df(c,"borderRightWidth"),u=df(c,"borderTopWidth"),Ba=df(c,"borderBottomWidth"),h=new bf(parseFloat(u),parseFloat(m),parseFloat(Ba),parseFloat(h));
else{h=nf(c,"borderLeft");m=nf(c,"borderRight");var u=nf(c,"borderTop"),Ba=nf(c,"borderBottom"),h=new bf(u,m,Ba,h)}f.pixelWidth=l.width-h.left-g.left-g.right-h.right;f.pixelHeight=l.height-h.top-g.top-g.bottom-h.bottom}else f.pixelWidth=l.width,f.pixelHeight=l.height;f=Hd(window);1==c.nodeType?(c=ff(c),h=new L(c.left,c.top)):(c=c.changedTouches?c.changedTouches[0]:c,h=new L(c.clientX,c.clientY));c=hf(d);m=Math.floor(c.width/2);l=!!(f.height<h.o+a.height);a=!!(h.o<a.height);g=!!(h.l<m);f=!!(f.width<
h.l+m);h=(c.width+3)/-2- -5;b=C(b,"force-tooltip-direction");if("left"==b||g)h=-5;else if("right"==b||f)h=20-c.width-3;b=Math.floor(h)+"px";d.style.left=b;e&&(e.style.left=b,e.style.height=c.height+"px",e.style.width=c.width+"px");return!(l||a)}
function di(a,b,c){a=U(a);var d=b.__yt_uid_key;d||(d=Vd(),b.__yt_uid_key=d);b=a+d;c&&(b+="-"+c);return b}
function gi(a,b){var c=null;uc&&sd(b,U(a,"masked"))&&((c=Ed("yt-uix-tooltip-shared-mask"))?(c.parentNode.removeChild(c),Rh(c)):(c=document.createElement("iframe"),c.src='javascript:""',c.id="yt-uix-tooltip-shared-mask",c.className=U(a,"tip-mask")));return c}
function ei(a){var b=Ed("yt-uix-tooltip-shared-mask"),c=b&&Ud(b,function(b){return b==a},!1,2);
b&&c&&(b.parentNode.removeChild(b),Sh(b),document.body.appendChild(b))}
;function ii(){Wh.call(this,"subscription-button")}
y(ii,Wh);ba(ii);ii.prototype.register=function(){Yh(this,"click",this.qa);Xh(this,zh,this.La);Xh(this,Ah,this.Ka);Xh(this,Bh,this.Fb);Xh(this,Eh,this.La);Xh(this,Fh,this.Ka);Xh(this,Gh,this.Hb);Xh(this,Ih,this.yb);Xh(this,Jh,this.xb)};
ii.prototype.unregister=function(){Zh(this,"click",this.qa);ii.B.unregister.call(this)};
var Td={ya:"hover-enabled",Za:"yt-uix-button-subscribe",$a:"yt-uix-button-subscribed",Xb:"ypc-enabled",ab:"yt-uix-button-subscription-container",bb:"yt-subscription-button-disabled-mask-container"},ji={Yb:"channel-external-id",cb:"subscriber-count-show-when-subscribed",eb:"subscriber-count-tooltip",fb:"subscriber-count-title",Zb:"href",$b:"insecure",za:"is-subscribed",ac:"parent-url",cc:"clicktracking",gb:"style-type",Aa:"subscription-id",dc:"target",ib:"ypc-enabled"};k=ii.prototype;
k.qa=function(a){var b=C(a,"href"),c=C(a,"insecure"),d=Oh(),c=c&&!0;if(b)a=C(a,"target")||"_self",window.open(b,a);else if(!c)if(d){b=C(a,"channel-external-id");d=C(a,"clicktracking");if(C(a,"ypc-enabled"))var c=C(a,"ypc-item-type"),e=C(a,"ypc-item-id"),c={itemType:c,itemId:e,subscriptionElement:a};else c=null;e=C(a,"parent-url");if(C(a,"is-subscribed")){var f=C(a,"subscription-id");Q(Dh,new vh(b,f,c,a,d,e))}else Q(yh,new th(b,c,d,e))}else ki(this,a)};
k.La=function(a){this.V(a.b,this.Sa,!0)};
k.Ka=function(a){this.V(a.b,this.Sa,!1)};
k.Fb=function(a){this.V(a.b,this.Ta,!0,a.subscriptionId)};
k.Hb=function(a){this.V(a.b,this.Ta,!1)};
k.yb=function(a){this.V(a.b,this.nb)};
k.xb=function(a){this.V(a.b,this.mb)};
k.Ta=function(a,b,c){b?(hb(a,ji.za,"true"),c&&hb(a,ji.Aa,c)):(jb(a,ji.za),jb(a,ji.Aa));li(a)};
k.Sa=function(a,b){var c;c=Sd(a);vd(c,Td.bb,b);a.setAttribute("aria-busy",b?"true":"false");a.disabled=b};
function li(a){var b=C(a,ji.gb),c=!!C(a,"is-subscribed"),b="-"+b,d=Td.$a+b;vd(a,Td.Za+b,!c);vd(a,d,c);C(a,ji.eb)&&!C(a,ji.cb)&&(b=U(ai.getInstance()),vd(a,b,!c),a.title=c?"":C(a,ji.fb));c?H(function(){td(a,Td.ya)},1E3):ud(a,Td.ya)}
k.nb=function(a){var b=!!C(a,"ypc-item-type"),c=!!C(a,"ypc-item-id");!C(a,"ypc-enabled")&&b&&c&&(td(a,"ypc-enabled"),hb(a,ji.ib,"true"))};
k.mb=function(a){C(a,"ypc-enabled")&&(ud(a,"ypc-enabled"),jb(a,"ypc-enabled"))};
function mi(a,b){return wa(Fd(U(a)),function(a){return b==C(a,"channel-external-id")},a)}
k.jb=function(a,b,c){var d=Ja(arguments,2);z(a,function(a){b.apply(this,Da(a,d))},this)};
k.V=function(a,b,c){var d=mi(this,a);this.jb.apply(this,Da([d],Ja(arguments,1)))};
function ki(a,b){a=w(function(a){a.discoverable_subscriptions&&F("SUBSCRIBE_EMBED_DISCOVERABLE_SUBSCRIPTIONS",a.discoverable_subscriptions);this.qa(b)},a);
Nh(a,"subscribe")}
;var ni=window.yt&&window.yt.uix&&window.yt.uix.widgets_||{};q("yt.uix.widgets_",ni,void 0);var oi=window,pi=document,qi=oi.location;function ri(){}
var si=/\[native code\]/;function V(a,b,c){return a[b]=a[b]||c}
function ti(a){for(var b=0;b<this.length;b++)if(this[b]===a)return b;return-1}
function ui(a){a=a.sort();for(var b=[],c=void 0,d=0;d<a.length;d++){var e=a[d];e!=c&&b.push(e);c=e}return b}
function W(){var a;if((a=Object.create)&&si.test(a))a=a(null);else{a={};for(var b in a)a[b]=void 0}return a}
var vi=V(oi,"gapi",{});var X;X=V(oi,"___jsl",W());V(X,"I",0);V(X,"hel",10);function wi(){var a=qi.href,b;if(X.dpo)b=X.h;else{b=X.h;var c=/([#].*&|[#])jsh=([^&#]*)/g,d=/([?#].*&|[?#])jsh=([^&#]*)/g;if(a=a&&(c.exec(a)||d.exec(a)))try{b=decodeURIComponent(a[2])}catch(e){}}return b}
function xi(a){var b=V(X,"PQ",[]);X.PQ=[];var c=b.length;if(0===c)a();else for(var d=0,e=function(){++d===c&&a()},f=0;f<c;f++)b[f](e)}
function yi(a){return V(V(X,"H",W()),a,W())}
;var zi=V(X,"perf",W());V(zi,"g",W());var Ai=V(zi,"i",W());V(zi,"r",[]);W();W();function Bi(a,b,c){b&&0<b.length&&(b=Ci(b),c&&0<c.length&&(b+="___"+Ci(c)),28<b.length&&(b=b.substr(0,28)+(b.length-28)),c=b,b=V(Ai,"_p",W()),V(b,c,W())[a]=(new Date).getTime(),b=zi.r,"function"===typeof b?b(a,"_p",c):b.push([a,"_p",c]))}
function Ci(a){return a.join("__").replace(/\./g,"_").replace(/\-/g,"_").replace(/\,/g,"_")}
;var Di=W(),Ei=[];function Fi(a){throw Error("Bad hint"+(a?": "+a:""));}
;Ei.push(["jsl",function(a){for(var b in a)if(Object.prototype.hasOwnProperty.call(a,b)){var c=a[b];"object"==typeof c?X[b]=V(X,b,[]).concat(c):V(X,b,c)}if(b=a.u)a=V(X,"us",[]),a.push(b),(b=/^https:(.*)$/.exec(b))&&a.push("http:"+b[1])}]);var Gi=/^(\/[a-zA-Z0-9_\-]+)+$/,Hi=/^[a-zA-Z0-9\-_\.,!]+$/,Ii=/^gapi\.loaded_[0-9]+$/,Ji=/^[a-zA-Z0-9,._-]+$/;function Ki(a,b,c,d){var e=a.split(";"),f=e.shift(),g=Di[f],l=null;g?l=g(e,b,c,d):Fi("no hint processor for: "+f);l||Fi("failed to generate load url");b=l;c=b.match(Li);(d=b.match(Mi))&&1===d.length&&Ni.test(b)&&c&&1===c.length||Fi("failed sanity: "+a);return l}
function Oi(a,b,c,d){function e(a){return encodeURIComponent(a).replace(/%2C/g,",")}
a=Pi(a);Ii.test(c)||Fi("invalid_callback");b=Qi(b);d=d&&d.length?Qi(d):null;return[encodeURIComponent(a.Jb).replace(/%2C/g,",").replace(/%2F/g,"/"),"/k=",e(a.version),"/m=",e(b),d?"/exm="+e(d):"","/rt=j/sv=1/d=1/ed=1",a.Da?"/am="+e(a.Da):"",a.Qa?"/rs="+e(a.Qa):"",a.Ya?"/t="+e(a.Ya):"","/cb=",e(c)].join("")}
function Pi(a){"/"!==a.charAt(0)&&Fi("relative path");for(var b=a.substring(1).split("/"),c=[];b.length;){a=b.shift();if(!a.length||0==a.indexOf("."))Fi("empty/relative directory");else if(0<a.indexOf("=")){b.unshift(a);break}c.push(a)}a={};for(var d=0,e=b.length;d<e;++d){var f=b[d].split("="),g=decodeURIComponent(f[0]),l=decodeURIComponent(f[1]);2==f.length&&g&&l&&(a[g]=a[g]||l)}b="/"+c.join("/");Gi.test(b)||Fi("invalid_prefix");c=Ri(a,"k",!0);d=Ri(a,"am");e=Ri(a,"rs");a=Ri(a,"t");return{Jb:b,version:c,
Da:d,Qa:e,Ya:a}}
function Qi(a){for(var b=[],c=0,d=a.length;c<d;++c){var e=a[c].replace(/\./g,"_").replace(/-/g,"_");Ji.test(e)&&b.push(e)}return b.join(",")}
function Ri(a,b,c){a=a[b];!a&&c&&Fi("missing: "+b);if(a){if(Hi.test(a))return a;Fi("invalid: "+b)}return null}
var Ni=/^https?:\/\/[a-z0-9_.-]+\.google\.com(:\d+)?\/[a-zA-Z0-9_.,!=\-\/]+$/,Mi=/\/cb=/g,Li=/\/\//g;function Si(){var a=wi();if(!a)throw Error("Bad hint");return a}
Di.m=function(a,b,c,d){(a=a[0])||Fi("missing_hint");return"https://apis.google.com"+Oi(a,b,c,d)};var Ti=decodeURI("%73cript"),Ui=/^[-+_0-9\/A-Za-z]+={0,2}$/;function Vi(a,b){for(var c=[],d=0;d<a.length;++d){var e=a[d];e&&0>ti.call(b,e)&&c.push(e)}return c}
function Wi(){var a=X.nonce;if(void 0!==a)return a&&a===String(a)&&a.match(Ui)?a:X.nonce=null;var b=V(X,"us",[]);if(!b||!b.length)return X.nonce=null;for(var c=pi.getElementsByTagName(Ti),d=0,e=c.length;d<e;++d){var f=c[d];if(f.src&&(a=String(f.getAttribute("nonce")||"")||null)){for(var g=0,l=b.length;g<l&&b[g]!==f.src;++g);if(g!==l&&a&&a===String(a)&&a.match(Ui))return X.nonce=a}}return null}
function Xi(a){if("loading"!=pi.readyState)Yi(a);else{var b=Wi(),c="";null!==b&&(c=' nonce="'+b+'"');pi.write("<"+Ti+' src="'+encodeURI(a)+'"'+c+"></"+Ti+">")}}
function Yi(a){var b=pi.createElement(Ti);b.setAttribute("src",a);a=Wi();null!==a&&b.setAttribute("nonce",a);b.async="true";(a=pi.getElementsByTagName(Ti)[0])?a.parentNode.insertBefore(b,a):(pi.head||pi.body||pi.documentElement).appendChild(b)}
function Zi(a,b){var c=b&&b._c;if(c)for(var d=0;d<Ei.length;d++){var e=Ei[d][0],f=Ei[d][1];f&&Object.prototype.hasOwnProperty.call(c,e)&&f(c[e],a,b)}}
function $i(a,b,c){aj(function(){var c;c=b===wi()?V(vi,"_",W()):W();c=V(yi(b),"_",c);a(c)},c)}
function bj(a,b){var c=b||{};"function"==typeof b&&(c={},c.callback=b);Zi(a,c);b=a?a.split(":"):[];var d=c.h||Si(),e=V(X,"ah",W());if(e["::"]&&b.length){a=[];for(var f=null;f=b.shift();){var g=f.split("."),g=e[f]||e[g[1]&&"ns:"+g[0]||""]||d,l=a.length&&a[a.length-1]||null,h=l;l&&l.hint==g||(h={hint:g,features:[]},a.push(h));h.features.push(f)}var m=a.length;if(1<m){var u=c.callback;u&&(c.callback=function(){0==--m&&u()})}for(;b=a.shift();)cj(b.features,c,b.hint)}else cj(b||[],c,d)}
function cj(a,b,c){function d(a,b){if(Ba)return 0;oi.clearTimeout(u);Cd.push.apply(Cd,ia);var d=((vi||{}).config||{}).update;d?d(f):f&&V(X,"cu",[]).push(f);if(b){Bi("me0",a,qc);try{$i(b,c,m)}finally{Bi("me1",a,qc)}}return 1}
a=ui(a)||[];var e=b.callback,f=b.config,g=b.timeout,l=b.ontimeout,h=b.onerror,m=void 0;"function"==typeof h&&(m=h);var u=null,Ba=!1;if(g&&!l||!g&&l)throw"Timeout requires both the timeout parameter and ontimeout parameter to be set";var h=V(yi(c),"r",[]).sort(),Cd=V(yi(c),"L",[]).sort(),qc=[].concat(h);0<g&&(u=oi.setTimeout(function(){Ba=!0;l()},g));
var ia=Vi(a,Cd);if(ia.length){var ia=Vi(a,h),Ha=V(X,"CP",[]),Ia=Ha.length;Ha[Ia]=function(a){function b(){var a=Ha[Ia+1];a&&a()}
function c(b){Ha[Ia]=null;d(ia,a)&&xi(function(){e&&e();b()})}
if(!a)return 0;Bi("ml1",ia,qc);0<Ia&&Ha[Ia-1]?Ha[Ia]=function(){c(b)}:c(b)};
if(ia.length){var te="loaded_"+X.I++;vi[te]=function(a){Ha[Ia](a);vi[te]=null};
a=Ki(c,ia,"gapi."+te,h);h.push.apply(h,ia);Bi("ml0",ia,qc);b.sync||oi.___gapisync?Xi(a):Yi(a)}else Ha[Ia](ri)}else d(ia)&&e&&e()}
;function aj(a,b){if(X.hee&&0<X.hel)try{return a()}catch(c){b&&b(c),X.hel--,bj("debug_error",function(){try{window.___jsl.hefn(c)}catch(d){throw c;}})}else try{return a()}catch(c){throw b&&b(c),c;
}}
;vi.load=function(a,b){return aj(function(){return bj(a,b)})};function dj(a){this.b=a||dg();ej||(ej=fj(this.b))}
var ej=null;function fj(a){return(new N(function(b){try{var c={gapiHintOverride:a.gapiHintOverride,_c:{jsl:{h:a.gapiHintParams}},callback:b},d=fa(c)?{callback:c}:c||{};d._c&&d._c.jsl&&d._c.jsl.h||Sa(d,{_c:{jsl:{h:G("GAPI_HINT_PARAMS",void 0)}}});if(d.gapiHintOverride||G("GAPI_HINT_OVERRIDE")){var e;var f=document.location.href;if(-1!=f.indexOf("?")){var f=(f||"").split("#")[0],g=f.split("?",2);e=Uc(1<g.length?g[1]:g[0])}else e={};var l=e.gapi_jsh;l&&Sa(d,{_c:{jsl:{h:l}}})}bj("client",d)}catch(h){Ib(h)}})).then(function(){})}
dj.prototype.i=function(){var a=r("gapi.config.update");a("googleapis.config/auth/useFirstPartyAuth",!0);var b=this.b.apiaryHost;ra(null==b?"":String(b))||a("googleapis.config/root",(-1==b.indexOf("://")?"//":"")+b);b=this.b.Ea;ra(null==b?"":String(b))||a("googleapis.config/root-1p",(-1==b.indexOf("://")?"//":"")+b);a("googleapis.config/sessionIndex",G("SESSION_INDEX"));r("gapi.client.setApiKey")(this.b.innertubeApiKey)};
dj.prototype.f=function(){return{context:eg(this.b)}};
dj.prototype.g=function(a,b,c){var d,e=!1;0<c.timeout&&(d=H(function(){e||(e=!0,c.R&&c.R())},c.timeout));
gj(this,a,b,function(a){if(!e)if(e=!0,d&&Hb(d),a)c.F&&c.F(a);else if(c.onError)c.onError()})};
function gj(a,b,c,d){var e={path:"/youtubei/"+a.b.innertubeApiVersion+"/"+b,headers:{"X-Goog-Visitor-Id":G("VISITOR_DATA")},method:"POST",body:Gc(c)},f=w(a.i,a);ej.then(function(){f();r("gapi.client.request")(e).execute(d||t)})}
;function hj(a){if(!a)return"";a=a.split("#")[0].split("?")[0];a=a.toLowerCase();0==a.indexOf("//")&&(a=window.location.protocol+a);/^[\w\-]*:\/\//.test(a)||(a=window.location.href);var b=a.substring(a.indexOf("://")+3),c=b.indexOf("/");-1!=c&&(b=b.substring(0,c));a=a.substring(0,a.indexOf("://"));if("http"!==a&&"https"!==a&&"chrome-extension"!==a&&"file"!==a&&"android-app"!==a)throw Error("Invalid URI scheme in origin");var c="",d=b.indexOf(":");if(-1!=d){var e=b.substring(d+1),b=b.substring(0,d);
if("http"===a&&"80"!==e||"https"===a&&"443"!==e)c=":"+e}return a+"://"+b+c}
;function ij(){function a(){e[0]=1732584193;e[1]=4023233417;e[2]=2562383102;e[3]=271733878;e[4]=3285377520;u=m=0}
function b(a){for(var b=g,c=0;64>c;c+=4)b[c/4]=a[c]<<24|a[c+1]<<16|a[c+2]<<8|a[c+3];for(c=16;80>c;c++)a=b[c-3]^b[c-8]^b[c-14]^b[c-16],b[c]=(a<<1|a>>>31)&4294967295;a=e[0];for(var d=e[1],f=e[2],h=e[3],l=e[4],m,u,c=0;80>c;c++)40>c?20>c?(m=h^d&(f^h),u=1518500249):(m=d^f^h,u=1859775393):60>c?(m=d&f|h&(d|f),u=2400959708):(m=d^f^h,u=3395469782),m=((a<<5|a>>>27)&4294967295)+m+l+u+b[c]&4294967295,l=h,h=f,f=(d<<30|d>>>2)&4294967295,d=a,a=m;e[0]=e[0]+a&4294967295;e[1]=e[1]+d&4294967295;e[2]=e[2]+f&4294967295;
e[3]=e[3]+h&4294967295;e[4]=e[4]+l&4294967295}
function c(a,c){if("string"===typeof a){a=unescape(encodeURIComponent(a));for(var d=[],e=0,g=a.length;e<g;++e)d.push(a.charCodeAt(e));a=d}c||(c=a.length);d=0;if(0==m)for(;d+64<c;)b(a.slice(d,d+64)),d+=64,u+=64;for(;d<c;)if(f[m++]=a[d++],u++,64==m)for(m=0,b(f);d+64<c;)b(a.slice(d,d+64)),d+=64,u+=64}
function d(){var a=[],d=8*u;56>m?c(l,56-m):c(l,64-(m-56));for(var g=63;56<=g;g--)f[g]=d&255,d>>>=8;b(f);for(g=d=0;5>g;g++)for(var h=24;0<=h;h-=8)a[d++]=e[g]>>h&255;return a}
for(var e=[],f=[],g=[],l=[128],h=1;64>h;++h)l[h]=0;var m,u;a();return{reset:a,update:c,digest:d,lb:function(){for(var a=d(),b="",c=0;c<a.length;c++)b+="0123456789ABCDEF".charAt(Math.floor(a[c]/16))+"0123456789ABCDEF".charAt(a[c]%16);return b}}}
;function jj(a,b,c){var d=[],e=[];if(1==(da(c)?2:1))return e=[b,a],z(d,function(a){e.push(a)}),kj(e.join(" "));
var f=[],g=[];z(c,function(a){g.push(a.key);f.push(a.value)});
c=Math.floor((new Date).getTime()/1E3);e=0==f.length?[c,b,a]:[f.join(":"),c,b,a];z(d,function(a){e.push(a)});
a=kj(e.join(" "));a=[c,a];0==g.length||a.push(g.join(""));return a.join("_")}
function kj(a){var b=ij();b.update(a);return b.lb().toLowerCase()}
;function lj(){this.b=dg()}
lj.prototype.f=function(){return{context:eg(this.b)}};
lj.prototype.g=function(a,b,c){b={headers:{"Content-Type":"application/json","X-Goog-Visitor-Id":G("VISITOR_DATA")},C:b,Oa:"JSON",R:c.R,F:c.F,onError:c.onError,timeout:c.timeout,withCredentials:!0};a:{c=[];var d=hj(String(n.location.href)),e=n.__OVERRIDE_SID;null==e&&(e=(new Se(document)).get("SID"));if(e&&(d=(e=0==d.indexOf("https:")||0==d.indexOf("chrome-extension:"))?n.__SAPISID:n.__APISID,null==d&&(d=(new Se(document)).get(e?"SAPISID":"APISID")),d)){var e=e?"SAPISIDHASH":"APISIDHASH",f=String(n.location.href);
c=f&&d&&e?[e,jj(hj(f),d,c||null)].join(" "):null;break a}c=null}c&&(b.headers.Authorization=c,b.headers["X-Goog-AuthUser"]=G("SESSION_INDEX",0));ad("//"+this.b.Vb+("/youtubei/"+this.b.innertubeApiVersion+"/"+a)+"?alt=json&key="+this.b.innertubeApiKey,b)};function Ne(){return de("enable_youtubei_innertube")?lj:dj}
;function mj(a){return(0==a.search("cue")||0==a.search("load"))&&"loadModule"!=a}
function nj(a,b,c){v(a)&&(a={mediaContentUrl:a,startSeconds:b,suggestedQuality:c});b=/\/([ve]|embed)\/([^#?]+)/.exec(a.mediaContentUrl);a.videoId=b&&b[2]?b[2]:null;return oj(a)}
function oj(a,b,c){if(ga(a)){b="endSeconds startSeconds mediaContentUrl suggestedQuality videoId two_stage_token".split(" ");c={};for(var d=0;d<b.length;d++){var e=b[d];a[e]&&(c[e]=a[e])}return c}return{videoId:a,startSeconds:b,suggestedQuality:c}}
function pj(a,b,c,d){if(ga(a)&&!da(a)){b="playlist list listType index startSeconds suggestedQuality".split(" ");c={};for(d=0;d<b.length;d++){var e=b[d];a[e]&&(c[e]=a[e])}return c}c={index:b,startSeconds:c,suggestedQuality:d};v(a)&&16==a.length?c.list="PL"+a:c.playlist=a;return c}
function qj(a){var b=a.video_id||a.videoId;if(v(b)){var c=pe()||{},d=pe()||{};p(void 0)?d[b]=void 0:delete d[b];var e=x()+3E5,f=re;if(f&&window.JSON){v(d)||(d=JSON.stringify(d,void 0));try{f.set("yt-player-two-stage-token",d,e)}catch(g){f.remove("yt-player-two-stage-token")}}(b=c[b])&&(a.two_stage_token=b)}}
;var rj=null,sj=[];function tj(a){return{externalChannelId:a.externalChannelId,wb:!!a.isChannelPaid,source:a.source,subscriptionId:a.subscriptionId}}
function uj(a){vj(tj(a))}
function vj(a){Oh()?(Q(yh,new th(a.externalChannelId,a.wb?{itemType:"U",itemId:a.externalChannelId}:null)),(a="/gen_204?"+Sc({event:"subscribe",source:a.source}))&&mg(a)):wj(a)}
function wj(a){Nh(function(b){b.subscription_ajax&&vj(a)},null)}
function xj(a){a=tj(a);Q(Dh,new vh(a.externalChannelId,a.subscriptionId,null));(a="/gen_204?"+Sc({event:"unsubscribe",source:a.source}))&&mg(a)}
function yj(a){rj&&rj.channelSubscribed(a.b,a.subscriptionId)}
function zj(a){rj&&rj.channelUnsubscribed(a.b)}
;function Aj(a){D.call(this);this.g=a;this.g.subscribe("command",this.Pa,this);this.i={};this.j=!1}
y(Aj,D);k=Aj.prototype;k.start=function(){this.j||this.f||(this.j=!0,Bj(this.g,"RECEIVING"))};
k.Wa=aa;k.addEventListener=aa;k.removeEventListener=aa;k.Pa=function(a,b){if(this.j&&!this.f){var c=b||{};switch(a){case "addEventListener":v(c.event)&&(a=c.event,a in this.i||(b=w(this.Nb,this,a),this.i[a]=b,this.addEventListener(a,b)));break;case "removeEventListener":v(c.event)&&Cj(this,c.event);break;default:this.Wa(a,b)}}};
k.Nb=function(a,b){this.j&&!this.f&&Bj(this.g,a,this.ra(a,b))};
k.ra=function(a,b){if(null!=b)return{value:b}};
function Cj(a,b){b in a.i&&(a.removeEventListener(b,a.i[b]),delete a.i[b])}
k.A=function(){this.g.unsubscribe("command",this.Pa,this);this.g=null;for(var a in this.i)Cj(this,a);Aj.B.A.call(this)};function Dj(a,b){Aj.call(this,b);this.b=a;this.start()}
y(Dj,Aj);k=Dj.prototype;k.addEventListener=function(a,b){this.b.addEventListener(a,b)};
k.removeEventListener=function(a,b){this.b.removeEventListener(a,b)};
k.Wa=function(a,b){this.b.isReady()&&this.b[a]&&(b=Ej(a,b||{}),b=this.b[a].apply(this.b,b),(b=Fj(a,b))&&this.j&&!this.f&&Bj(this.g,a,b))};
function Ej(a,b){switch(a){case "loadVideoById":return b=oj(b),qj(b),[b];case "cueVideoById":return b=oj(b),qj(b),[b];case "loadVideoByPlayerVars":return qj(b),[b];case "cueVideoByPlayerVars":return qj(b),[b];case "loadPlaylist":return b=pj(b),qj(b),[b];case "cuePlaylist":return b=pj(b),qj(b),[b];case "seekTo":return[b.seconds,b.allowSeekAhead];case "playVideoAt":return[b.index];case "setVolume":return[b.volume];case "setPlaybackQuality":return[b.suggestedQuality];case "setPlaybackRate":return[b.suggestedRate];
case "setLoop":return[b.loopPlaylists];case "setShuffle":return[b.shufflePlaylist];case "getOptions":return[b.module];case "getOption":return[b.module,b.option];case "setOption":return[b.module,b.option,b.value];case "handleGlobalKeyDown":return[b.keyCode,b.shiftKey]}return[]}
function Fj(a,b){switch(a){case "isMuted":return{muted:b};case "getVolume":return{volume:b};case "getPlaybackRate":return{playbackRate:b};case "getAvailablePlaybackRates":return{availablePlaybackRates:b};case "getVideoLoadedFraction":return{videoLoadedFraction:b};case "getPlayerState":return{playerState:b};case "getCurrentTime":return{currentTime:b};case "getPlaybackQuality":return{playbackQuality:b};case "getAvailableQualityLevels":return{availableQualityLevels:b};case "getDuration":return{duration:b};
case "getVideoUrl":return{videoUrl:b};case "getVideoEmbedCode":return{videoEmbedCode:b};case "getPlaylist":return{playlist:b};case "getPlaylistIndex":return{playlistIndex:b};case "getOptions":return{options:b};case "getOption":return{option:b}}}
k.ra=function(a,b){switch(a){case "onReady":return;case "onStateChange":return{playerState:b};case "onPlaybackQualityChange":return{playbackQuality:b};case "onPlaybackRateChange":return{playbackRate:b};case "onError":return{errorCode:b}}return Dj.B.ra.call(this,a,b)};
k.A=function(){Dj.B.A.call(this);delete this.b};function Gj(a,b,c,d){D.call(this);this.i=b||null;this.L="*";this.j=c||null;this.b=null;this.channel=d||null;this.O=!!a;this.G=w(this.P,this);window.addEventListener("message",this.G)}
y(Gj,D);Gj.prototype.P=function(a){if(!("*"!=this.j&&a.origin!=this.j||this.i&&a.source!=this.i)&&v(a.data)){var b;try{b=Ec(a.data)}catch(c){return}null!=b&&(this.O&&(this.b&&this.b!=b.id||this.channel&&this.channel!=b.channel)||b&&this.J(a,b))}};
Gj.prototype.J=aa;Gj.prototype.sendMessage=function(a,b){if(b=b||this.i){this.b&&(a.id=this.b);this.channel&&(a.channel=this.channel);try{var c=Gc(a);b.postMessage(c,this.L)}catch(d){Ib(d,"WARNING")}}};
Gj.prototype.A=function(){window.removeEventListener("message",this.G);Gj.B.A.call(this)};function Hj(a,b,c){Gj.call(this,a,b,c||G("POST_MESSAGE_ORIGIN",void 0)||window.document.location.protocol+"//"+window.document.location.hostname,"widget");this.D=this.g=this.w=null}
y(Hj,Gj);Hj.prototype.J=function(a,b){switch(b.event){case "listening":"null"!=a.origin?this.j=this.L=a.origin:Ib(Error("MessageEvent origin is null"),"WARNING");this.i=a.source;this.b=b.id;this.g&&(this.g(),this.g=null);break;case "command":this.w&&(this.D&&!Aa(this.D,b.func)||this.w(b.func,b.args))}};function Ij(){var a=this.f=new Hj(!!G("WIDGET_ID_ENFORCE")),b=w(this.Kb,this);a.w=b;a.D=null;this.f.channel="widget";if(a=G("WIDGET_ID"))this.f.b=a;this.i=[];this.w=!1;this.j={}}
k=Ij.prototype;k.Kb=function(a,b){"addEventListener"==a&&b?(a=b[0],this.j[a]||"onReady"==a||(this.addEventListener(a,Jj(this,a)),this.j[a]=!0)):this.Xa(a,b)};
k.Xa=function(){};
function Jj(a,b){return w(function(a){this.sendMessage(b,a)},a)}
k.addEventListener=function(){};
k.ob=function(){this.w=!0;this.sendMessage("initialDelivery",this.sa());this.sendMessage("onReady");z(this.i,this.Ra,this);this.i=[]};
k.sa=function(){return null};
function Kj(a,b){a.sendMessage("infoDelivery",b)}
k.Ra=function(a){this.w?this.f.sendMessage(a):this.i.push(a)};
k.sendMessage=function(a,b){this.Ra({event:a,info:void 0==b?null:b})};
k.dispose=function(){this.f=null};function Lj(a){Ij.call(this);this.b=a;this.g=[];this.addEventListener("onReady",w(this.Eb,this));this.addEventListener("onVideoProgress",w(this.Rb,this));this.addEventListener("onVolumeChange",w(this.Sb,this));this.addEventListener("onApiChange",w(this.Mb,this));this.addEventListener("onPlaybackQualityChange",w(this.Ob,this));this.addEventListener("onPlaybackRateChange",w(this.Pb,this));this.addEventListener("onStateChange",w(this.Qb,this))}
y(Lj,Ij);k=Lj.prototype;k.Xa=function(a,b){if(this.b[a]){b=b||[];if(0<b.length&&mj(a)){var c;c=b;if(ga(c[0])&&!da(c[0]))c=c[0];else{var d={};switch(a){case "loadVideoById":case "cueVideoById":d=oj.apply(window,c);break;case "loadVideoByUrl":case "cueVideoByUrl":d=nj.apply(window,c);break;case "loadPlaylist":case "cuePlaylist":d=pj.apply(window,c)}c=d}qj(c);b.length=1;b[0]=c}this.b[a].apply(this.b,b);mj(a)&&Kj(this,this.sa())}};
k.Eb=function(){var a=w(this.ob,this);this.f.g=a};
k.addEventListener=function(a,b){this.g.push({eventType:a,listener:b});this.b.addEventListener(a,b)};
k.sa=function(){if(!this.b)return null;var a=this.b.getApiInterface();Ca(a,"getVideoData");for(var b={apiInterface:a},c=0,d=a.length;c<d;c++){var e=a[c],f=e;if(0==f.search("get")||0==f.search("is")){var f=e,g=0;0==f.search("get")?g=3:0==f.search("is")&&(g=2);f=f.charAt(g).toLowerCase()+f.substr(g+1);try{var l=this.b[e]();b[f]=l}catch(h){}}}b.videoData=this.b.getVideoData();b.currentTimeLastUpdated_=x()/1E3;return b};
k.Qb=function(a){a={playerState:a,currentTime:this.b.getCurrentTime(),duration:this.b.getDuration(),videoData:this.b.getVideoData(),videoStartBytes:0,videoBytesTotal:this.b.getVideoBytesTotal(),videoLoadedFraction:this.b.getVideoLoadedFraction(),playbackQuality:this.b.getPlaybackQuality(),availableQualityLevels:this.b.getAvailableQualityLevels(),videoUrl:this.b.getVideoUrl(),playlist:this.b.getPlaylist(),playlistIndex:this.b.getPlaylistIndex(),currentTimeLastUpdated_:x()/1E3,playbackRate:this.b.getPlaybackRate(),
mediaReferenceTime:this.b.getMediaReferenceTime()};this.b.getProgressState&&(a.progressState=this.b.getProgressState());this.b.getStoryboardFormat&&(a.storyboardFormat=this.b.getStoryboardFormat());Kj(this,a)};
k.Ob=function(a){Kj(this,{playbackQuality:a})};
k.Pb=function(a){Kj(this,{playbackRate:a})};
k.Mb=function(){for(var a=this.b.getOptions(),b={namespaces:a},c=0,d=a.length;c<d;c++){var e=a[c],f=this.b.getOptions(e);b[e]={options:f};for(var g=0,l=f.length;g<l;g++){var h=f[g],m=this.b.getOption(e,h);b[e][h]=m}}this.sendMessage("apiInfoDelivery",b)};
k.Sb=function(){Kj(this,{muted:this.b.isMuted(),volume:this.b.getVolume()})};
k.Rb=function(a){a={currentTime:a,videoBytesLoaded:this.b.getVideoBytesLoaded(),videoLoadedFraction:this.b.getVideoLoadedFraction(),currentTimeLastUpdated_:x()/1E3,playbackRate:this.b.getPlaybackRate(),mediaReferenceTime:this.b.getMediaReferenceTime()};this.b.getProgressState&&(a.progressState=this.b.getProgressState());Kj(this,a)};
k.dispose=function(){Lj.B.dispose.call(this);for(var a=0;a<this.g.length;a++){var b=this.g[a];this.b.removeEventListener(b.eventType,b.listener)}this.g=[]};function Mj(){D.call(this);this.b=new E;zb(this,na(Ab,this.b))}
y(Mj,D);Mj.prototype.subscribe=function(a,b,c){return this.f?0:this.b.subscribe(a,b,c)};
Mj.prototype.unsubscribe=function(a,b,c){return this.f?!1:this.b.unsubscribe(a,b,c)};
Mj.prototype.T=function(a){return this.f?!1:this.b.T(a)};
Mj.prototype.N=function(a,b){return this.f?!1:this.b.N.apply(this.b,arguments)};function Nj(a,b,c){Mj.call(this);this.g=a;this.i=b;this.j=c}
y(Nj,Mj);function Bj(a,b,c){if(!a.f){var d=a.g;d.f||a.i!=d.b||(a={id:a.j,command:b},c&&(a.data=c),d.b.postMessage(Gc(a),d.i))}}
Nj.prototype.A=function(){this.i=this.g=null;Nj.B.A.call(this)};function Oj(a,b,c){D.call(this);this.b=a;this.i=c;this.j=M(window,"message",w(this.w,this));this.g=new Nj(this,a,b);zb(this,na(Ab,this.g))}
y(Oj,D);Oj.prototype.w=function(a){var b;if(b=!this.f)if(b=a.origin==this.i)a:{b=this.b;do{var c;b:{c=a.source;do{if(c==b){c=!0;break b}if(c==c.parent)break;c=c.parent}while(null!=c);c=!1}if(c){b=!0;break a}b=b.opener}while(null!=b);b=!1}if(b&&(a=a.data,v(a))){try{a=Ec(a)}catch(d){return}a.command&&(b=this.g,b.f||b.N("command",a.command,a.data))}};
Oj.prototype.A=function(){$d(this.j);this.b=null;Oj.B.A.call(this)};var Pj=document,Y=window;var Qj=!1,Rj="";function Sj(a){a=a.match(/[\d]+/g);if(!a)return"";a.length=3;return a.join(".")}
(function(){if(navigator.plugins&&navigator.plugins.length){var a=navigator.plugins["Shockwave Flash"];if(a&&(Qj=!0,a.description)){Rj=Sj(a.description);return}if(navigator.plugins["Shockwave Flash 2.0"]){Qj=!0;Rj="2.0.0.11";return}}if(navigator.mimeTypes&&navigator.mimeTypes.length&&(a=navigator.mimeTypes["application/x-shockwave-flash"],Qj=!(!a||!a.enabledPlugin))){Rj=Sj(a.enabledPlugin.description);return}try{var b=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");Qj=!0;Rj=Sj(b.GetVariable("$version"));
return}catch(c){}try{b=new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");Qj=!0;Rj="6.0.21";return}catch(c){}try{b=new ActiveXObject("ShockwaveFlash.ShockwaveFlash"),Qj=!0,Rj=Sj(b.GetVariable("$version"))}catch(c){}})();
var Tj=Qj,Uj=Rj;function Vj(a){return(a=a.exec(A))?a[1]:""}
(function(){if(ve)return Vj(/Firefox\/([0-9.]+)/);if(I||rc||pc)return Ac;if(ze)return Vj(/Chrome\/([0-9.]+)/);if(Ae&&!(mc()||B("iPad")||B("iPod")))return Vj(/Version\/([0-9.]+)/);if(we||xe){var a=/Version\/(\S+).*Mobile\/(\S+)/.exec(A);if(a)return a[1]+"."+a[2]}else if(ye)return(a=Vj(/Android\s+([0-9.]+)/))?a:Vj(/Version\/([0-9.]+)/);return""})();var Wj=(new Date).getTime();function Xj(){var a=Qa(Yj);return new N(function(b,c){a.F=function(a){Xc(a)?b(a):c(new Zj("Request failed, status="+a.status,"net.badstatus"))};
a.onError=function(){c(new Zj("Unknown request error","net.unknown"))};
a.R=function(){c(new Zj("Request timed out","net.timeout"))};
bd("//googleads.g.doubleclick.net/pagead/id",a)})}
function Zj(a,b){pa.call(this,a+", errorCode="+b);this.errorCode=b}
y(Zj,pa);Zj.prototype.name="PromiseAjaxError";function ak(a){pa.call(this,a.message);this.b=a instanceof Uf}
y(ak,pa);ak.prototype.name="BiscottiError";function bk(a,b){this.f=a;this.b=b}
bk.prototype.then=function(a,b,c){try{if(p(this.f))return a?Rf(a.call(c,this.f)):Rf(this.f);if(p(this.b)){if(!b)return Sf(this.b);var d=b.call(c,this.b);return!p(d)&&this.b.b?Sf(this.b):Rf(d)}throw Error("Invalid Result_ state");}catch(e){return Sf(e)}};
Mf(bk);var Yj={format:"RAW",method:"GET",timeout:5E3,withCredentials:!0},ck=null;function dk(a){a=a.responseText;if(0!=a.lastIndexOf(")]}'",0))return ek(""),ck=new bk(""),"";a=JSON.parse(a.substr(4)).id;ek(a);ck=new bk(a);fk(18E5,2);return a}
function gk(a,b){b=new ak(b);ek("");ck=new bk(void 0,b);0<a&&fk(12E4,a-1);throw b;}
function fk(a,b){H(function(){var a=w(gk,n,b),a=Xj().then(dk,a);Tf(a,null,t,void 0)},a)}
function ek(a){q("yt.www.ads.biscotti.lastId_",a,void 0)}
;function hk(){}
function ik(){try{var a;try{var b=r("yt.www.ads.biscotti.getId_"),c;if(b)c=b();else{if(!ck){var d=w(gk,n,2);ck=Xj().then(dk,d)}c=ck}a=c}catch(e){a=Sf(e)}a.then(jk,hk);H(ik,18E5)}catch(e){Ib(e)}}
function jk(a){var b;a:{try{b=window.top.location.href}catch(u){b=2;break a}b=null!=b?b==window.document.location.href?0:1:2}b={dt:Wj,flash:Uj||"0",frm:b};b.u_tz=-(new Date).getTimezoneOffset();var c;try{c=Y.history.length}catch(u){c=0}b.u_his=c;b.u_java=!!Y.navigator&&"unknown"!==typeof Y.navigator.javaEnabled&&!!Y.navigator.javaEnabled&&Y.navigator.javaEnabled();Y.screen&&(b.u_h=Y.screen.height,b.u_w=Y.screen.width,b.u_ah=Y.screen.availHeight,b.u_aw=Y.screen.availWidth,b.u_cd=Y.screen.colorDepth);
Y.navigator&&Y.navigator.plugins&&(b.u_nplug=Y.navigator.plugins.length);Y.navigator&&Y.navigator.mimeTypes&&(b.u_nmime=Y.navigator.mimeTypes.length);b.bid=a;b.ca_type=Tj?"flash":"image";if(de("enable_server_side_search_pyv")){var d;a=window;try{d=a.screenX;var e=a.screenY}catch(u){}try{var f=a.outerWidth,g=a.outerHeight}catch(u){}try{var l=a.innerWidth,h=a.innerHeight}catch(u){}d=[a.screenLeft,a.screenTop,d,e,a.screen?a.screen.availWidth:void 0,a.screen?a.screen.availTop:void 0,f,g,l,h];var m;e=
window.top||Y;try{m=e.document&&!e.document.body?new od(-1,-1):Hd(e||window).round()}catch(u){m=new od(-12245933,-12245933)}e=0;window.SVGElement&&document.createElementNS&&(e|=1);oa(b,{bc:e,bih:m.height,biw:m.width,brdim:d.join(),vis:{visible:1,hidden:2,prerender:3,preview:4}[Pj.webkitVisibilityState||Pj.mozVisibilityState||Pj.visibilityState||""]||0,wgl:!!Y.WebGLRenderingContext})}ad("/ad_data_204",{sb:!0,C:b})}
;function kk(){this.b=G("ALT_PREF_COOKIE_NAME","PREF");var a;if(a=Ue.get(""+this.b,void 0)){a=unescape(a).split("&");for(var b=0;b<a.length;b++){var c=a[b].split("="),d=c[0];(c=c[1])&&(Z[d]=c.toString())}}}
ba(kk);var Z=r("yt.prefs.UserPrefs.prefs_")||{};q("yt.prefs.UserPrefs.prefs_",Z,void 0);function lk(a){if(/^f([1-9][0-9]*)$/.test(a))throw"ExpectedRegexMatch: "+a;}
function mk(a){if(!/^\w+$/.test(a))throw"ExpectedRegexMismatch: "+a;}
function nk(a){a=void 0!==Z[a]?Z[a].toString():null;return null!=a&&/^[A-Fa-f0-9]+$/.test(a)?parseInt(a,16):null}
kk.prototype.get=function(a,b){mk(a);lk(a);a=void 0!==Z[a]?Z[a].toString():null;return null!=a?a:b?b:""};
kk.prototype.set=function(a,b){mk(a);lk(a);if(null==b)throw"ExpectedNotNull";Z[a]=b.toString()};
kk.prototype.remove=function(a){mk(a);lk(a);delete Z[a]};
kk.prototype.clear=function(){Z={}};function ok(a){for(var b=0;b<a.length;b++){var c=a[b];"send_follow_on_ping_action"==c.name&&c.data&&c.data.follow_on_url&&(c=c.data.follow_on_url)&&mg(c)}}
;function pk(a){O.call(this,1,arguments);this.pa=a}
y(pk,O);function qk(a,b){O.call(this,2,arguments);this.f=a;this.b=b}
y(qk,O);function rk(a,b,c,d){O.call(this,1,arguments);this.b=b;this.f=c||null;this.itemId=d||null}
y(rk,O);function sk(a,b){O.call(this,1,arguments);this.f=a;this.b=b||null}
y(sk,O);function tk(a){O.call(this,1,arguments)}
y(tk,O);var uk=new P("ypc-core-load",pk),vk=new P("ypc-guide-sync-success",qk),wk=new P("ypc-purchase-success",rk),xk=new P("ypc-subscription-cancel",tk),yk=new P("ypc-subscription-cancel-success",sk),zk=new P("ypc-init-subscription",tk);var Ak=!1,Bk=[],Ck=[];function Dk(a){a.b?Ak?Q(Ch,a):Q(uk,new pk(function(){Q(zk,new tk(a.b))})):Ek(a.f,a.i,a.g,a.source)}
function Fk(a){a.b?Ak?Q(Hh,a):Q(uk,new pk(function(){Q(xk,new tk(a.b))})):Gk(a.f,a.subscriptionId,a.i,a.g,a.source)}
function Hk(a){Ik(Ea(a.b))}
function Jk(a){Kk(Ea(a.b))}
function Lk(a){Mk(a.g,a.f,a.b)}
function Nk(a){var b=a.itemId,c=a.b.subscriptionId;b&&c&&Q(Bh,new uh(b,c,a.b.channelInfo))}
function Ok(a){var b=a.b;Ka(a.f,function(a,d){Q(Bh,new uh(d,a,b[d]))})}
function Pk(a){Q(Gh,new S(a.f.itemId));a.b&&a.b.length&&(Qk(a.b,Gh),Qk(a.b,Ih))}
function Ek(a,b,c,d){var e=new S(a);Q(zh,e);var f={};f.c=a;c&&(f.eurl=c);d&&(f.source=d);c={};(d=G("PLAYBACK_ID"))&&(c.plid=d);(d=G("EVENT_ID"))&&(c.ei=d);b&&Rk(b,c);bd("/subscription_ajax?action_create_subscription_to_channel=1",{method:"POST",wa:f,C:c,F:function(b,c){b=c.response;Q(Bh,new uh(a,b.id,b.channel_info));b.show_feed_privacy_dialog&&Tb("SHOW-FEED-PRIVACY-SUBSCRIBE-DIALOG",a);b.actions&&ok(b.actions)},
va:function(){Q(Ah,e)}})}
function Gk(a,b,c,d,e){var f=new S(a);Q(Eh,f);var g={};d&&(g.eurl=d);e&&(g.source=e);d={};d.c=a;d.s=b;(a=G("PLAYBACK_ID"))&&(d.plid=a);(a=G("EVENT_ID"))&&(d.ei=a);c&&Rk(c,d);bd("/subscription_ajax?action_remove_subscriptions=1",{method:"POST",wa:g,C:d,F:function(a,b){a=b.response;Q(Gh,f);a.actions&&ok(a.actions)},
va:function(){Q(Fh,f)}})}
function Mk(a,b,c){if(a){var d={};d.channel_id=a;switch(b){case "receive-all-updates":d.receive_all_updates=!0;break;case "receive-no-updates":d.receive_no_updates=!0;d.receive_post_updates=!1;break;case "receive-highlight-updates":d.receive_all_updates=!1;d.receive_no_updates=!1;break;default:return}null===c||d.receive_no_updates||(d.receive_post_updates=c);var e=new sh(a,b,c);bd("/subscription_ajax?action_update_subscription_preferences=1",{method:"POST",C:d,onError:function(){Q(Mh,e)},
F:function(){Q(Lh,e)}})}}
function Ik(a){if(a.length){var b=Ga(a,0,40);Q("subscription-batch-subscribe-loading");Qk(b,zh);var c={};c.a=b.join(",");var d=function(){Q("subscription-batch-subscribe-loaded");Qk(b,Ah)};
bd("/subscription_ajax?action_create_subscription_to_all=1",{method:"POST",C:c,F:function(c,f){d();c=f.response;f=c.id;if(da(f)&&f.length==b.length){var e=c.channel_info_map;z(f,function(a,c){c=b[c];Q(Bh,new uh(c,a,e[c]))});
a.length?Ik(a):Q("subscription-batch-subscribe-finished")}},
onError:function(){d();Q("subscription-batch-subscribe-failure")}})}}
function Kk(a){if(a.length){var b=Ga(a,0,40);Q("subscription-batch-unsubscribe-loading");Qk(b,Eh);var c={};c.c=b.join(",");var d=function(){Q("subscription-batch-unsubscribe-loaded");Qk(b,Fh)};
bd("/subscription_ajax?action_remove_subscriptions=1",{method:"POST",C:c,F:function(){d();Qk(b,Gh);a.length&&Kk(a)},
onError:function(){d()}})}}
function Qk(a,b){z(a,function(a){Q(b,new S(a))})}
function Rk(a,b){a=Uc(a);oa(b,a)}
;var Sk=null,Tk=null,Uk=null,Vk={};function Wk(a){var b=a.id;a=a.ve_type;var c=Ce++;a=new Be(void 0,a,c);Vk[b]=a;b=Re();c=Qe();b&&c&&Le(b,c,a)}
function Xk(a){var b=a.csn;a=a.root_ve_type;if(b&&a&&(F("client-screen-nonce",b),F("ROOT_VE_TYPE",a),a=Qe()))for(var c in Vk)Le(b,a,Vk[c])}
function Yk(a){Vk[a.id]=new Be(a.tracking_params)}
function Zk(a){var b=Re();a=Vk[a.id];b&&a&&Me(Ne(),{click:{csn:b,visualElement:De(a)}},void 0)}
function $k(a){a=a.ids;var b=Re();if(b){for(var c=[],d=0;d<a.length;d++){var e=Vk[a[d]];e&&c.push(e)}c.length&&Oe(b,c)}}
function al(){var a=Sk.startInteractionLogging;a&&a()}
;q("yt.setConfig",F,void 0);q("yt.setMsg",function(a){Fb(Eb,arguments)},void 0);
q("yt.logging.errors.log",function(a,b,c,d,e){c={name:c||G("INNERTUBE_CONTEXT_CLIENT_NAME",1),version:d||G("INNERTUBE_CONTEXT_CLIENT_VERSION",void 0)};e=window&&window.yterr||e||!1;if(a&&e&&!(5<=id)){e=a.stacktrace;d=a.columnNumber;var f=r("window.location.href");if(v(a))a={message:a,name:"Unknown error",lineNumber:"Not available",fileName:f,stack:"Not available"};else{var g,l,h=!1;try{g=a.lineNumber||a.hc||"Not available"}catch(Ba){g="Not available",h=!0}try{l=a.fileName||a.filename||a.sourceURL||
n.$googDebugFname||f}catch(Ba){l="Not available",h=!0}a=!h&&a.lineNumber&&a.fileName&&a.stack&&a.message&&a.name?a:{message:a.message||"Not available",name:a.name||"UnknownError",lineNumber:g,fileName:l,stack:a.stack||"Not available"}}e=e||a.stack;g=a.lineNumber.toString();isNaN(g)||isNaN(d)||(g=g+":"+d);if(!(hd[a.message]||0<=e.indexOf("/YouTubeCenter.js")||0<=e.indexOf("/mytube.js"))){b={wa:{a:"logerror",t:"jserror",type:a.name,msg:a.message.substr(0,1E3),line:g,level:b||"ERROR"},C:{url:G("PAGE_NAME",
window.location.href),file:a.fileName},method:"POST"};e&&(b.C.stack=e);for(var m in c)b.C["client."+m]=c[m];if(m=G("LATEST_ECATCHER_SERVICE_TRACKING_PARAMS",void 0))for(var u in m)b.C[u]=m[u];bd("/error_204",b);hd[a.message]=!0;id++}}},void 0);
q("writeEmbed",function(){var a=G("PLAYER_CONFIG",void 0);"1"!=a.privembed&&ik();"gvn-experiment"==a.args.ps&&(document.body.style.backgroundColor="transparent");var b=document.referrer,c=G("POST_MESSAGE_ORIGIN");window!=window.top&&b&&b!=document.URL&&(a.args.loaderUrl=b);G("LIGHTWEIGHT_AUTOPLAY")&&(a.args.autoplay="1");a.args.autoplay&&qj(a.args);Sk=b=fh("player",a);b.addEventListener("onScreenChanged",Xk);b.addEventListener("onLogClientVeCreated",Wk);b.addEventListener("onLogServerVeCreated",Yk);
b.addEventListener("onLogVeClicked",Zk);b.addEventListener("onLogVesShown",$k);b.addEventListener("onReady",al);var d=G("POST_MESSAGE_ID","player");G("ENABLE_JS_API")?Uk=new Lj(b):G("ENABLE_POST_API")&&v(d)&&v(c)&&(Tk=new Oj(window.parent,d,c),Uk=new Dj(b,Tk.g));G("ENABLE_CAST_API")||(a.args.enablecastapi="0");G("BG_P")&&(G("BG_I")||G("BG_IU"))&&ec();md();rj=b;rj.addEventListener("SUBSCRIBE",uj);rj.addEventListener("UNSUBSCRIBE",xj);sj.push(R(Bh,yj),R(Gh,zj))},void 0);
q("yt.www.watch.ads.restrictioncookie.spr",function(a){(a+="mac_204?action_fcts=1")&&mg(a);return!0},void 0);
var bl=Gb(function(){Cg("ol");Ak=!0;Ck.push(R(yh,Dk),R(Dh,Fk));Ak||Ck.push(R(Ch,Dk),R(Hh,Fk),R(wh,Hk),R(xh,Jk),R(Kh,Lk),R(wk,Nk),R(yk,Pk),R(vk,Ok));var a=kk.getInstance(),b=1<window.devicePixelRatio;if(!!((nk("f"+(Math.floor(119/31)+1))||0)&67108864)!=b){var c="f"+(Math.floor(119/31)+1),d=nk(c)||0,d=b?d|67108864:d&-67108865;0==d?delete Z[c]:Z[c]=d.toString(16).toString();var a=a.b,b=[],e;for(e in Z)b.push(e+"="+escape(Z[e]));Ue.set(""+a,b.join("&"),63072E3,"/","youtube.com")}}),cl=Gb(function(){var a=
Sk;
a&&a.sendAbandonmentPing&&a.sendAbandonmentPing();G("PL_ATT")&&(dc=null);for(var a=0,b=kd.length;a<b;a++){var c=kd[a];if(!isNaN(c)){var d=r("yt.scheduler.instance.cancelJob");d?d(c):Hb(c)}}kd.length=0;a=$b("//static.doubleclick.net/instream/ad_status.js");if(b=document.getElementById(a))Vb(a),b.parentNode.removeChild(b);ld=!1;F("DCLKSTAT",0);Sb(Bk);Bk.length=0;ug(Ck);Ck.length=0;Ak=!1;rj&&(rj.removeEventListener("SUBSCRIBE",vj),rj.removeEventListener("UNSUBSCRIBE",xj));rj=null;ug(sj);sj.length=0;
Bb(Uk,Tk);if(a=Sk)a.removeEventListener("onScreenChanged",Xk),a.removeEventListener("onLogClientVeCreated",Wk),a.removeEventListener("onLogServerVeCreated",Yk),a.removeEventListener("onLogVeClicked",Zk),a.removeEventListener("onLogVesShown",$k),a.removeEventListener("onReady",al),a.destroy();Vk={}});
window.addEventListener?(window.addEventListener("load",bl),window.addEventListener("unload",cl)):window.attachEvent&&(window.attachEvent("onload",bl),window.attachEvent("onunload",cl));var dl=ii.getInstance(),el=U(dl);el in ni||(dl.register(),dl.ja.push(Qb("yt-uix-init-"+el,dl.init,dl)),dl.ja.push(Qb("yt-uix-dispose-"+el,dl.dispose,dl)),ni[el]=dl);})();
