<!DOCTYPE html>
<html>
<head>
	<title>Failed</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Oops!", "Entry Not Possible!", "error")
		.then(() => {
		  location.href="editinwarddetails";
		});
    </script>
</body>
</html>