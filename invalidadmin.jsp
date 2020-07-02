<!DOCTYPE html>
<html>
<head>
	<title>Invalid Admin credentials</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Oops!", "Invalid username or password!", "error")
		.then(() => {
		  location.href="adminlogin";
		});
    </script>
</body>
</html>