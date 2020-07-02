<!DOCTYPE html>
<html>
<head>
	<title>Failed</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Oops!", "Profile Updation Falied!", "error")
		.then(() => {
		  location.href="userProfile";
		});
    </script>
</body>
</html>