<!DOCTYPE html>
<html>
<head>
	<title>Success</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <script lang="javascript">
    	swal("Done!", "Inward LR Details Record Deleted Successfully!", "success")
    	.then(() => {
		  location.href="editinwarddetails";
		});
    </script>

</body>
</html>