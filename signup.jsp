<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html lang="en">

<head>
	<title>SCN Stock || SignUp</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">
	<meta name="keywords" content="Stock Industry Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
	<script
		type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
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
	<link href="//fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i"
		rel="stylesheet">
	<!-- //font -->
	<script src="js/jquery-2.2.3.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script type="text/javascript">
		function validate()
		{
			
			var password=document.getElementById("password").value;
			var password2=document.getElementById("password2").value;
			
			if(password!=password2)
			{
				swal("Oops!", "Passwords didnot match!", "error");
				document.getElementById("password2").focus();
				return false;
			}

			return true;

		}
		
	</script>
</head>

<body>
	<!-- w3-banner -->
	<div class="w3-banner-1 jarallax">
		<div class="w3layouts-header-top">
		<!--
			<div class="w3layouts-header-top">
				<div class="container">
					<div class="w3-header-top-grids">
						<div class="w3-header-top-left">
							<p>Goralmal Balmokand</p>
						</div>
						<div class="w3-header-top-right">
							<div class="agileinfo-social-grids">
								<ul>
									<li><a href="#"><i class="fa fa-facebook"></i></a></li>
									<li><a href="#"><i class="fa fa-twitter"></i></a></li>
									<li><a href="#"><i class="fa fa-rss"></i></a></li>
									<li><a href="#"><i class="fa fa-vk"></i></a></li>
								</ul>
							</div>
							<div class="clearfix"> </div>
						</div>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>-->
		<div class="head">
			<div class="container">
				<div class="navbar-top">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<div class="navbar-brand logo ">
							<h1><a href="index">Imports & Exports</a></h1>
						</div>

					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav link-effect-4">
							<li><a href="index">Home</a></li>
							<li><a href="gallery">Gallery</a></li>
							<li class="active first-list"><a href="signup">Sign Up</a></li>
							<li><a href="about">About</a></li>
						</ul>
					</div><!-- /.navbar-collapse -->
				</div>
			</div>
		</div>
	</div>


	</div>
	<!-- //w3-banner -->
	<!-- contact -->
	<div class="contact agileits">
		<div class="container">
			<h2 class="agileits-title">User Sign UP</h2>
		</div>
	</div>
<%
	int fl=0; 
	if(session.getAttribute("fl")!=null)
	{
		fl=(int) session.getAttribute("fl");
		if(fl==1){
		String name=session.getAttribute("name").toString();
		String uname=session.getAttribute("uname").toString();
		String email=session.getAttribute("email").toString();
		String number=session.getAttribute("number").toString();
		String password=session.getAttribute("password").toString();
		%>
<form action="userentry" method="post" onsubmit="return validate();">
	<div class="container"> 
<div class="form-row">
    <div class="form-group col-md-6">
      <label for="Name">Name</label>
      <input type="text" class="form-control" placeholder="Name" id="name" value="<%=name%>" name="name" required>
    </div>
    <div class="form-group col-md-6">
      <label for="Username">Username</label>
      <input type="text" class="form-control" placeholder="Username" id="username" value="<%=uname%>" name="username" required>
    </div>
</div>
<div class="form-row">
    <div class="form-group col-md-6">
      <label name = "Email">Email</label>
      <input type="email" class="form-control" placeholder="Email" id="email" value="<%=email%>" name="email" required>
    </div>
    <div class="form-group col-md-6">
      <label for="Number">Number</label>
      <input type="text" class="form-control" placeholder="Number" id="number" value="<%=number%>" name="number" required>
    </div>
  </div>
  
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="Password">Password</label>
      <input type="password" class="form-control" placeholder="Password" value="<%=password%>" id="password"name="password" required>
    </div>
    <div class="form-group col-md-6">
      <label for="Password">Confirm Password</label>
      <input type="password" class="form-control" placeholder="Confirm Password" value="<%=password%>" id="password2" name="password2" required>
    </div>
</div>
</div>
    <center>
  &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-secondary">SignUp</button>
  <button type="reset" class="btn btn-danger">Clear</button>
</center>
</form>
<%}
else{
%>
<form action="userentry" method="post" onsubmit="return validate();">
	<div class="container"> 
<div class="form-row">
    <div class="form-group col-md-6">
      <label for="Name">Name</label>
      <input type="text" class="form-control" placeholder="Name" id="name" name="name" required>
    </div>
    <div class="form-group col-md-6">
      <label for="Username">Username</label>
      <input type="text" class="form-control" placeholder="Username" id="username" name="username" required>
    </div>
</div>
<div class="form-row">
    <div class="form-group col-md-6">
      <label name = "Email">Email</label>
      <input type="email" class="form-control" placeholder="Email" id="email"  name="email" required>
    </div>
    <div class="form-group col-md-6">
      <label for="Number">Number</label>
      <input type="text" class="form-control" placeholder="Number" id="number" name="number" required>
    </div>
  </div>
  
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="Password">Password</label>
      <input type="password" class="form-control" placeholder="Password" id="password"name="password" required>
    </div>
    <div class="form-group col-md-6">
      <label for="Password">Confirm Password</label>
      <input type="password" class="form-control" placeholder="Confirm Password" id="password2" name="password2" required>
    </div>
</div>
</div>
    <center>
  &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-secondary">SignUp</button>
  <button type="reset" class="btn btn-danger">Clear</button>
</center>
</form>
<%
}
}
else{
%>
<form action="userentry" method="post" onsubmit="return validate();">
	<div class="container"> 
<div class="form-row">
    <div class="form-group col-md-6">
      <label for="Name">Name</label>
      <input type="text" class="form-control" placeholder="Name" id="name" name="name" required>
    </div>
    <div class="form-group col-md-6">
      <label for="Username">Username</label>
      <input type="text" class="form-control" placeholder="Username" id="username" name="username" required>
    </div>
</div>
<div class="form-row">
    <div class="form-group col-md-6">
      <label name = "Email">Email</label>
      <input type="email" class="form-control" placeholder="Email" id="email"  name="email" required>
    </div>
    <div class="form-group col-md-6">
      <label for="Number">Number</label>
      <input type="text" class="form-control" placeholder="Number" id="number" name="number" required>
    </div>
  </div>
  
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="Password">Password</label>
      <input type="password" class="form-control" placeholder="Password" id="password"name="password" required>
    </div>
    <div class="form-group col-md-6">
      <label for="Password">Confirm Password</label>
      <input type="password" class="form-control" placeholder="Confirm Password" id="password2" name="password2" required>
    </div>
</div>
</div>
    <center>
  &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-secondary">SignUp</button>
  <button type="reset" class="btn btn-danger">Clear</button>
</center>
</form>
<%
}
%>

		<br><br>
		<center>
		Already registered?<a href="userlogin" style="color:blue;text-decoration: none;"><i> click here to Log
				in</i></a>

	</center><br><br>
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
							<p>Suman Chakraborty<br>Manihar Co-Opt. Housing Society Limited-7/6, Calcutta Greens Phase
								2,Survey Park,Kolkata-700075
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