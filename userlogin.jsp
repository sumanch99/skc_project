<!DOCTYPE html>
<html lang="en">
<head>
<title>SCN Stock || User Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta name="keywords" content="Stock Industry Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!--// bootstrap-css -->
<!-- css -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!--// css -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!-- font -->
<link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<!-- //font -->
<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">
		function showPassword()
		{
			var x=document.getElementById("password");
			if(x.type=="text")
			{
				x.type="password";
				return true;
			}
			x.type="text";
			return true;
		}
	</script>
</head>
<body>
	<!-- w3-banner -->
	<div class="w3-banner-1 jarallax">
			<div class="w3layouts-header-top">
		
			<div class="head">
				<div class="container">
					<div class="navbar-top">
							<!-- Brand and toggle get grouped for better mobile display -->
							<div class="navbar-header">
							  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							  </button>
								 <div class="navbar-brand logo ">
									<h1><a href="index">Goramal Balmokand</a></h1>
								</div>

							</div>

							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							 <ul class="nav navbar-nav link-effect-4">
								<li><a href="index">Home</a></li>
								<li><a href="gallery">Gallery</a></li>
								<li class="active first-list"><a href="userlogin" >Login</a></li> 
								<li><a href="about">About</a></li>
							  </ul>
							</div><!-- /.navbar-collapse -->
						</div>
				</div>
			</div>
	</div>
</div>


	<!--header-->
	<!-- //w3-banner -->
	<!-- contact -->
	<%
	int flag=0; 
	if(session.getAttribute("flag")!=null)
	{
		flag=(int) session.getAttribute("flag");
		if(flag==1){
		String uname=session.getAttribute("uname").toString();
		String pwd=session.getAttribute("pwd").toString();
		%>
		<div class="contact agileits">
		<div class="container"> 
			<h2 class="agileits-title">User Login</h2></div></div>
<form action="dashboard" method="post">
		<form action="dashboard" method="post">
<div class="container" style="max-width: 60%;"> 
	<div class="form-row">
    <div class="form-group col-md-12">
      <label for="username">Username</label>
      <input type="text" class="form-control" placeholder="username" name="username" id="username" value="<%=uname%>"required>
    </div>
    <div class="form-group col-md-12">
      <label for="password">Password</label>
      <input type="password" class="form-control" name="password" placeholder="password" value="<%=pwd%>" id="password" required>
      <input type="checkbox" onclick="showPassword()"> Show Password
    </div>
  </div>
</div>
<center>
  &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-secondary">LogIn</button>
  <button type="reset" class="btn btn-danger">Clear</button>
</center>
</form>
<%}
else{
%>

	<div class="contact agileits">
		<div class="container"> 
			<h2 class="agileits-title">User Login</h2></div></div>
<form action="dashboard" method="post">
<div class="container" style="max-width: 60%;"> 
	<div class="form-row">
    <div class="form-group col-md-12">
      <label for="username">Username</label>
      <input type="text" class="form-control" placeholder="username" name="username" id="username" required>
    </div>
    <div class="form-group col-md-12">
      <label for="password">Password</label>
      <input type="password" class="form-control" name="password" placeholder="password" id="password" required>
      <input type="checkbox" onclick="showPassword()"> Show Password
    </div>
  </div>
</div>
<center>
  &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-secondary">LogIn</button>
  <button type="reset" class="btn btn-danger">Clear</button>
</center>
</form>
<%
}
}
else{
%>
<div class="contact agileits">
		<div class="container"> 
			<h2 class="agileits-title">User Login</h2></div></div>
<form action="dashboard" method="post">
<form action="dashboard" method="post">
<div class="container" style="max-width: 60%;"> 
	<div class="form-row">
    <div class="form-group col-md-12">
      <label for="username">Username</label>
      <input type="text" class="form-control" placeholder="username" name="username" id="username" required>
    </div>
    <div class="form-group col-md-12">
      <label for="password">Password</label>
      <input type="password" class="form-control" name="password" placeholder="password" id="password" required>
      <input type="checkbox" onclick="showPassword()"> Show Password
    </div>
  </div>
</div>
<center>
  &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-secondary">LogIn</button>
  <button type="reset" class="btn btn-danger">Clear</button>
</center>
</form>
<%
}
%>
<br><br>
<center>
				Not registered?<a href="signup" style="color:blue;text-decoration: none;"><i> click here to sign up</i></a>
	</center><br><br><br><br>
	<!-- //contact -->  
	<!-- map 
	<div class="map w3layouts">  
		<iframe src="https://www.google.com/maps/place//data=!4m2!3m1!1s0x3a0277b0a5ce85b3:0x6405ec053fc0d096?utm_source=mstt_1"></iframe>
	</div>
	//map -->  

	<!-- footer -->
		<div class="agileits-w3layouts-footer">
			<div class="container">
				<div class="col-md-4 w3-agile-grid">
					<div class="w3-address-grid">
							<div class="w3-address-left">
								<i class="fa fa-phone" aria-hidden="true"></i>
							</div>
							<div class="w3-address-right">
								<h6>Phone Number</h6>
								<p>Suman Chakraborty: (+91)8918316046</p>
								<p>Sinjon Nath: (+91)8013896502</p>
							</div>
							<div class="clearfix"> </div>
						</div>
					
				</div>
				<div class="col-md-4 w3-agile-grid">
					  <div class="w3-address-grid">
							<div class="w3-address-left">
								<i class="fa fa-envelope" aria-hidden="true"></i>
							</div>
							<div class="w3-address-right">
								<h6>Email Address</h6>
								<p><a href="mailto:sch.suman@gmail.com"> sch.suman@gmail.com</a>
								<span><a href="mailto:sinjonnath@gmail.com">sinjonnath@gmail.com</a></span>
								</p>
							</div>
							<div class="clearfix"> </div>
						</div>
				</div>
				<div class="col-md-4 w3-agile-grid">
					<div class="w3-address">
						<div class="w3-address-grid">
							<div class="w3-address-left">
								<i class="fa fa-map-marker" aria-hidden="true"></i>
							</div>
							<div class="w3-address-right">
								<h6>Location</h6>
								<p>Suman Chakraborty<br>Manihar Co-Opt. Housing Society Limited-7/6, Calcutta Greens Phase 2,Survey Park,Kolkata-700075 
								<span>Sinjon Nath<br>8/1,M.M. Dutta Road,New Barrackpur,Kolkata-700131 </span>
								</p>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<div class="copyright">
			<div class="container">
				<p>&copy; 2020 Stock Industry. All rights reserved | Design by <a href="cv\Suman_Chakraborty_Resume.pdf" target="_blank">SUMAN CHAKRABORTY</a> & <a href="cv\sinjon_resume.pdf" target="_blank">SINJON NATH</a></p>
			</div>
		</div>
	<!-- //footer -->
	<!-- ResponsiveTabs -->
	<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$('#horizontalTab').easyResponsiveTabs({
				type: 'default', //Types: default, vertical, accordion           
				width: 'auto', //auto or any width like 600px
				fit: true   // 100% fit in a container
			});
		});
	</script>
	<!-- //ResponsiveTabs --> 

	<script src="js/jarallax.js"></script>
	<script src="js/SmoothScroll.min.js"></script>
	<script type="text/javascript">
		/* init Jarallax */
		$('.jarallax').jarallax({
			speed: 0.5,
			imgWidth: 1366,
			imgHeight: 768
		})
	</script>
</body>	
</html>