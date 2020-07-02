<!DOCTYPE html>
<html>
<head>
	<title>Success</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Done!", "User is now registered!", "success")
    	.then(() => {
		  location.href="admindashboard";
		});
    </script>

</body>
</html>