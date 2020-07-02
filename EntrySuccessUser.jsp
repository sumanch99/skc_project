<!DOCTYPE html>
<html>
<head>
	<title>Success</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Done!", "Login access is requested!", "success")
    	.then(() => {
		  location.href="userlogin";
		});
    </script>

</body>
</html>