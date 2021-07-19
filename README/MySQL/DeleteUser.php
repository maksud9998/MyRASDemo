<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"rentasavari");
$email = $_POST["email"];

$qry = "DELETE FROM users WHERE email = '$email' ";
$raw=mysqli_query($conn,$qry);

if($raw)
{
    echo "Profile Deleted";
}
else
{
    echo "Failed To Delete Profile";
}
?>