var jsapi_ticket = '';
var back_nonceStr ='';
var back_timestamp='';
var back_signature='';
var url =  window.location.href;
var share_url = "http://www.baidu.com";
$.ajax({
	url:"appFirstPage/getJsApiTicket",
	type:"post",
	async:false,
	data:{"url":url},
	success:function(rs){
		jsapi_ticket = rs.jsapi_ticket;
		back_nonceStr=rs.nonceStr;
		back_timestamp=rs.timestamp;
		back_signature=rs.qm;
	}
});
var appId = "wxce12dee674432cb7";
console.log("jsapi_ticket:"+jsapi_ticket);
console.log("back_nonceStr:"+back_nonceStr);
console.log("back_timestamp:"+back_timestamp);
console.log("signature:"+back_signature);
console.log("url:"+url);//encodeURIComponent();
//alert(location.href.split('#')[0]);

	wx.config({
		debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId : appId, // 必填，公众号的唯一标识
		timestamp : back_timestamp, // 必填，生成签名的时间戳
		nonceStr : back_nonceStr, // 必填，生成签名的随机串
		signature : back_signature,// 必填，签名，见附录1
		jsApiList : ['checkJsApi','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ']
	});
	
	
	wx.ready(function() {
				// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
				wx.checkJsApi({
					jsApiList : ['checkJsApi','onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
					success : function(res) {
						// 以键值对的形式返回，可用的api值true，不可用为false
						// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
					}
				});
				wx.onMenuShareTimeline({// 朋友圈
					title : '23度轻生活', // 分享标题
					link : share_url, // 分享链接
					imgUrl : 'http://qq276385266.6655.la/estraight/static/images/2.png', // 分享图标
					success : function() {
						// 用户确认分享后执行的回调函数
					},
					cancel : function() {
						// 用户取消分享后执行的回调函数
					}
				});
				
				wx.onMenuShareAppMessage({// 微信好友
					title : '23度轻生活', // 分享标题
					desc : '来我的店看看，不看我就丢你一个螺母', // 分享描述
					link : share_url, // 分享链接
					imgUrl : 'http://qq276385266.6655.la/estraight/static/images/2.png', // 分享图标
					type : 'link', // 分享类型,music、video或link，不填默认为link
					dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
					success : function() {
						// 用户确认分享后执行的回调函数
					},
					cancel : function() {
						// 用户取消分享后执行的回调函数
					}
				});
				
				wx.onMenuShareQQ({
					title : '23度轻生活', // 分享标题
					desc : '来我的店看看吧，大兄弟', // 分享描述
					link : share_url, // 分享链接
					imgUrl : 'http://qq276385266.6655.la/estraight/static/images/2.png', // 分享图标
					success : function() {
						// 用户确认分享后执行的回调函数
					},
					cancel : function() {
						// 用户取消分享后执行的回调函数
					}
				});
        
        });
	wx.error(function(res) {
	 alert(JSON.stringify(res));
	});