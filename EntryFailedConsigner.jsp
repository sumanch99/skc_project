<!DOCTYPE html>
<html>
<head>
	<title>Failed</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Oops!", "Consigner Insertion Failed!", "error")
		.then(() => {
		  location.href="consignerMaster";
		});
    </script>
</body>
</html>