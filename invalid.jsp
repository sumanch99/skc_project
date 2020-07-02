<!DOCTYPE html>
<html>
<head>
	<title>Invalid credentials</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Oops!", "Invalid username or password!", "error")
		.then(() => {
		  location.href="userlogin";
		});
    </script>
</body>
</html>