<!DOCTYPE html>
<html>
<head>
	<title>Failed</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Oops!", "Username or Password already taken!", "error")
		.then(() => {
		  location.href="signup";
		});
    </script>
</body>
</html>