<%@page import="java.sql.*,java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SCN Stock || Quality Master</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta name="keywords" content="SCN Stock Quality Master" />
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
									<h1><a href="dashboard">Imports & Exports</a></h1>
								</div>

							</div>

							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							 <ul class="nav navbar-nav link-effect-4" style="display:inline-block;">
							 	<li><a href="dashboard" >Dashboard</a></li> 
							 	<li class="active first-list"><a href="QualityMaster">Quality Master</a></li>
							 	<li><a href="logout" >LogOut</a></li> 
							  </ul>
							</div><!-- /.navbar-collapse -->
						</div>
				</div>
			</div>
		</div>
	</div>
<% 
     ArrayList c_codes = new ArrayList();

     if (request.getSession().getAttribute("c_codes") != null) {
         c_codes = (ArrayList ) request.getSession().getAttribute("c_codes");
     }
     Iterator iter = c_codes.iterator();

     Set <String> engaged_qualities = new HashSet<>();
     if (request.getSession().getAttribute("engaged_qualities") != null) {
         engaged_qualities = (HashSet) request.getSession().getAttribute("engaged_qualities");
     }
%>
		<!--End of Header -->
		<div class="container">
		<br>
		<h3 class="card-title text-center">Quality Master</h3>
		<!--<div class="jumbotron" style="background-color:#839192 ;">-->
		

<form action="qualitymasterentry" method="post">
  <div class="form-row">
    <div class="form-group col-md-4">
      <label name = "Quality">Quality</label>
      <input type="text" class="form-control" placeholder="Enter Quality" name="q_name" required>
    </div>
    <div class="form-group col-md-4">
      <label name = "Consigner ID">Consigner Code</label>
      <select style="width: 350px;height:33px;margin: 0px;border-color:#8080AA;" name="c_code" required="">
      	<option></option>
      <%  while(iter.hasNext())
      		{ %>
            <option>
            	<%String ch =iter.next().toString();%>
            	<%=ch.substring(1,ch.length()-1)%>	
            </option>
        	<% } %>
	  </select>
  </div>
   <div class="form-group col-md-4">
      <label for="Hsn Code">HSN Code</label>
      <input type="text" class="form-control" placeholder="Enter HSN Code" name="h_code" required>
    </div>
</div>
  <div class="form-row">
    <div class="form-group col-md-4">
      <label for="Quality Description">Quality Description</label>
      <input type="text" class="form-control" placeholder="Enter Quality Description" name="q_des" required="">
    </div>
        <div class="form-group col-md-4">
      <label for="CGst Rate">CGST Rate (%)</label>
      <input type="text" class="form-control" placeholder="Enter CGST Rate." name="cgst" required="">
    </div>
    <div class="form-group col-md-4">
      <label for="SGst Rate">SGST Rate (%)</label>
      <input type="text" class="form-control" placeholder="Enter SGST Rate" name="sgst" required="">
    </div>
    </div>
   <center>
  &nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-secondary">SUBMIT</button>
  <button type="reset" class="btn btn-danger">Clear</button>
</center>
</form>
</div>
<br><br>
<% 
     List<List> qualities = new ArrayList<List>();

     if (request.getSession().getAttribute("qualities") != null) {
         qualities = (ArrayList ) request.getSession().getAttribute("qualities");
     }
%>
<div class="container"> 
<div class="table-responsive">
<table class = "table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Quality</th>
      <th scope="col">Consigner Code</th>
      <th scope="col">HSN Code</th>
      <th scope="col">Quality Description</th>
      <th scope="col">CGST Rate(%)</th>
      <th scope="col">SGST Rate(%)</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <script type="text/javascript">
	function isSure()
	{
		if (confirm("Are you sure you want to delete it?")) 
		{
			return true;
		}
		return false;
	}
</script>
<tbody>
<%
	for(List<String> row:qualities)
	{%>
		<tr>
		<tr>
			<td><font color="black"><%=row.get(1)%></font></td>
			<td><font color="black"><%=row.get(2)%></font></td>
			<td><font color="black"><%=row.get(3)%></font></td>
			<td><font color="black"><%=row.get(4)%></font></td>
			<td><font color="black"><%=row.get(5)%></font></td>
			<td><font color="black"><%=row.get(6)%></font></td>
			
		<td><a href= "UpdateQuality?q_id=<%=row.get(0)%>"><button type="button" class="update">Update</button></a>
			&nbsp;
		<%if(!engaged_qualities.contains(row.get(0)))
		{%>
			<a href= "deletequality?q_id=<%=row.get(0)%>"><button type="button" class="update" onclick="return isSure();">Delete</button></a>
		<%}%>
		</td>
		</tr>
	<%}
%>
</tbody>
</table>
</div>
</div>
<br><br>
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