<%@ page language="java" import="java.util.*,com.lyc.util.*"  pageEncoding="utf-8"%>

<!doctype html>
<html>
		<head>		
			<!--申明编码集 -->	
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title>人脸识别</title>
			<meta name="Keywords" content="关键字">
			<meta name="description" content="">
			
			<!--css,js  可修饰div-->
			<style type="text/CSS">
				*{matgin:0; padding:0;}
				body{background:#E6E6E6;font-size:12px;font-family:"微软雅黑";color:#666;}
				/*以。开头 的css样式为类样式*/
				h1{border:1px solid white;text-align:center;padding:20px 0;}
				/*face start*/
				.face{width:1024px;height:400px;border:1px solid white;text-align:center; margin:0 auto; }
				/*face end*/
				
				/*btn start*/
				.btn{width:200px;height:36px;display:block;border:1px solid #00b4ff;
					text-align:center;line-height:36px;text-decoration:none;margin:20px auto;
					color:#00b4ff;font-size:16px;border-radius:18px;}
				.btn:hover{background:#00b4ff;color:#fff;}
				/*btn end*/
				
				/*photo start*/
				.photo{width:800px;height:460px;border:1px solid white;margin:0 auto; }
				.photo .p_pic{width:505px;height:460px;border:1px solid white;float:left;}
				.photo .p_info{width:290px;height:460px;background #fff;float:left;}
				.photo .p_info h2{text-align:center;margin:50px 0;}
				.photo .p_info p{line-height:38px;font-size:16px;padding-left:28px;}
				/*photo end*/
			</style>
		</head>
		
		<body>
			<h1> 人脸识别 </h1>
			<!--div：层 -->
			<!--face start-->
			<form action="upload.jsp" method="post" enctype="multipart/form-data" id="arryForm">
				<div class="face"  >	
					<!---src路径 alt描述 width height-->
					<img src="images/facial.jpg" alt="人脸识别" width="1024" height="400"  />
				</div>
				<!--end face-->
				
				<!--btn start-->
				<a href="#" class="btn" onclick="openBrows();">上传照片</a>
				<!-- 下面的是用来调试的，看到传入文件的名字窗口  不显示出来-->
				<input type="file" id="file" name="file" onchange="saveFile();" style="display:none"/>
				<input type="text" id="filename" style="display:none"/>
				<!--btn end-->
				
				
				<!--photo start-->
				<div class="photo">						
						<div class="p_pic">
							<img src="${empty fileName ? '':fileName }"	alt="上传的图片" width="505" height="460" id ="path"/>
						</div>
						
						<div class="p_info">
							<h2> 扫描结果：</h2>
						  <p id="p_message">
						        年龄：<br />
						        性别：<br />
						        种族：<br />
						        微笑：
						  </p>					  
						</div>
						
				</div> 
			</form>
			<!--photo end-->
			<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
			<script type="text/javascript">
				//点击按钮浏览本地图片
				function openBrows(){
					var ie=navigator.appName == "Microsoft Internet Explorer" ? true:false;
					//alert(ie);
					if(ie){
						document.getElementById("file").click();
						document.getElementById("filename").value = document.getElementById("file").value();
					} else {
						var a=document.createEvent("MouseEvents");
						a.initEvent("click",true,true);
						document.getElementById("file").dispatchEvent(a);
					}
				}
				
				//提交表单
				function saveFile(){
					document.getElementById("arryForm").submit();
				}
				$(function(){
					faceDo();
				});
				function faceDo(){
					var msg = $("#path").attr("src");
					$.ajax({
						type:"post",
						url:"faceData.jsp",
						data:{"path":msg},
						success:function(data){
							$("#p_message").prepend(data);
						}
					});
				}
			</script>
		</body>
</html>
